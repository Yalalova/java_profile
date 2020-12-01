import ru.stqa.pft.sandbox.Point;

public class MyFirstProgram {

  public static void main(String[] args) {

    System.out.println("Hello, world !");




    Point p = new Point(2,2,3,1);
    System.out.println("Distance between points" + "P1" + "and" + "P2" + " = " + p.distance());

    double a1 = 5;
    double a2 = 2;

    System.out.println("Multiplication numbers" + "a1" + "and" + "a2" + " = " + area(a1, a2));
  }


  public static double distance(double p1x1, double p1y1, double p2x2, double p2y2) {
    return Math.sqrt((p2y2 - p1y1) * (p2y2 - p1y1) + (p2x2 - p1x1) * (p2x2 - p1x1));

  }

  public static double area(double a1, double a2) {
    return a1 * a2;
  }
}


