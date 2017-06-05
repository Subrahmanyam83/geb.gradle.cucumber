package Module

import geb.Module

/**
 * Created by E002183 on 3/28/2017.
 */
class SearchResultsModule extends Module{

    static content = {

        firstSearchResultsLink          {$("ul.mw-search-results li").getAt(0).find("a")}
    }
}
