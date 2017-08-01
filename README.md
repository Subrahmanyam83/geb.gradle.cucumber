## Geb-BDD Framework

###### HOW TO RUN A TEST CASE
1. Provide the feature file path and scenario tag of the scenario in project.properties.
    eg: feature.file.path=src/test/resources/features/
        feature.file.tags=@sanity,@regression
        
2. Execute command: ```gradle chromeTest```(To execute the test case on chrome) or ```gradle firefoxTest```(To execute the test case on firefox)      
        
###### PARALLELISM:
1. Set the following parameters in project.properties:
    a. ```threadCount =```, 
    b. ```gridUrl =```, 
    c. ```class.name =``` comma separated absolute paths of the Runners
    
###### REPORTS:
1. Reports are generated in the following folder: target/reports/feautre-overview    
    
###### USP of the FRAMEWORK:
1. BDD Framework combined with Geb, Gradle and Groovy
2. Groupism
3. Screenshots for Failed Scenarios
4. Re-Run Functionality
5. Listeners - Cucumber and TestNG
6. Soft and Hard Assertions
