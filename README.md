PARALLELISM:
1. Set the following parameters in project.properties:
    a. threadCount, 
    b. gridUrl, 
    c. class.name = <comma separated absolute paths of the Runners>
    
HOW TO RUN TEST CASE
1.  Provide the feature file path and scenario tag to run that particular scenario tagged with the given tag in the feature file mentioned.
    eg: feature.file.path=src/test/resources/features/
        feature.file.tags=@sanity,@regression
        
2. If you want to run multiple Runnners, then give the value as comma separated Runners as value to the key: class.name 
   in project.properties.

3. 


USP of the FRAMEWORK:
1. BDD Framework combined with Geb, Gradle and Groovy::: Done
2. Groupism::: Done
3. Screenshots for Failed Scenarios::: Done
4. Re-Run
5. Listeners- Combined Cucumber and TestNG::: Done
6. Soft and Hard Assertions ::: To be Implemented
