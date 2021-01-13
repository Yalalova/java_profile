package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void applyPreconditions() {
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
      }
      app.contact().createNewContact
              (new ContactData().withFirstname("firstname").withLastname("lastname").withGroup("test1"),
                      true);
    }
    app.goTo().returnToHomePage();
  }


  @Test
  public void testContactDeletionTests() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().removeContact(deletedContact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedContact)));


  }
}






