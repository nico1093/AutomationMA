package Functions;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CreateDriver {
    private static String browser;

    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");


    /** Se encarga de inicializar el driver unicamente*/
    public static WebDriver initConfig() throws IOException {
        WebDriver driver = null;

        try {
            prop.load(in);
            browser = prop.getProperty("browser");
        } catch (IOException e) {
            e.getMessage();
        }
        //Se establece la creacion del driver en caso que se encuentre los datos en el properties.
        driver = WebDriverFactory.createNewWebDriver(browser);
        return driver;
    }
}
