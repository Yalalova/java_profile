package ru.stqa.pft.addressbook;


import org.testng.annotations.*;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreationTests() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test1", "test1"));
    submitGroupCreation();
    returntoGroupPage();
    logout();
  }

}
