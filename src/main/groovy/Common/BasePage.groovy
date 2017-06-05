package Common

import Utilities.Data.ExcelReader
import com.relevantcodes.extentreports.ExtentTest
import geb.Page

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created by E002183 on 3/28/2017.
 */
class BasePage extends Page implements Constants{

    public String rootDir = new File(".").getCanonicalPath();
    public String sep = File.separator;
    public ExcelReader xlsrdr = new ExcelReader(rootDir+ "/src/main/resources/TestData.xls".replace('/', sep),"Credentials");
    public static ThreadLocal extentTest = new ThreadLocal<ExtentTest>()

    public static synchronized ExtentTest getEreportTest() {
        return extentTest.get();
    }

    public static synchronized void setEReporterTest(ExtentTest etest){
        extentTest.set(etest);
    }

    public void type(Closure closure, String value,String elementName){
        waitFor closure
        closure() << value
        if(!getEreportTest().equals(null)){
            getEreportTest().log(PASS,"Entered : '"+value+"' on "+elementName);
        }
    }

    public void click(Closure closure,String elementName){
        waitFor closure
        closure().click()
        if(!getEreportTest().equals(null)){
            getEreportTest().log(PASS,"Clicked on: "+elementName);
        }
    }

    def generateRandom() {
        Random r = new Random(System.currentTimeMillis());
        return 1000000000 + r.nextInt(2000000000);
    }

    def getCurrentDate(){
        Date currDate = new Date()
        DateFormat dFormat = new SimpleDateFormat("MMM dd, yyyy")
        return dFormat.format(currDate).toString()
    }
}
