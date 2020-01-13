package cs3500.animator.model;

/**
 * Represents a shape Rectangle.
 */
public final class Rectangle extends AShape {

  /**
   * Constructor for a Rectangle.
   *
   * @param name   the unique name of this rectangle
   * @param xPos   the x position of this rectangle
   * @param yPos   the y position of the rectangle
   * @param width  the width of the rectangle
   * @param height the height of the rectangle
   * @param col    the color of the rectangle
   */
  public Rectangle(String name, int xPos, int yPos, int width, int height, OurColor col) {
    super(name, xPos, yPos, width, height, col);
  }

  public Rectangle(String name, int xPos, int yPos, int width, int height, OurColor col, int rot) {
    super(name, xPos, yPos, width, height, col, rot);
  }

  @Override
  public String shapeName() {
    return "rectangle";
  }

  @Override
  public AShape copy() {
    return new Rectangle(this.getName(),
            this.getX(), this.getY(), this.getW(), this.getH(), this.getC());
  }


}

