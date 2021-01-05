package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {

      if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createNewContact(new ContactData(0,"test1", "test2", null, null, null, "test1_group"),true);
    }
     List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size() - 1);
      app.acceptNextAlert = true;
      app.getContactHelper().clickDelete();
      app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$");
      app.goTo().returnToHomePage();
      List<ContactData> after =app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(),before.size() - 1);
      before.remove(before.size() - 1);
      Assert.assertEquals(before,after);
      app.getSessionHelper().logout();
    }

  }






