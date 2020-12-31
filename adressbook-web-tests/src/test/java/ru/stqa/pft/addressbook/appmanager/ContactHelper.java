package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void initContactModification(int index) {
    driver.findElements(By.cssSelector("a[href^='edit.php?id=']")).get(index).click();
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

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> elementsInEntries = element.findElements(By.cssSelector("td"));
      String lastname = elementsInEntries.get(1).getText();
      String firstname = elementsInEntries.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }


}
