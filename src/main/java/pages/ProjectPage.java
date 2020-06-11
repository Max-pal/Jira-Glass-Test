package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends MainPage {

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div/div[1]/header/div/div[3]/div/a")
    private WebElement boardDropDown;

    @FindBy(xpath = "//section[@id='content']//li[6]//a[1]")
    private WebElement componentsButton;

    @FindBy(xpath = "//input[@placeholder='Component name']")
    private WebElement componentNameInputField;

    @FindBy(xpath = "//input[@placeholder='Description (optional)']")
    private WebElement componentDescriptionInputField;

    @FindBy(xpath = "//input[@id='assigneeType-field']")
    private WebElement componentAssigneeInputField;

    @FindBy(xpath = "//div[@class=\"components-add__confirm\"]//button[@class='aui-button aui-button-primary']")
    private WebElement addComponentButton;

    @FindBy(xpath = "//a[@class='component-delete-dialog deletecomponent_link']")
    private WebElement deleteComponentButton;

    @FindBy(xpath = "//input[contains(@class,'aui-button')]")
    private WebElement deleteForSure;

    @FindBy(xpath = "//span[@class='aui-icon aui-icon-small aui-iconfont-more']")
    private WebElement actionButton;

    @FindBy(xpath = "//table[@id=\"components-table\"]//a[contains(text(),'Very Component')]")
    private WebElement expectedComponent;

    @FindBy(xpath = "//a[@class='aui-button aui-button-subtle aui-sidebar-toggle aui-sidebar-footer-tipsy']")
    private WebElement expandSidebarButton;

    public ProjectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void createComponent(String componentName, String componentDescription, String componentAssignee){
        navigateToComponentsPage();
        componentNameInputField.sendKeys(componentName);
        componentDescriptionInputField.sendKeys(componentDescription);
        componentAssigneeInputField.sendKeys(componentAssignee);
        addComponentButton.click();
    }

    public void deleteComponent(){
        navigateToComponentsPage();
        actionButton.click();
        deleteComponentButton.click();
        deleteForSure.click();
    }

    public boolean doesComponentExists(){
        try {
//            driver.findElement(By.xpath("//table[@id=\"components-table\"]//a[contains(text(),'Very Component')]"));
            wait.until(ExpectedConditions.visibilityOf(expectedComponent)).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void navigateToComponentsPage() {
        navigateToUrl(System.getenv("PROJECT_COMPONENTS_URL"));
    }
}
