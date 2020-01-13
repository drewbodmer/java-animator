package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an abstract class for a shape. It abstracts out all the common fields and
 * methods that are the same for all shapes.
 */
public abstract class AShape implements IShape {
  private String name;
  private int width;
  private int height;
  private int xPos;
  private int yPos;
  private OurColor col;
  private int createdTime;
  private List<Keyframe> directions;
  private int rotation;
  private int layer;

  /**
   * Constructor for an abstract AShape.
   *
   * @param name   the unique name of this shape
   * @param xPos   the x position of this shape
   * @param yPos   the y position of this shape
   * @param width  the width of this shape
   * @param height the height of this shape
   * @param col    the color of this shape
   * @throws IllegalArgumentException if width or height is zero or negative.
   */
  public AShape(String name, int xPos, int yPos, int width, int height, OurColor col)
          throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid shape size: w:" + width + "h: " + height);
    }
    this.name = name;
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
    this.col = col;
    directions = new ArrayList<>();
  }

  /**
   * Constructor for an abstract AShape with rotation.
   *
   * @param name   the unique name of this shape
   * @param xPos   the x position of this shape
   * @param yPos   the y position of this shape
   * @param width  the width of this shape
   * @param height the height of this shape
   * @param col    the color of this shape
   * @param rot    the rotation of this shape
   * @throws IllegalArgumentException if width or height is zero or negative.
   */
  public AShape(String name, int xPos, int yPos, int width, int height,
                OurColor col, int rot) throws IllegalArgumentException {
    this(name, xPos, yPos, width, height, col);
    this.rotation = rot;
  }

  @Override
  public AShape addLayer(int layer) {
    this.layer = layer;
    return this;
  }

  @Override
  public int getLayer() {
    return this.layer;
  }

  @Override
  public List<Keyframe> getDirections() {
    return directions;
  }

  @Override
  public void addToDir(Keyframe kf) throws IllegalArgumentException {
    boolean found = false;
    if (this.directions.isEmpty()) {
      this.directions.add(kf);
    } else {
      for (int i = 0; i < this.directions.size(); i++) {
        if (this.directions.get(i).getTime() > kf.getTime()) {
          this.directions.add(i, kf);
          found = true;
          break;
        } else if (this.directions.get(i).getTime() == kf.getTime()) {
          this.directions.set(i, kf);
          found = true;
          break;
        }
      }

      if (!found) {
        this.directions.add(kf);
      }
    }
  }

  @Override
  public abstract String shapeName();


  @Override
  public String getInfo() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < directions.size() - 1; i++) {
      result.append("motion ").append(this.getName()).append(directions.get(i).getKeyInfo())
              .append(" ").append(directions.get(i + 1).getKeyInfo()).append("\n");
    }
    return result.toString();
  }

  @Override
  public String shapeInfo(int tick) {
    return this.name + " " + tick + " " + this.xPos + " " + this.yPos + " " + this.width + " "
            + this.height + " " + col.printCol();
  }

  @Override
  public AShape getShapeState(int time) {
    Keyframe initialkf;
    Keyframe finalkf;

    for (int i = 0; i < this.directions.size() - 1; i++) {
      initialkf = this.directions.get(i);
      finalkf = this.directions.get(i + 1);
      if (initialkf.getTime() < time && finalkf.getTime() > time) {
        this.currshape(time, initialkf, finalkf);

      }
    }
    return this;
  }

  @Override
  public void removeKeyframe(Keyframe key) {
    Keyframe dummy = null;

    for (Keyframe keyframe : directions) {
      if (keyframe.equals(key)) {
        dummy = key;
      }
    }

    if (dummy != null) {
      directions.remove(dummy);
    }
  }

  @Override
  public int getMaxTick() {
    int max = 0;
    for (Keyframe k : directions) {
      max = Math.max(max, k.getTime());
    }
    return max;
  }

  @Override
  public String getName() {
    String holder = this.name;
    return holder;
  }

  @Override
  public int getX() {
    int holder = this.xPos;
    return holder;
  }

  @Override
  public int getY() {
    int holder = this.yPos;
    return holder;
  }


  @Override
  public int getW() {
    int holder = this.width;
    return holder;
  }

  @Override
  public int getH() {
    int holder = this.height;
    return holder;
  }

  @Override
  public OurColor getC() {
    OurColor holder = this.col;
    return holder;
  }

  @Override
  public int getCT() {
    return this.createdTime;
  }

  @Override
  public void setCT(int t) {
    this.createdTime = t;
  }

  @Override
  public int getRot() {
    return this.rotation;
  }

  /**
   * Returns the tweened value of the component at a time or a intermediate state.
   *
   * @param time     we want the component value.
   * @param initval  intial value of the component.
   * @param finalval final value of the component.
   */
  private void currshape(int time, Keyframe initval, Keyframe finalval) {
    AShape initshape = initval.getState();
    AShape finalshape = finalval.getState();
    int xval = this.tween(time, initval.getTime(), finalval.getTime(),
            initshape.xPos, finalshape.xPos);
    int yval = this.tween(time, initval.getTime(), finalval.getTime(),
            initshape.yPos, finalshape.yPos);
    this.xPos = xval;
    this.yPos = yval;

    OurColor holder = this.col.tweenColor(initshape.col, finalshape.col, time,
            initval.getTime(), finalval.getTime());
    this.col = holder;

    int width = this.tween(time, initval.getTime(), finalval.getTime(),
            initshape.width, finalshape.width);
    int height = this.tween(time, initval.getTime(), finalval.getTime(),
            initshape.height, finalshape.height);
    this.width = width;
    this.height = height;

    int rot = this.tween(time, initval.getTime(), finalval.getTime(),
            initval.getRot(), finalval.getRot());
    this.rotation = rot;
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
  protected int tween(int time, int start, int end, int initval, int finalval) {
    return (int) Math.round(initval * ((double) (end - time) / (end - start)) + finalval * ((double)
            (time - start) / (end - start)));
  }


}
