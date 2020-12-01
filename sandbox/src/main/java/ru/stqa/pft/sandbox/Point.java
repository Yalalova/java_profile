package ru.stqa.pft.sandbox;

public class Point {

  public double p1x1;
  public double p1y1;
  public double p2x2;
  public double p2y2;

  public Point(double p1x1, double p1y1, double p2x2, double p2y2) {

    this.p1x1 = p1x1;
    this.p1y1 = p1y1;
    this.p2x2 = p2x2;
    this.p2y2 = p2y2;
  }

  public double distance() {
    return Math.sqrt((this.p2y2 - this.p1y1) * (this.p2y2 - this.p1y1) + (this.p2x2 - this.p1x1) * (this.p2x2 - this.p1x1));

  }
}