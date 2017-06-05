package Pages

import Common.BasePage
import Module.SearchResultsModule

/**
 * Created by E002183 on 3/28/2017.
 */
class SearchResultsPage extends BasePage{

    static at ={

    }

    static content ={
        searchResults       {module SearchResultsModule}
    }

    def clickOnFirstResult(){
        click({searchResults.firstSearchResultsLink})
    }


}
