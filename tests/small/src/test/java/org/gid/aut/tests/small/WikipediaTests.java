package org.gid.aut.tests.small;

import org.gid.aut.models.WikipediaMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WikipediaTests extends BaseTestClass {

    private WebDriver driver;
    private WikipediaMainPage wikipediaMainPage;
    private String url = "https://wikipedia.org";

    @BeforeClass
//    @Parameters({ "browser.name" })
    public void setup() {

        driver = getDriver();
        wikipediaMainPage = PageFactory.initElements(driver, WikipediaMainPage.class);

    }

    @Test
    public void search(){

        wikipediaMainPage.search(".NET");

        Assert.assertTrue(driver.getTitle().contains(".NET"));
    }


}
