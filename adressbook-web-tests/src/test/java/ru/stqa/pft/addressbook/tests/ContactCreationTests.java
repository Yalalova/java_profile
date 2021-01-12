package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void applyPreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().returnToHomePage();
  }



  @Test
  public void testContactCreationTests() throws Exception {
    app.goTo().returnToHomePage();
    Contacts before = app.contact().all();
    ContactData contact =
            new ContactData().withFirstname("firstname").withLastname("lastname");
    app.contact().createNewContact(contact, true);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}









