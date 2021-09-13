package Functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {
    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    private static String resourceFolder;


    /** Se encanga de instanciar el driver con el navegador correspondiente*/
    public static WebDriver createNewWebDriver(String browser) throws IOException {
        WebDriver driver;
        prop.load(in);
        resourceFolder = prop.getProperty("resourceFolder");
        //Instancia de driver para navegador Google Chrome
        if ("Chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", resourceFolder +"/chromedriver.exe");
            driver = new ChromeDriver();
        }

        //Instancia de driver para navegador Mozilla FireFox
        else if ("FireFox".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.gecko.driver", resourceFolder + "/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        //Instancia de driver para navegador Internet Explorer
        else if ("Internet Explorer".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.ie.driver", resourceFolder +"/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        //Caso de no encontrar los driver correspondientes.
        else {
            return null;
        }
        driver.manage().window().maximize();
        return driver;

    }
}
