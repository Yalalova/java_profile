package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void applyPreconditions() {
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1_group").withHeader("test1_group").withFooter("test1_group"));
      }
      app.contact().createNewContact
              (new ContactData().withFirstname("firstname").withLastname("lastname").withGroup("test1_group"),
                      true);
    }
    app.goTo().returnToHomePage();

  }


  @Test
  public void testContactModificationTests() throws Exception {

      Contacts before = app.contact().all();
      int index = before.size();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname(
              modifiedContact.getFirstname()).withLastname(
              modifiedContact.getLastname());
      app.contact().modifyContact(modifiedContact);
      Contacts after = app.contact().all();
      Assert.assertEquals(after.size(), before.size());


      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }

      }

