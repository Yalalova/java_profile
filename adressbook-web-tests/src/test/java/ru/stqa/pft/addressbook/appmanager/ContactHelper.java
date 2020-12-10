package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.fail;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver driver) {
    super (driver);
  }

  public void submitContactCreation() {
    driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());

  }


 public void addNewContact() {
  click(By.linkText("add new"));
  }

  public void clickDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));

  }

  public void initContactModification() {
    click((By.xpath("(//img[@alt='Edit'])[1]")));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[1]"));
  }
}
