package cs3500.animator.model;

import java.util.Objects;

public class KeyframeImpl implements Keyframe {
  private int time;
  private int x;
  private int y;
  private int w;
  private int h;
  private int r;
  private int g;
  private int b;
  private int rot;

  /**
   * Represents a Keyframe, all the data of a shape at a specific time.
   *
   * @param time The time at which the keyframe exists.
   * @param x    The x coordinate of this keyframe.
   * @param y    The y coordinate of this keyframe.
   * @param w    The width of this keyframe.
   * @param h    The height of this keyframe.
   * @param r    The red value of this keyframe.
   * @param g    The green value of this keyframe.
   * @param b    The blue value this keyframe.
   */
  public KeyframeImpl(int time, int x, int y, int w, int h, int r, int g, int b) {
    this.time = time;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public KeyframeImpl(int time, int x, int y, int w, int h, int r, int g, int b, int rot) {
    this(time, x, y, w, h, r, g, b);
    this.rot = rot;
  }


  @Override
  public AShape getState() {
    AShape s = new Rectangle("holder", x, y, w, h, new OurColor(r, g, b));
    return s;
  }

  @Override
  public int getTime() {
    int holder = this.time;
    return holder;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getW() {
    return w;
  }

  @Override
  public int getH() {
    return h;
  }

  @Override
  public int getR() {
    return r;
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public int getB() {
    return b;
  }

  @Override
  public int getRot() {
    return this.rot;
  }

  ;

  @Override
  public String getKeyInfo() {
    return " " + time + " " + x + " " + y + " " + w + " " + h + " " + r + " " + g + " " + b;
  }

  @Override
  public String getSVG(double speed, Keyframe nextKF, String shapeAtt) {
    StringBuilder result = new StringBuilder();
    double dur = nextKF.getTime() - this.getTime();

    if (shapeAtt.equalsIgnoreCase("rect")) {
      if (this.getX() != nextKF.getX()) {
        this.svgTemp(result, speed, dur, "x",
                Integer.toString(this.getX()), Integer.toString(nextKF.getX()));
      }
      if (this.getY() != nextKF.getY()) {
        this.svgTemp(result, speed, dur, "y",
                Integer.toString(this.getY()), Integer.toString(nextKF.getY()));
      }
      if (this.getW() != nextKF.getW()) {
        this.svgTemp(result, speed, dur, "width",
                Integer.toString(this.getW()), Integer.toString(nextKF.getW()));
      }
      if (this.getH() != nextKF.getH()) {
        this.svgTemp(result, speed, dur, "height",
                Integer.toString(this.getH()), Integer.toString(nextKF.getH()));
      }
      if (this.getR() != nextKF.getR() || this.getG() != nextKF.getG() ||
              this.getB() != nextKF.getB()) {
        this.svgTemp(result, speed, dur, "fill",
                new OurColor(this.getR(), this.getG(), this.getB()).formatSVG(),
                new OurColor(nextKF.getR(), nextKF.getG(), nextKF.getB()).formatSVG());
      }
    } else {
      if (this.getX() != nextKF.getX()) {
        this.svgTemp(result, speed, dur, "cx",
                Integer.toString(this.getX()), Integer.toString(nextKF.getX()));
      }
      if (this.getY() != nextKF.getY()) {
        this.svgTemp(result, speed, dur, "cy",
                Integer.toString(this.getY()), Integer.toString(nextKF.getY()));
      }
      if (this.getW() != nextKF.getW()) {
        this.svgTemp(result, speed, dur, "rx",
                Integer.toString(this.getW() / 2), Integer.toString(nextKF.getW() / 2));
      }
      if (this.getH() != nextKF.getH()) {
        this.svgTemp(result, speed, dur, "ry",
                Integer.toString(this.getH() / 2), Integer.toString(nextKF.getH() / 2));
      }
      if (this.getR() != nextKF.getR() || this.getG() != nextKF.getG() ||
              this.getB() != nextKF.getB()) {
        this.svgTemp(result, speed, dur, "fill",
                new OurColor(this.getR(), this.getG(), this.getB()).formatSVG(),
                new OurColor(nextKF.getR(), nextKF.getG(), nextKF.getB()).formatSVG());
      }
    }
    if (this.getRot() != nextKF.getRot()) {
      result.append("\n\t<animateTransform attributeName=\"transform\" type=\"rotate\" begin=\"" +
              (10 * speed * this.time) + "ms\" dur=\"" + (10 * speed * dur) + "ms\" from=\""
              + this.getRot() + " " + (this.getX() + (this.getW() / 2)) + " " + (this.getY() +
              (this.getH() / 2)) + "\" to=\"" + nextKF.getRot() + " " + (nextKF.getX() +
              (this.getW() / 2)) + " " + (nextKF.getY() + (nextKF.getH() / 2)) + "\" />");
    }
    return result.toString();
  }

  private void svgTemp(StringBuilder sb, double speed, double dur, String att, String from,
                       String to) {
    sb.append("\n\t<animate attributeType=\"xml\" begin=\"").append(10 * speed * this.time)
            .append("ms\" dur=\"").append(10 * speed * dur).append("ms\" attributeName=\"")
            .append(att).append("\" from=\"").append(from).append("\" to=\"").append(to)
            .append("\" fill=\"freeze\" />");
  }

  /**
   * Defines equality for Keyframes.
   *
   * @param other an Object to check equal.
   * @return A boolean representing whether the two objects are equal.
   */
  public boolean equals(Object other) {
    if (!(other instanceof Keyframe)) {
      return false;
    }
    // this cast is safe, because we just checked instanceof
    Keyframe that = (Keyframe) other;


    return this.time == that.getTime() && this.x == that.getX() && this.y == that.getY()
            && this.w == that.getW() && this.h == that.getH() && this.r == that.getR()
            && this.g == that.getG() && this.b == that.getB() && this.rot == that.getRot();
  }


  public int hashCode() {
    return Objects.hash(this.getTime(), this.getX(), this.getY(), this.getW(),
            this.getH(), this.getR(), this.getG(), this.getB(), this.getRot());
  }

}
