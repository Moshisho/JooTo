package org.gid.aut.models;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WikipediaMainPage extends BasePage {


    public WikipediaMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "searchInput")
    @CacheLookup
    WebElement searchInput;

    @FindBy(how = How.CSS, using = "button.pure-button.pure-button-primary-progressive")
    @CacheLookup
    WebElement searchButton;

    public WikipediaResultsPage search(String query) {

        searchInput.sendKeys(query);
        searchButton.click();

        return new WikipediaResultsPage(driver);
    }
}
