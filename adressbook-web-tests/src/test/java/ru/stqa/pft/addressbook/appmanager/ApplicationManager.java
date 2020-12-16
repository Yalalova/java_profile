package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;



  public class ApplicationManager  {
    WebDriver driver;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    public boolean acceptNextAlert = true;
    private String baseUrl;
    private String browser;

    public ApplicationManager(String browser) {
      this.browser = browser;
    }

    public void init() {
      //driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
       if (browser.equals(BrowserType.CHROME)) {
        driver = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)) {
          driver = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE)) {
          driver = new InternetExplorerDriver();
      }

      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      driver.get("http://localhost/addressbook/");
      groupHelper = new GroupHelper(driver);
      navigationHelper = new NavigationHelper(driver);
      sessionHelper = new SessionHelper(driver);
      contactHelper = new ContactHelper(driver);
      sessionHelper.login("admin", "secret");
    }


    public void stop() {
    driver.quit();
    }

    public boolean isElementPresent(By by) {
      try {
      driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public boolean isAlertPresent() {
      try {
      driver.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    public String closeAlertAndGetItsText() {
      try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
          alert.accept();
        } else {
          alert.dismiss();
        }
        return alertText;
      } finally {
        acceptNextAlert = true;
      }
    }

   public GroupHelper getGroupHelper() {
      return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
      return navigationHelper;
    }

    public ContactHelper getContactHelper() {
      return contactHelper;
    }
    public SessionHelper getSessionHelper() {
      return sessionHelper;
    }

  }
