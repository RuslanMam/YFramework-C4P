package ui_automation.utilities;

import org.openqa.selenium.WebDriver;

public class Driver {
    ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();// thread local driver object for webdriver
    private Driver(){

    }
    private static Driver instance=new Driver();
    public static Driver getInstance(){
        return instance;
    }



    public WebDriver getDriver(){
        return driver.get();
    }
    public  void setDriver(WebDriver driverParameter){
        driver.set(driverParameter);
    }
    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }
}