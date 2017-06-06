package Specs.Utilities.Listeners

import Common.Constants
import Reports.GenerateReport
import cucumber.api.Scenario
import cucumber.api.java.After
import cucumber.api.java.Before
import geb.testng.GebTest
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.testng.IExecutionListener

/**
 * Created by Subrahmanyam on 17-10-2016.
 */
class CucumberListener extends GebTest implements IExecutionListener,Constants{

    def rootDir = new File(".").getCanonicalPath()
    def sep = File.separator;

    //public ExcelReader xlsrdr = new ExcelReader(System.getProperty("user.dir") + "/src/main/resources/TestData.xls".replace("/", sep), "TestData");
    def Map sysInfo = new HashMap();


    @Before
    void beforeScenario(Scenario scenario) {
    }

    @After
    def afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("screenshots.path")+scenario+".png");
            FileUtils.copyFile(scr, dest);
            Thread.sleep(3000);
            if(driver instanceof TakesScreenshot) {
                byte[] screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshotData, "image/png");
            }
        }
        //driver.manage().deleteAllCookies();
    }

    @Override
    public void onExecutionStart() {
        convertPropertiesToSystemProperties();
    }

    @Override
    public void onExecutionFinish() {
        GenerateReport.GenerateReport();
        this.driver.quit();
    }

    /* This Function reads the project.properties file and puts each K,V pair into System Properties.*/
    def convertPropertiesToSystemProperties() {
        sysInfo = new HashMap();
        String rootDir = new File(".").getCanonicalPath() /*Absolute Path*/
        String projectPropertiesPath = rootDir + "/src/test/resources/project.properties".replace('/', File.separator)
        Properties properties = new Properties()
        properties.load(new FileInputStream(projectPropertiesPath))
        properties.each { key, value ->
            if (System.getProperty(key) == null) {
                System.setProperty(key, value);
                sysInfo.put(key,value);
            }
        }
    }
}

