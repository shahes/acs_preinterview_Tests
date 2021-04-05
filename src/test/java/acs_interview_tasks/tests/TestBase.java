package acs_interview_tasks.tests;

import acs_interview_tasks.utilities.ConfigurationReader;
import acs_interview_tasks.utilities.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    @BeforeMethod
    public void setUp(){
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



   @AfterMethod
    public void tearDown(){
     Driver.closeDriver();
    }




}
