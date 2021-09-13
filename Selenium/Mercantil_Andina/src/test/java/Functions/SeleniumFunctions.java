package Functions;

import StepsDefinitions.Hooks;
import cucumber.api.java.en.And;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumFunctions {
    static WebDriver driver;
    public static Properties prop = new Properties();
    public static InputStream in = SeleniumFunctions.class.getResourceAsStream("../test.properties");

    public static String FileName = "";
    public static String PagesFilePath = "src/test/resources/Pages/";

    public static String field = "";
    public static String value = "";

    public SeleniumFunctions(){
        driver = Hooks.driver;
    }

    /** Se encarga de leer la variable con el nombre pasado por el parametro */
    public String readProperties(String property) throws IOException {
        prop.load(in);
        return prop.getProperty(property);
    }


    /** Se encarga de leer un archivo en formato json */
    public static Object readJson() throws Exception {
        FileReader reader = new FileReader(PagesFilePath + FileName);
        try {
            if (reader != null) {
                JSONParser jsonParser = new JSONParser();
                return jsonParser.parse(reader);
            } else {
                return null;
            }
        } catch (FileNotFoundException | NullPointerException e) {
            throw new IllegalStateException("ReadEntity: No existe el archivo " + FileName, e);
        }

    }

    /** Se encarga de leer una entidad que sen encuentra en un json*/
    public static JSONObject ReadEntity(String element) throws Exception {
        JSONObject Entity = null;
        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        return Entity;
    }

    /** Se denota el elemento by que esta asociado con el parametro dentro de un arhivo json */
    public static By getCompleteElement(String element) throws Exception {
        By result = null;
        JSONObject Entity = ReadEntity(element);

        field = (String) Entity.get("field");
        value = (String) Entity.get("value");

        if ("id".equalsIgnoreCase(field)) {
            result = By.id(value);
        } else if ("name".equalsIgnoreCase(field)) {
            result = By.name(value);
        } else if ("xpath".equalsIgnoreCase(field)) {
            result = By.xpath(value);
        } else if ("className".equalsIgnoreCase(field)) {
            result = By.className(value);
        } else if ("cssSelector".equalsIgnoreCase(field)) {
            result = By.cssSelector(value);
        } else if ("link".equalsIgnoreCase(field)) {
            result = By.partialLinkText(value);
        } else if ("tagName".equalsIgnoreCase(field)) {
            result = By.tagName(value);
        } else if ("linkText".equalsIgnoreCase(field)) {
            result = By.linkText(value);
        }
        return result;
    }

    /** Completa el campo dado con el valor dado */
    public void iSetKeyValueIn(String element, String value) throws Exception {
        By seleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(seleniumElement).clear();
        driver.findElement(seleniumElement).sendKeys(value);
    }

    /** El metodo selecciona en un despegable el valor que se le pasa por parametro. */
    public void selectOptionDropdownByText(String element, String option) throws Exception
    {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        Select opt = new Select(driver.findElement(SeleniumElement));
        opt.selectByVisibleText(option);
    }

    /** Realiza un doble clic sobre un elemento */
    public void doubleClic(String element) throws Exception {
        Actions action = new Actions(driver);
        By seleniumElement = SeleniumFunctions.getCompleteElement(element);
        action.moveToElement(driver.findElement(seleniumElement)).doubleClick().perform();
    }

    /** Hace un clic sobre un elemento */
    public void iClicInElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).click();
    }

    /** Espera hasta que el elemento dado se encuentre presente en la web */
    public void waitForElementPresent(String element,int time) throws Exception
    {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait w = new WebDriverWait(driver, time);
        w.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
    }

    /** Espera hasta que el elemento dado sea visible en la web */
    public void waitForElementVisible(String element, int time) throws Exception
    {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait w = new WebDriverWait(driver, time);
        w.until(ExpectedConditions.visibilityOfElementLocated(SeleniumElement));
    }

    /** Espera la cantidad de tiempo determinada. */
    public void absoluteWait(int time){
        try{
            Thread.sleep(time * 1000);
        }catch (InterruptedException e){
            e.getMessage();
        }
    }

    /** Obtiene el texto contenido en un elemento dado. */
    public String getTextIn(String element) throws Exception {
        return driver.findElement(SeleniumFunctions.getCompleteElement(element)).getText();
    }

    /** Obtiene el precio total de la cotizacion. */
    public String getNumberPrice(String element){
        String result = "";
        for(char c : element.toCharArray()){
            if(Character.getNumericValue(c) >= 0 || c == '-'|| c == ','){
                result+=c;
            }
        }
        return result;
    }

    /** Cambia del frame actual al frame determinado por paramentro. */
    public void switchToFrame(String Frame) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(Frame);
        driver.switchTo().frame(driver.findElement(SeleniumElement));
    }
}
