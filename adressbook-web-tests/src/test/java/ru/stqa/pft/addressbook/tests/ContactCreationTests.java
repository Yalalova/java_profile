package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {





  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c)-> new Object[] {c}).collect(Collectors.toList()).iterator();
  }






  @Test (dataProvider = "validContacts")
  public void testContactCreationTests(ContactData contact)  {
    app.goTo().returnToHomePage();
    Contacts before = app.contact().all();
    app.contact().createNewContact(contact, true);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}









