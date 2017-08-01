package Specs.Utilities

import org.testng.TestNG
import org.testng.xml.XmlClass
import org.testng.xml.XmlSuite
import org.testng.xml.XmlTest

/**
 * Created by E002183 on 5/7/2016.
 */
class TestNGXMLCreator {

    public static void main(String[] args) {
        convertPropertiesToSystemProperties();

        String CLASSES = System.getProperty("class.name").trim();

        int THREAD_COUNT = System.getProperty("threadCount").trim().toInteger();
        String ROOT_DIR = new File(".").getCanonicalPath();
        String TESTNG_XML_FILE_PATH = ROOT_DIR + System.getProperty("testng.xml.file.path").replace("/", File.separator)
        String SUITE_NAME = System.getProperty("suite.name")
        String TEST_NAME = System.getProperty("test.name")
        String TESTNG_LISTENER_PATH = System.getProperty("testng.listener.path")

        TestNG myTestNG = new TestNG();
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();

        XmlSuite suite = new XmlSuite();
        suite.setName(SUITE_NAME);
        suite.setParallel("tests");
        suite.setThreadCount(THREAD_COUNT)
        suite.addListener(TESTNG_LISTENER_PATH)

        XmlTest test = new XmlTest(suite);
        test.setName(TEST_NAME);
        test.setPreserveOrder("true");

        /*Create only CLASSES*/
            List<XmlClass> classList = new ArrayList<XmlClass>();
            CLASSES.split(",").each {
                classes -> classList.add(new XmlClass(classes));
            }
            test.setXmlClasses(classList);

        File file = new File(TESTNG_XML_FILE_PATH);
        FileWriter writer = new FileWriter(file);
        writer.write(suite.toXml());
        writer.close();
        mySuites.add(suite);

        myTestNG.setXmlSuites(mySuites);
    }

    public static void convertPropertiesToSystemProperties() {
        String project_properties_path = "/src/test/resources/project.properties".replace('/', File.separator)
        String projectPropertiesPath = new File(".").getCanonicalPath() + project_properties_path;
        Properties properties = new Properties()
        properties.load(new FileInputStream(projectPropertiesPath))
        properties.each { key, value ->
            if (System.getProperty(key) == null) {
                System.setProperty(key, value);
            }
        }

    }
}
