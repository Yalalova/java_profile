package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver driver) {
  super(driver);
  }

  public void groupPage() {
       click(By.linkText("groups"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void returntoGroupPage() {
    click(By.linkText("group page"));
  }

    public void confirmAction() {
  driver.switchTo().alert().accept();
}

}