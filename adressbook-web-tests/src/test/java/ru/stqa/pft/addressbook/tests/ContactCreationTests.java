package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationTests() throws Exception {
    app.getContactHelper().createNewContact(new ContactData("test1", "test2", "test3", "test4", "test5", "test1"), true);
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();

  }


}






