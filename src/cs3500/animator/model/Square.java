package cs3500.animator.model;

/**
 * Represents a Square.
 */
public final class Square extends AShape {

  /**
   * Constructor for a Square.
   *
   * @param name the unique name of a square.
   * @param xPos the x position of a square.
   * @param yPos the y position of a square.
   * @param side the side length of a square.
   * @param col  the color of the square.
   */
  public Square(String name, int xPos, int yPos, int side, OurColor col) {
    super(name, xPos, yPos, side, side, col);
  }

  public Square(String name, int xPos, int yPos, int side, OurColor col, int rot) {
    super(name, xPos, yPos, side, side, col, rot);
  }

  @Override
  public String shapeName() {
    return "square";
  }

  @Override
  public AShape copy() {
    return new Square(this.getName(), this.getX(), this.getY(), this.getW(), this.getC());
  }

}
