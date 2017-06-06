package Module

import geb.Module

/**
 * Created by E002183 on 3/28/2017.
 */
class DashboardModule extends Module{

    static content = {
        searchWikipediaTextField            {$("input",'type':'search')}
        firstHeadingTitleText               {$("#firstHeading").text()}
    }
}
