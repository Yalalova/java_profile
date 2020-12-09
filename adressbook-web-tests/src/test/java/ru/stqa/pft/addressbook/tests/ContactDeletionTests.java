package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {
    app.getContactHelper().selectContact();
    app.acceptNextAlert = true;
    app.getContactHelper().clickDelete();
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }

}






