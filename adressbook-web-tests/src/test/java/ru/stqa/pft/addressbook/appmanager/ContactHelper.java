package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("mobile"), contactData.getMobilePhone());
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


  public void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void initContactModification(int index) {
    driver.findElements(By.cssSelector("a[href^='edit.php?id=']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    driver.findElement(By.cssSelector(String.format("a[href^='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[1]"));


  }

  public void modifyContact(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();

  }


  public void removeContact(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectcontacts();
    confirmAction();
    returnToHomePage();
  }

  private void confirmAction() {
    driver.switchTo().alert().accept();
  }

  private void deleteSelectcontacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
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

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = driver.findElements(By.cssSelector("[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> elementsInEntries = element.findElements(By.cssSelector("td"));
      String lastname = elementsInEntries.get(1).getText();
      String firstname = elementsInEntries.get(2).getText();
      String allPhones = elementsInEntries.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllphones(allPhones));
    }
    return contacts;

  }
    public ContactData infoFromEditForm (ContactData contact){
      initContactModificationById(contact.getId());
      String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
      String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
      String homephone = driver.findElement(By.name("home")).getAttribute("value");
      String mobilephone = driver.findElement(By.name("mobile")).getAttribute("value");
      String workphone = driver.findElement(By.name("work")).getAttribute("value");

      driver.navigate().back();

      return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
              .withHomePhone(homephone).withMobilePhone(mobilephone).withWorkPhone(workphone);


    }


  }


