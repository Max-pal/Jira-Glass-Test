package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class GlassPage extends MainPage {

    @FindBy(xpath = "//li[@id='glass-workflow-nav']/a")
    private WebElement issueTypes;
    @FindBy(xpath = "//*[@data-issue-type='Improvement']/a")
    private WebElement improvementIssueType;
    @FindBy(xpath = "//li[@id='glass-general-nav']/a")
    private WebElement general;
    @FindBy(xpath = "//li[@id='glass-people-nav']/a")
    private WebElement people;
    @FindBy(xpath = "//li[@id='glass-permissions-nav']/a")
    private WebElement permissions;
    @FindBy(xpath = "//li[@id='glass-notifications-nav']/a")
    private WebElement notifications;
    @FindBy(xpath = "//a[text()='Components']")
    private WebElement componentsTab;
    @FindBy(xpath = "//a[text()='Versions']")
    private WebElement versionsTab;
    @FindBy(xpath = "//a[text()='Schemes']")
    private WebElement schemesTab;
    @FindBy(xpath = "//td[@class='components-table__assignee']//a")
    private WebElement assignee;

    public GlassPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void navigateToComponentsTab() {
        navigateToUrl(System.getenv("GLASS_PROJECTURL"));
        componentsTab.click();
    }

    public boolean doesComponentExists(){
        try {
            driver.findElement(By.xpath("//table[@id=\"components-table\"]//a[contains(text(),'"+System.getenv("COMPONENT_NAME")+"')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

//    TODO: Add env to xpath
    public WebElement doesComponentAssigneePresent() {
        return assignee;
    }

    public boolean doesComponentDescriptionPresent(){
        try {
            driver.findElement(By.xpath("//td[@class=\"glass-components-table__description\"]//div[contains(text(),'"+System.getenv("COMPONENT_DESCRIPTION")+"')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
