package ru.stqa.pft.sandbox;

import org.junit.Test;
import org.testng.Assert;

public class PointTests {

@Test
  public void testdistance() {
    Point p = new Point(2, 2, 3, 1);
  Assert.assertEquals(p.distance(), 1.4142135623730951);
  }
}
