package org.gid.aut.fws.sewrap;


public abstract class Helper {

    public static Boolean isDebug() {
        return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }

}
