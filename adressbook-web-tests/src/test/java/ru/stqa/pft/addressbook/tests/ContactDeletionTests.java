package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {

      if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createNewContact(new ContactData("test1", "test2", "test3", "test4", "test5", "test1"), true);
    }
      app.getContactHelper().selectContact();
      app.acceptNextAlert = true;
      app.getContactHelper().clickDelete();
      assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
      app.getNavigationHelper().returnToHomePage();
      app.getSessionHelper().logout();
    }

  }






