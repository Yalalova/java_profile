package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {




  @Test
  public void testContactModificationTests() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createNewContact(new ContactData("test1", "test2", "test3", "test4", "test5", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("test1","test2","test3","test4", "test5", "test1"), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
