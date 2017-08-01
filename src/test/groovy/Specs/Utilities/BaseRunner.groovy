package Specs.Utilities

import Reports.GenerateReport
import cucumber.api.testng.AbstractTestNGCucumberTests
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite

/**
 * Created by n471306 on 22/06/2017.
 */
class BaseRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    def beforeSuite(){
        convertPropertiestoSystemProperties();
        deleteTargetFolder()
    }

    def convertPropertiestoSystemProperties(){
        String rootDir = new File(".").getCanonicalPath() /*Absolute Path*/
        String projectPropertiesPath = rootDir + "/src/test/resources/project.properties".replace('/', File.separator)
        Properties properties = new Properties()
        properties.load(new FileInputStream(projectPropertiesPath))
        properties.each { key, value ->
            if (System.getProperty(key) == null) {
                System.setProperty(key, value);
            }
        }
    }

    @AfterSuite
    def generateReport(){
        if(!new File("target/Reports").exists()){
            GenerateReport.GenerateReport();
        }
    }

    def deleteTargetFolder(){
        def targetFolder = new File('target');
        targetFolder.deleteDir();
    }
}
