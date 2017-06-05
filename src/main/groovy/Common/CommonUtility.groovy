package Common

import geb.Module
import geb.navigator.Navigator
import org.openqa.selenium.WebDriver

import java.text.SimpleDateFormat

/**
 * Created by E002149 on 6/29/2016.
 */
class CommonUtility extends Module{

    def getCurrentDateForGenomeValidations(String tzone){
        Date currDate = new Date()
        SimpleDateFormat sdf = new SimpleDateFormat("MMM , yyyy");
        formatDateToString(currDate,sdf,tzone)
    }

    def getBackgroundColorOfWebElement(Navigator navigator){
        return navigator.firstElement().getCssValue("background-color")
    }

    def getColorOfWebElement(Navigator navigator){
        return navigator.firstElement().getCssValue("color")
    }

    def formatDateToString(Date date, SimpleDateFormat dateFormat, String timeZone) {
        if (date == null) return null;
        if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
            timeZone = Calendar.getInstance().getTimeZone().getID();
        }
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(date);
    }

    def clickUsingJavaScriptExecutor(Navigator navigator){
        js.execjs("arguments[0].click();",navigator.firstElement())
    }

    def scrollPageToRightMostSide(WebDriver driver){
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(2000,0)", "");
    }

    def scrollPageToLeftMostSide(WebDriver driver){
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(-2000,0)", "");
    }

    def scrollToCenter(Navigator navigator, WebDriver driver) {
        int center = driver.manage().window().getSize().getHeight() / 2
        int locatorHeight = navigator.firstElement().getLocation().getY() - center
        js.exec("window.scrollTo(0,$locatorHeight);")
        Thread.sleep(1000)
    }

    def dragAndDropSliderHandle(Navigator navigator, int X, int Y){
        interact {
            clickAndHold(navigator)
            moveByOffset(X,Y)
            release()
        }
    }
}