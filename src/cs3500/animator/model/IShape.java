package cs3500.animator.model;

import java.util.List;

/**
 * Represents a shape, and all the operations you can do to a shape. You can move the position of
 * the shape, resize the shape, recolor the shape, and return the information of a shape at a given
 * tick.
 */
public interface IShape {

  /**
   * Gets the list of directions of this AShape.
   *
   * @return the list of directions of this AShape
   */
  List<Keyframe> getDirections();

  /**
   * Gets the layer of this shape.
   *
   * @return an integer representing the layer of the shape.
   */
  int getLayer();

  /**
   * Adds the given transformation to the list of transformations being done on this AShape.
   *
   * @param t the keyframe to be added to this shape
   * @throws IllegalArgumentException if Keyframe being added is at the same time as another
   *                                  Keyframe.
   */
  void addToDir(Keyframe t) throws IllegalArgumentException;

  /**
   * Returns the type of shape that it is as a String.
   *
   * @return the type of shape as a String
   */
  String shapeName();

  /**
   * Creates the same shape as a new object.
   *
   * @return a copy of the shape it's called on
   */
  AShape copy();

  /**
   * Returns a description of this AShape for as long as it is in the animation.
   *
   * @return the description of a shape in the animation as a String
   */
  String getInfo();

  /**
   * Returns a description of this AShape at a given tick.
   *
   * @param tick the tick at which we want to get the shape info of
   * @return the description of a shape at the given tick as a String
   */
  String shapeInfo(int tick);

  /**
   * Gets the shape at the particular time.
   *
   * @param time the time want the shape.
   * @return the shape at the time.
   */
  AShape getShapeState(int time);

  /**
   * Gets the unique name of the shape.
   *
   * @return the shape's unique name.
   */
  String getName();

  /**
   * gets the total time of the shape.
   *
   * @return the max time of the shape.
   */
  int getMaxTick();

  /**
   * Gets thge x-coordinate of the shape.
   *
   * @return the x-coordinate of the shape.
   */
  int getX();

  /**
   * Gets the y-coordinate of the shape.
   *
   * @return y coordinate of the shape.
   */
  int getY();

  /**
   * Gets the width of the shape .
   *
   * @return the width of the shape.
   */
  int getW();

  /**
   * gets the height of the shape .
   *
   * @return the height of the shape.
   **/
  int getH();

  /**
   * The color of shape.
   * @return color of the shape.
   */

  /**
   * gets the color of the shape.
   */
  OurColor getC();

  /**
   * gets the created time of the shape.
   *
   * @return the created time of the shape.
   */
  int getCT();

  /**
   * sets the created time of the shape.
   *
   * @param t sets the created time.
   */
  void setCT(int t);

  /**
   * gets the rotation of this shape in degrees.
   *
   * @return the rotation of this shape in degrees.
   */
  int getRot();

  /**
   * Adds the given layer to this shape.
   */
  AShape addLayer(int layer);

  /**
   * removes the KeyFrame from the listofKeyframes.
   *
   * @param key the keyframe to be removed.
   */
  void removeKeyframe(Keyframe key);

}
