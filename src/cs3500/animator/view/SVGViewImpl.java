package cs3500.animator.view;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import cs3500.animator.model.AShape;
import cs3500.animator.model.ROAnimator;

/**
 * Represents an SVG View. This View can write the given animation (given in its constructor) to an
 * appendable (also given in constructor) in an SVG format when its beginAnimation method is
 * called.
 */
public final class SVGViewImpl implements View {
  private double speed;
  private int canvasWidth;
  private int canvasHeight;
  private ROAnimator a;
  private Appendable ap;
  private Dictionary<String, String> names = new Hashtable<>();

  /**
   * Constructor for SVGViewImpl.
   *
   * @param speed        speed of the animation.
   * @param canvasWidth  width of the canvas.
   * @param canvasHeight height of the canvas.
   * @param a            read only animator to get data.
   * @param ap           appendable to write output to.
   */
  public SVGViewImpl(double speed, int canvasWidth, int canvasHeight, ROAnimator a, Appendable ap) {
    this.speed = speed;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
    this.a = a;
    this.ap = ap;

    names.put("circle", "ellipse");
    names.put("rectangle", "rect");
    names.put("ellipse", "ellipse");
    names.put("square", "rect");
  }

  @Override
  public void beginAnimation() {
    Map<String, AShape> data = a.getFrame(a.getLastTick());
    toAppend(ap, "<svg width=\"" + this.canvasWidth + "\" height=\"" + this.canvasHeight + "\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
    for (AShape s : data.values()) {
      toAppend(ap, shapeInfo(s));

      for (int i = 0; i < s.getDirections().size() - 1; i++) {
        toAppend(ap, s.getDirections().get(i).getSVG(this.speed,
                s.getDirections().get(i + 1), names.get(s.shapeName())));
      }

      toAppend(ap, "\n\n</" + names.get(s.shapeName()) + ">");
    }
    toAppend(ap, "\n\n</svg>");
  }


  /**
   * Writes the given String to the given Appendable.
   *
   * @param ap  the appendable that will be written to.
   * @param str the string that will be written to the appendable.
   */
  private void toAppend(Appendable ap, String str) {
    try {
      ap.append(str);
    } catch (IOException e) {
      throw new IllegalStateException("IO Error while trying to append to Appendable.");
    }
  }

  /**
   * Returns the given AShape as its corresponding basic shape SVG element (as a String).
   *
   * @param s an AShape to find the data of and turn into the SVG basic shape format.
   * @return a String of the basic shape SVG element representation for the given AShape.
   */
  private String shapeInfo(AShape s) {
    AShape shape = s.getShapeState(s.getCT());
    if (names.get(shape.shapeName()).equals("rect")) {
      return "\n\n<" + names.get(s.shapeName()) + " id=\"" + shape.getName() + "\" " +
              "x=\"" + shape.getX() + "\" y=\"" + shape.getY() + "\" width=\"" + shape.getW() +
              "\" height=\"" + shape.getH() + "\" fill=\"" + shape.getC().formatSVG() +
              "\" visibility=\"visible\">\n";
    } else {
      return "\n\n<" + names.get(shape.shapeName()) + " id=\"" + shape.getName() + "\" " +
              "cx=\"" + shape.getX() + "\" cy=\"" + shape.getY() + "\" rx=\"" + shape.getW() / 2 +
              "\" ry=\"" + shape.getH() / 2 + "\" fill=\"" + shape.getC().formatSVG() +
              "\" visibility=\"visible\">\n";
    }
  }
}
