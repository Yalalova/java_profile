package ru.stqa.pft.addressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class TestBase {
  private WebDriver driver;
  private String baseUrl;
  public boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    //driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver = new ChromeDriver();

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  public void login(String username, String password) {
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
  }

  public void logout() {
    driver.findElement(By.linkText("Logout")).click();
  }

  protected void returntoGroupPage() {
    driver.findElement(By.linkText("group page")).click();
  }

  protected void submitGroupCreation() {
    driver.findElement(By.name("submit")).click();
  }

  protected void fillGroupForm(GroupData groupData) {
    driver.findElement(By.name("group_name")).click();
    driver.findElement(By.name("group_name")).clear();
    driver.findElement(By.name("group_name")).sendKeys(groupData.getName());
    driver.findElement(By.name("group_header")).click();
    driver.findElement(By.name("group_header")).clear();
    driver.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    driver.findElement(By.name("group_footer")).click();
    driver.findElement(By.name("group_footer")).clear();
    driver.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  protected void initGroupCreation() {
    driver.findElement(By.name("new")).click();
  }

  protected void gotoGroupPage() {
    driver.findElement(By.linkText("groups")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
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

  public void deleteSelectedGroups() {
    driver.findElement(By.name("delete")).click();
  }

  public void selectGroup() {
    driver.findElement(By.xpath("(//input[@name='selected[]'])[10]")).click();
  }

  protected void returnToHomePage() {
    driver.findElement(By.linkText("home")).click();
  }

  protected void submitContactCreation() {
    driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  protected void fillContactForm(ContactData contactData) {
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  protected void addNewContact() {
    driver.findElement(By.linkText("add new")).click();
  }


  protected void clickDelete() {
    driver.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  protected void selectContact() {
    driver.findElement(By.xpath("(//input[@name='selected[]'])")).click();

  }
}

