package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationTests() throws Exception {

    addNewContact();
    fillContactForm(new ContactData("test1", "test2", "test3", "test4", "test5"));
    submitContactCreation();
    returnToHomePage();
    logout();

  }


}






