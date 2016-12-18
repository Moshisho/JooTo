package org.gid.aut.tests.small;

import org.gid.aut.fws.sewrap.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public abstract class BaseTestClass {

    protected WebDriver driver;
    private SeleniumHelper helper = new SeleniumHelper();

    public WebDriver getDriver(){
        return driver;
    }

    public void setUpDriver(String browserName, String appURL){
        driver = helper.openBrowser(browserName);
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
    }

    //this is called before the beforeclass of the test itself.
    @Parameters({ "app.url", "browser.name" })
    @BeforeClass
    public void initializeTestBaseSetup(String appURL, String browserName) {
        System.out.println("My URL:"+ appURL);
        try {
            setUpDriver(browserName, appURL);

        } catch (Exception e) {
            System.out.println("Error....." + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    private Boolean isDebug = null;

    public Boolean isDebug() {

        if (isDebug == null)
            setDebug();

        return isDebug;
    }

    private void setDebug() {
        isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }
}
