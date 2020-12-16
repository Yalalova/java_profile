package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {
     int before = app.getContactHelper().getContactCount();
      if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createNewContact(new ContactData("test1", "test2", "test3", "test4", "test5", "test1"), true);
    }
      app.getContactHelper().selectContact(before - 1);
      app.acceptNextAlert = true;
      app.getContactHelper().clickDelete();
      app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$");
      app.getNavigationHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before - 1);
      app.getSessionHelper().logout();
    }

  }






