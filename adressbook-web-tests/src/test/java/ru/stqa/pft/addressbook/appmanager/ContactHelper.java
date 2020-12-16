package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.fail;

public class ContactHelper extends HelperBase {

  private boolean creation;
  private ContactData contact;

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void submitContactCreation() {
    driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {

    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
     Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void addNewContact() {
  click(By.linkText("add new"));
  }

  public void clickDelete() {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();

  }

  public void initContactModification() {
    click((By.xpath("(//img[@alt='Edit'])[1]")));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[1]"));
  }

  public boolean isCreation() {
    return creation;
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }


  public void createNewContact(ContactData contact, boolean b) {
    addNewContact();
    fillContactForm(contact, b);
    submitContactCreation();
  }

  public int getContactCount() {
    return driver.findElements(By.name("selected[]")).size();
  }
}

