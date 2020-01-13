package cs3500.animator.model;

import java.util.Map;


/**
 * Represents a Read-Only Animator. Contains operations that allow us to get the data of a shape at
 * a given time, get the data of all the shapes in the animation, get a map of all the shapes in the
 * animation at a given time, and get the last tick of the animation. This interface does not allow
 * any operations that will mutate an Animator.
 */
public interface ROAnimator {

  /**
   * Returns a text description of a given shape for the whole duration it is manipulated for.
   *
   * @param name the name of the shape whose information is being returned
   * @return a description of the shape throughout the animation as a String.
   * @throws IllegalArgumentException if the given AShape does not exist in this Animator
   */
  String printSInfo(String name) throws IllegalArgumentException;

  /**
   * Returns a text description for the entire animation.
   *
   * @return a verbose description of the entire animation as a String.
   */
  String printDesc();

  /**
   * Returns the shapes, and everything applied to those shapes, inside this animation at the given
   * time.
   *
   * @param time represents the particular time we want to see the animation up until
   * @return hashmap of shapes within the animation.
   */
  Map<String, AShape> getFrame(int time);


  /**
   * Gets the last tick of the model.
   *
   * @return the last tick of the model.
   */
  int getLastTick();

}
