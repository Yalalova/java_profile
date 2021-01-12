package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests1  extends TestBase {
  @Test


  public void TestContactPhoneTest1() {

    app.goTo().returnToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));


  }

  private String mergePhones(ContactData contact) {
   return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(),contact.getWorkPhone())

            .stream().filter((s) -> ! s.equals(""))
           .map(ContactPhoneTests1::cleaned)
           .collect(Collectors.joining("\n"));


  }


  public static String cleaned (String phone){
  return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}

