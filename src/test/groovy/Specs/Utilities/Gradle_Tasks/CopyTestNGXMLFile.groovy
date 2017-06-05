package Specs.Utilities.Gradle_Tasks

import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * Created by E002183 on 7/14/2016.
 */
class CopyTestNGXMLFile {

    public static void main(String[] args) {
        if(System.getProperty("current.date").empty){
            System.setProperty("current.date","null")
        }
        if((new File(new File(".").getCanonicalPath()+("/reports/Junit-Reports/"+System.getProperty("current.date")+"/testng-failed.xml").replace("/",File.separator)).exists())){
            Files.copy(new File(new File(".").getCanonicalPath()+("/reports/Junit-Reports/"+System.getProperty("current.date")+"/testng-failed.xml").replace("/",File.separator)).toPath(), new File(new File(".").getCanonicalPath()+("/src/test/resources/xmls/testng-failed.xml")).toPath(),StandardCopyOption.REPLACE_EXISTING)
        }
        else{
            Files.copy(new File(new File(".").getCanonicalPath()+("/src/test/resources/xmls/testng-pass.xml").replace("/",File.separator)).toPath(), new File(new File(".").getCanonicalPath()+("/src/test/resources/xmls/testng-failed.xml")).toPath(),StandardCopyOption.REPLACE_EXISTING)
        }
    }
}
