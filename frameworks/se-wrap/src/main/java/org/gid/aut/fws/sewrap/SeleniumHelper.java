package org.gid.aut.fws.sewrap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SeleniumHelper extends Helper{

    private String chromeDriverExePath = this.getClass().getClassLoader().getResource("chromedriver.exe").getPath();

    public WebDriver openBrowser(String browserName) {

        if (isDebug() || !System.getProperty("user.name").toLowerCase().contains("cam_oo_auto")) {
            System.setProperty("browser.downloads.folder.path", "C:\\Users\\azariam\\Downloads");
            System.setProperty("webdriver.chrome.driver", chromeDriverExePath);
            System.setProperty("ui.actions.timeout", "4");
        } else {
            System.setProperty("browser.downloads.folder.path", "C:\\Users\\CAM_OO_Auto\\Downloads");
            System.setProperty("chromeDriverPath", "C:\\jenkins\\tools"); // set system variable from Jenkins.
        }

        // consider downloading driver if not exist.
                /* from open browser BB */
        System.setProperty("reportAutoOpen", "false"); //do not open maas FW report
        System.setProperty("selenium-timeout", System.getProperty("ui.actions.timeout")); //set the timeout for selenium actions (ex: try to click something for this amount of seconds)
        System.setProperty("browserFileDownloadFolderPath", System.getProperty("browser.downloads.folder.path")); //path to the folder where all downloads will be downloaded to

        switch (browserName.toUpperCase()){
            case "CHROME":
                return new ChromeDriver();
            case "FIREFOX":

                break;
            case "IE":

                break;
            case "SAFARI":

                break;
            case "EDGE":

                break;
        }
        throw new NotImplementedException();
    }
}
