import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.GlassPage;
import pages.ProjectPage;

import java.net.MalformedURLException;

public class GlassComponentTest extends BaseTest {
    public static GlassPage glassPage;
    public static ProjectPage projectPage;

    private static final String componentName = System.getenv("COMPONENT_NAME");
    private static final String componentDescription = System.getenv("COMPONENT_DESCRIPTION");
    private static final String componentAssignee = System.getenv("COMPONENT_ASSIGNEE");


    @BeforeAll
    public static void setUp() throws MalformedURLException {
        BaseTest.setUp();
        glassPage = new GlassPage(driver);
        projectPage = new ProjectPage(driver);
        projectPage.login(USERNAME, PASSWORD);
        projectPage.createComponent(componentName, componentDescription, componentAssignee);
    }

    @Test
    public void viewComponentName() {
        glassPage.navigateToComponentsTab();
        Assertions.assertTrue(glassPage.doesComponentExists());
    }

    @Test
    public void viewComponentsAssignee() {
        glassPage.navigateToComponentsTab();
        Assertions.assertTrue(glassPage.doesComponentAssigneePresent());
    }

    @Test
    public void viewComponentDescription(){
        glassPage.navigateToComponentsTab();
        Assertions.assertTrue(glassPage.doesComponentDescriptionPresent());
    }


    @AfterAll
    public static void teardown() {
        projectPage.deleteComponent();
    }
}

