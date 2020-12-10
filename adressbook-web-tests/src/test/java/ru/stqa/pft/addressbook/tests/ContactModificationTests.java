package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {




  @Test
  public void testContactModificationTests() throws Exception {
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("firstname", "lastname", "address",  "mobile", "email"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
