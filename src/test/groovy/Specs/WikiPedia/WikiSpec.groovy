package Specs.WikiPedia

import Common.BaseSpec
import Pages.DashboardPage
import Pages.WikiPage
import cucumber.api.java.en.And
import cucumber.api.java.en.Given

/**
 * Created by Subrahmanyam on 3/13/2017.
 */
class WikiSpec extends BaseSpec{

    @Given("I search the text \"(.*?)\" in Wiki Home Page")
    def I_Search_For_String_In_Wiki_Home_Page(String searchString) {
        to WikiPage
        searchForText(searchString)

        at DashboardPage
    }

    @And("I open the first link and verify the title to be \"(.*?)\"")
    def I_Open_First_Link(String verificationText){
        verifyTheTitle(verificationText)
    }
}
