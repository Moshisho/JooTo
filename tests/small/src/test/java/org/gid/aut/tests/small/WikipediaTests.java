package org.gid.aut.tests.small;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class WikipediaTests extends BaseTestClass {

    private WebDriver driver;

    @BeforeClass
    @Parameters({ "browser.name" })
    public void setup(@Optional("FIREFOX") String browser) {



    }


}
