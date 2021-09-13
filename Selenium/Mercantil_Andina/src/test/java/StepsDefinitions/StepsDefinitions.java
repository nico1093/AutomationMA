package StepsDefinitions;

import Functions.SeleniumFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class StepsDefinitions {
    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();

    public StepsDefinitions() {
        driver = Hooks.driver;
    }

    @Given("^Acceder a la web de mercantil andina")
    public void getURL() throws IOException {
        String url = functions.readProperties("url");
        driver.get(url);
    }

    @Then("^Cargar la informacion DOM (.*)")
    public void iLoadTheDOMInformation(String file) throws Exception {
        SeleniumFunctions.FileName = file;
        SeleniumFunctions.readJson();
    }

    @And("^Hacer clic en el elemento (.*)")
    public void iClicInElement(String element) throws Exception {
        functions.iClicInElement(element);
    }

    @And("^Hacer doble Clic sobre (.*)")
    public void doubleClick(String element) throws Exception {
        functions.doubleClic(element);
    }

    @Then("^Esperar hasta (.*) segundos a que el elemento (.*) este presente$")
    public void waitForElementPresent(String time, String element) throws Exception {
        functions.waitForElementPresent(element, Integer.parseInt(time));
    }

    @Then("^Esperar hasta (.*?) segundos a que el elemento (.*) sea visible$")
    public void waitForElementVisible(String time, String element) throws Exception {
        functions.waitForElementVisible(element, Integer.parseInt(time));
    }

    @Then("^Esperar hasta (.*) segundos")
    public void absoluteWait(String time){
        functions.absoluteWait(Integer.parseInt(time));
    }

    @And("^Completar el campo (.*) con el texto (.*)")
    public void iSetElementWithText(String element, String text) throws Exception {
        functions.iSetKeyValueIn(element, text);
    }

    @And("Seleccionar en el despegable (.*?) el valor (.*?)$")
    public void iSetTextInDropdown(String element, String option) throws Exception {
        functions.selectOptionDropdownByText(element, option);
    }

    @Then("^Validar que el titulo de la Web sea (.*)$")
    public void checkTitle(String value){
        WebDriver driver = Hooks.driver;
        Assert.assertEquals(driver.getTitle(), value);
    }

    @Then("^Validar el (.*) en la cotizacion$")
    public void checkCotizacion(String element) throws Exception {
        String visualCosto = functions.getTextIn(element);
        visualCosto = functions.getNumberPrice(visualCosto);
        int costo = Integer.parseInt(visualCosto);
        Assert.assertTrue(costo > 0);
    }

    @Then("^Validar que el elemento (.*) se visualiza.$")
    public void checkVisualElement(String element) throws Exception {
        By elementCheck = SeleniumFunctions.getCompleteElement(element);
        Assert.assertTrue(driver.findElement(elementCheck).isDisplayed());
    }

    @When("^Cambiar al Frame: (.*?)$")
    public void switchToFrame(String Frame) throws Exception {
        functions.switchToFrame(Frame);
    }

}
