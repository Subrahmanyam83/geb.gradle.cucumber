package Specs

import Specs.Utilities.BaseRunner
import cucumber.api.CucumberOptions

/**
 * Created by Subrahmanyam on 9/9/2016.
 */

@CucumberOptions(
        features=["src/test/resources/features/"],
        format=["html:target/site/cucumber-pretty", "pretty","rerun:target/rerun.txt","json:target/cucumber.json"],
        tags=["@sanity"])

class Runner extends BaseRunner {}
