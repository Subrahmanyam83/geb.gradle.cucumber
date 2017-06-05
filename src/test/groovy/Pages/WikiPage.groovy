package Pages

import Module.WikiModule
import Common.BasePage

/**
 * Created by E002183 on 3/13/2017.
 */
class WikiPage extends BasePage{

    static at = {wikiHome.searchField}

    static content ={
        wikiHome  {module WikiModule}
    }

    def searchForText(String searchString){
        type({wikiHome.searchField},searchString,"Search Field")
        click ({wikiHome.searchButton},"Search Button")
    }
}
