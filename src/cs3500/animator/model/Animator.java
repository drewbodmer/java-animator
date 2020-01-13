package cs3500.animator.model;

import java.util.Map;

/**
 * This interface represents the operations offered by a simple animator. A user can add a shape to
 * the animator, remove a shape from the animator, perform transformations onto the shapes in the
 * animator, print a description of a particular shape's actions and print the description of the
 * entire animation.
 */
public interface Animator extends ROAnimator {


  /**
   * Adds the given shape to the animation at the given time.
   *
   * @param s    the shape to be created
   * @param time the tick at which to create the shape
   * @throws IllegalArgumentException if the shape already exists in the animation or the time given
   *                                  is negative
   */
  void createShape(AShape s, int time) throws IllegalArgumentException;

  /**
   * Removes the given shape from the animation at the given time.
   *
   * @param name the name of the shape to be removed
   * @param time the tick at which the shape should be removed
   * @throws IllegalArgumentException if the shape does not exist, if the shape does not exist at
   *                                  the given time, or if the time is negative
   */
  void removeShape(String name, int time) throws IllegalArgumentException;

  /**
   * Adds the given keyframe to the given shape.
   *
   * @param name the name of the shape to add the keyframe to
   * @param kf   the keyframe to be added
   */
  void addKF(String name, Keyframe kf);

  /**
   * Gets the map of shapes from the animator.
   *
   * @return Map String, AShape of all the shapes in the model.
   **/
  Map<String, AShape> getShapes();

}
