package ru.stqa.pft.addressbook;


import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {

    returnToHomePage();
    findContact();
    acceptNextAlert();
    deleteContact();
    acceptDeletion();
    logout();
  }
}



