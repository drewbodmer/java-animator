package cs3500.animator.model;

/**
 * Represents each frame per tick.
 */
public interface Keyframe {

  /**
   * Gets the shape at the keyframe.
   *
   * @return the shape at the keyframe.
   */
  AShape getState();

  /**
   * Gets the time of the KeyFrame in terms of ticks.
   *
   * @return the ticks.
   */
  int getTime();

  /**
   * Gets the X-coordinate of the keyframe.
   *
   * @return the x-coordinate
   */
  int getX();

  /**
   * Gets the y-coordinate of the keyframe.
   *
   * @return the y-coordinate
   */
  int getY();

  /**
   * Gets the width of the keyframe.
   *
   * @return the width of the keyframe.
   */
  int getW();

  /**
   * Gets the height of the keyframe.
   *
   * @return the height.
   */
  int getH();

  /**
   * Gets the Red value in the RGB.
   *
   * @return the RED value
   */
  int getR();

  /**
   * Gets the Green value in RGB.
   *
   * @return the Green value.
   */
  int getG();

  /**
   * Gets the Blue value in the RGB.
   *
   * @return the blue value.
   */
  int getB();

  /**
   * Returns the rotation at this keyframe.
   */
  int getRot();

  /**
   * Describes the animation in text format.
   *
   * @returnn description of the keyframe.
   */
  String getKeyInfo();

  /**
   * Gives the SVG description of the keyframe.
   *
   * @param speed    the speed at the current keyframe.
   * @param nextKF   the next keyframe.
   * @param shapeAtt the current shaoe
   * @return the SVG description of the keyframe.
   */
  String getSVG(double speed, Keyframe nextKF, String shapeAtt);

}
