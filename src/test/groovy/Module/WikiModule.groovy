package Module

import geb.Module

/**
 * Created by E002183 on 3/13/2017.
 */
class WikiModule extends Module{

    static content = {
        searchField         {$("#searchInput")}
        searchButton        {$("i.sprite-icons.sprite-icons-search-icon")}
    }
}
