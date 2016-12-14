package org.gid.aut.common.api;

import java.io.File;


public interface Logger {

    public void info(String message);
    public void error(String message);
    public void error(String message, Exception e);
    public void startLevel(String level);
    public void stopLevel();
    public File getLogDir();
    public void addLink(String title, String link);
    public String getCurrentTestFolder();

}
