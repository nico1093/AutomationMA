package StepsDefinitions;

import Functions.CreateDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;

import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;



import java.io.IOException;

public class Hooks {
    public static WebDriver driver;
    Scenario scenario = null;

    @Before
    public void initDriver(Scenario scenario) throws IOException {
        driver = CreateDriver.initConfig();
        this.scenario = scenario;
    }

    @After
    public void tearDown() {

        driver.close();
    }
}
