package cs3500.animator.model;

/**
 * This class represents a color in the RGB scale.
 */
public final class OurColor extends java.awt.Color {
  private int r;
  private int g;
  private int b;

  /**
   * Constructor for a color.
   *
   * @param r represents the red component of a color
   * @param g represents the green component of a color
   * @param b represents a blue component of a color
   * @throws IllegalArgumentException if any RGB value is invalid
   */
  public OurColor(int r, int g, int b) throws IllegalArgumentException {
    super(r, g, b);
    if (r < 0 || g < 0 || b < 0
            || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("RGB values must all be between 0 and 255.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Prints the RGB color values of this color as a string.
   *
   * @return a string of the red, green and blue components of this color
   */
  String printCol() {
    return this.r + " " + this.g + " " + this.b;
  }

  /**
   * Prints the color value as a String in the format needed in an SVG.
   *
   * @return a string of the RGB color value
   */
  public String formatSVG() {
    return "rgb(" + this.r + "," + this.g + "," + this.b + ")";
  }

  /**
   * Gives the tweened color.
   *
   * @param newcol the new color.
   * @param endCol the end color.
   * @param time   the time at which we want the color.
   * @param begin  the start time.
   * @param end    the end time.
   * @return the tweened color.
   */
  protected OurColor tweenColor(OurColor newcol, OurColor endCol, int time, int begin, int end) {
    return new OurColor(
            this.tween(time, begin, end, newcol.r, endCol.r),
            this.tween(time, begin, end, newcol.g, endCol.g),
            this.tween(time, begin, end, newcol.b, endCol.b));
  }


  /**
   * Retuns the tweened value of the component at a time or a intermediate state.
   *
   * @param time     we want the component value.
   * @param start    start time.
   * @param end      end time .
   * @param initval  intial value of the component.
   * @param finalval final value of the component.
   * @return the state of the component at a given time.
   */
  private int tween(int time, int start, int end, int initval, int finalval) {
    return (int) Math.round(initval * ((double) (end - time) / (end - start))
            + finalval * ((double) (time - start) / (end - start)));
  }
}
