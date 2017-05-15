package org.gid.aut.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessesUtils {

    final static Logger log = LoggerFactory.getLogger(ProcessesUtils.class);

    // get max ram process
    // by name

    public static List<ProcessInfo> getRunningProcesses(){
        String processCommand = getProcessesCommandByOS();
        Process p = tryExecuteCommand(processCommand);
        return getProcessInfos(p);
    }

    public static Process tryExecuteCommand(String processCommand) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(processCommand);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return p;
    }

    private static List<ProcessInfo> getProcessInfos(Process p) {
        List<ProcessInfo> processes = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))){
            String line;
            while ((line = input.readLine()) != null) {
                processes.add(ProcessInfoParser.parseOutputLine(line));
            }
        } catch (Exception err) {
            log.error(err.toString(), err);
        }
        log.info("Found {} processes", processes.size());
        processes.sort(Comparator.comparingInt(ProcessInfo::getRAMinMegaBytes).reversed());
        log.info("Max RAM usage in KBytes: {} {}", processes.get(0).name, processes.get(0).getRAMinMegaBytes());
        return processes;
    }

    private static String getProcessesCommandByOS() {
        String processCommand;
        switch (OSUtils.getOperatingSystemType()){
            case Linux:
            case MacOS:
            case Other:
            default:
                processCommand = "ps -e";
                break;
            case Windows:
                // /nh = no header, /fo = format
                processCommand = System.getenv("windir") +"\\system32\\tasklist.exe  /fo csv /nh";
                break;
        }
        return processCommand;
    }

    public class ProcessInfo {

        public final int processID;
        public final String name;
        public final int RAMinMegaBytes;
        public final String SessionName;
        public final int SessionNumber;

        public ProcessInfo(String name, int processID, String sessionName, int sessionNumber, int RAMinMegaBytes) {
            this.processID = processID;
            this.name = name;
            this.RAMinMegaBytes = RAMinMegaBytes;
            SessionName = sessionName;
            SessionNumber = sessionNumber;
        }

        public int getRAMinMegaBytes() {
            return RAMinMegaBytes;
        }
    }
}
class ProcessInfoParser {

    public static ProcessesUtils.ProcessInfo parseOutputLine(String line){
        String regExByOS;
        switch (OSUtils.getOperatingSystemType()){
            case Linux:
            case MacOS:
            case Other:
            default:
                regExByOS = " ";
                break;
            case Windows:
                regExByOS = "([^\",]*)";
                break;
        }
        Pattern p = Pattern.compile(regExByOS);
        Matcher m = p.matcher(line);

        List<String> allMatches = new ArrayList<>();
        while (m.find()) {
            if (m.group().isEmpty())
                continue;
            allMatches.add(m.group());
        }

        return new ProcessesUtils().new ProcessInfo(allMatches.get(0),
                Integer.parseInt(allMatches.get(1)),
                allMatches.get(2),
                Integer.parseInt(allMatches.get(3)),
                Integer.parseInt(allMatches.get(4).replace(" K", "")));
    }
}
