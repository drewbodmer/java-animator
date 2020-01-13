//import org.junit.Test;


//import cs3500.animator.model.Oval;
//import cs3500.animator.view.CustomJPanel;
//import cs3500.animator.view.SVGViewImpl;
//import cs3500.animator.view.VisualViewImpl;
//import cs3500.animator.model.AShape;
//import cs3500.animator.model.Animator;
//import cs3500.animator.model.AnimatorImpl;
//import cs3500.animator.model.Circle;
//import cs3500.animator.model.OurColor;
//import cs3500.animator.model.ROAnimator;
//import cs3500.animator.model.Rectangle;
//import cs3500.animator.model.Square;
//
//import static junit.framework.TestCase.assertEquals;

public class ViewTests {
  /*
  OurColor c = new OurColor(5,5, 5);
  Animator a = new AnimatorImpl();
  AShape sqr = new Square("sqr", 10, 10, 10, c);
  AShape sqr1 = new Square("sqr1", 11, 11, 8, c);
  AShape circle = new Circle("circle", 10,10,10, c);
  AShape circle1 = new Circle("circle1", 11,11,10, c);
  AShape rect = new Rectangle("rect", 1,10, 5,5, c);
  AShape rect1 = new Rectangle("rect1", 1,10, 7,7, c);
  AShape oval = new Oval("oval", 50,50,50,20,
          new OurColor(100,0,0));
  ROAnimator roa = a;
  CustomJPanel panel = new CustomJPanel();
  View vv = new VisualViewImpl(roa, 5, 100, 100);


  View v = new VisualViewImpl(a, 2, 100, 100);
  Transformations move = new Move(0,2,12,12);
  Transformations resize = new Resize(0,2,7,7);

  @Test
  public void testGetState() {
    a.createShape(sqr, 0);
    a.transform(move,"sqr");
    a.transform(resize, "sqr");
    assertEquals("1 11 11 9 9 5 5 5", sqr.getShapeState(1).shapeInfo(1));

  }

  @Test
  public void testGetState1() {
    a.createShape(sqr, 0);
    a.createShape(circle, 0);
    a.createShape(rect, 0);
    a.transform(move, "circle");
    assertEquals("1 11 11 20 20 5 5 5", circle.getShapeState(1).shapeInfo(1));

  }

  @Test
  public void testSVGViewCreateShape() {
    StringBuffer out = new StringBuffer();
    Animator a = new AnimatorImpl();
    a.createShape(sqr, 1);
    a.createShape(circle, 3);
    View svgView = new SVGViewImpl(1, 500, 700, a, out);
    svgView.outputSVG();
    assertEquals("<svg width=\"500\" height=\"700\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"sqr\" x=\"10\" y=\"10\" width=\"10\" height=\"10\" " +
            "fill=\"rgb(5,5,5)\" visibility=\"visible\">\n" +
            "\n" +
            "\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"circle\" cx=\"10\" cy=\"10\" rx=\"20\" ry=\"20\" " +
            "fill=\"rgb(5,5,5)\" visibility=\"visible\">\n" +
            "\n" +
            "\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>", out.toString());
  }

  @Test
  public void testSVGViewMoveRect() {
    StringBuffer out = new StringBuffer();
    Animator a = new AnimatorImpl();
    a.createShape(sqr, 1);
    a.transform(new Move(1,5,15,10), "sqr");
    View svgView = new SVGViewImpl(1, 500, 700, a, out);
    svgView.outputSVG();
    assertEquals("<svg width=\"500\" height=\"700\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n" +
            "<rect id=\"sqr\" x=\"10\" y=\"10\" width=\"10\" height=\"10\" " +
            "fill=\"rgb(5,5,5)\" visibility=\"visible\">\n\n" +
            "\t<animate attributeType=\"xml\" begin=\"100ms\" dur=\"400ms\" " +
            "attributeName=\"x\" from=\"10\" to=\"15\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"100ms\" dur=\"400ms\" " +
            "attributeName=\"y\" from=\"10\" to=\"10\" fill=\"freeze\" />\n\n" +
            "</rect>\n\n" + "</svg>", out.toString());
  }

  @Test
  public void testSVGViewMoveEllipse() {
    StringBuffer out = new StringBuffer();
    Animator a = new AnimatorImpl();
    a.createShape(circle, 1);
    a.transform(new Move(1,5,20,10), "circle");
    View svgView = new SVGViewImpl(1, 500, 700, a, out);
    svgView.outputSVG();
    assertEquals("<svg width=\"500\" height=\"700\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n" +
            "<ellipse id=\"circle\" cx=\"10\" cy=\"10\" rx=\"20\" ry=\"20\" " +
            "fill=\"rgb(5,5,5)\" visibility=\"visible\">\n\n" +
            "\t<animate attributeType=\"xml\" begin=\"100ms\" dur=\"400ms\" " +
            "attributeName=\"cx\" from=\"10\" to=\"20\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"100ms\" dur=\"400ms\" " +
            "attributeName=\"cy\" from=\"10\" to=\"10\" fill=\"freeze\" />\n\n" +
            "</ellipse>\n\n" + "</svg>", out.toString());
  }

  @Test
  public void testSVGViewRecolor() {
    StringBuffer out = new StringBuffer();
    Animator a = new AnimatorImpl();
    a.createShape(circle, 1);
    a.transform(new Recolor(1,5,new OurColor(200,200,200)), "circle");
    View svgView = new SVGViewImpl(1, 500, 700, a, out);
    svgView.outputSVG();
    assertEquals("<svg width=\"500\" height=\"700\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n" +
            "<ellipse id=\"circle\" cx=\"10\" cy=\"10\" rx=\"20\" ry=\"20\" " +
            "fill=\"rgb(5,5,5)\" visibility=\"visible\">\n\n" +
            "\t<animate attributeType=\"xml\" begin=\"100ms\" dur=\"400ms\" " +
            "attributeName=\"fill\" from=\"rgb(5,5,5)\" to=\"rgb(200,200,200)\" fill=\"freeze\" />"
            + "\n\n</ellipse>\n\n" + "</svg>", out.toString());
  }*/
}
