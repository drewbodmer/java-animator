package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Map;

import cs3500.animator.controller.ListSelectListener;
import cs3500.animator.model.AShape;
import cs3500.animator.model.Keyframe;

/**
 * Represents a composite view of the visual view with an UI editor. Contains functionality to
 * pause, resume, restart, enable/disable looping for and increase/decrease the speed of the
 * animation. Also contains functionality for creating dialog boxes.
 */
public interface CompositeView extends View {

  /**
   * Adds an actionlistener to the View.
   *
   * @param actionListener The actionlistener to be added.
   */
  void addActionListener(ActionListener actionListener);

  /**
   * Adds an itemlistener.
   *
   * @param itemListener The given itemlistener to be added.
   */
  void addItemListener(ItemListener itemListener);

  /**
   * Adds a listselectlistener.
   *
   * @param listSelectListener the listseleectlistener to be added.
   */
  void addListSelectionListener(ListSelectListener listSelectListener);

  /**
   * Pauses the animation.
   */
  void pauseAnimation();

  /**
   * Resumes the animation.
   */
  void resumeAnimation();

  /**
   * Restarts the animation.
   */
  void restartAnimation();

  /**
   * Allows the user to enable looping.
   */
  void enableLoop();

  /**
   * Allows the user to disable looping.
   */
  void disableLoop();

  /**
   * Increases the speed at which the animation runs.
   */
  void increaseSpeed();

  /**
   * Decreases the speed at which the animation runs.
   */
  void decreaseSpeed();

  /**
   * Creates a dialog box that takes in the input needed to add a shape to the animation.
   */
  void addShapeDialog();

  /**
   * Gets the user's inputted time to create a shape (given inside the add shape dialog box).
   *
   * @return The time at which the shape was created.
   */
  int getAddShapeTimeInput();

  /**
   * Returns the shape selected by the user in the UI.
   *
   * @return the shape to be edited.
   */
  AShape getShapeToEdit();

  /**
   * Returns a new Shape object that the user wants to add, created with the parameters specified by
   * the user.
   *
   * @return an AShape that the user wants to add to the animation.
   */
  AShape getNewShapeInput();

  /**
   * Returns a new Keyframe object that the user wants to add, created with the parameters specified
   * by the user.
   *
   * @return a Keyframe that the user wants to add to the animation.
   */
  Keyframe getKFToAdd();

  /**
   * Returns the edited version of a selected Keyframe, whose fields are specified by the user
   * input.
   *
   * @return the edited version of the selected Keyframe.
   */
  Keyframe getEditedKF();

  /**
   * Gets the keyframe that the user has selected to edit.
   *
   * @return a Keyframe for the user to edit.
   */
  Keyframe getKFToEdit();

  /**
   * Updates the Jlist in the UI.
   */
  void updateLists();

  /**
   * Finds the keyframes of a given shape and adds it to the keyframes JList in the UI to be visible
   * to the user).
   *
   * @param s The specified shape whose keyframes are needed.
   */
  void findKeyframes(AShape s);

  /**
   * Creates a dialog box in the UI that takes inputs needed to add a Keyframe.
   */
  void addKeyframeDialog();

  /**
   * Creates a dialog box in the UI that takes in inputs needed to edit a Keyframe.
   */
  void editKeyframeDialog();

  /**
   * Modifies the map of shapes to be converted into a map that indicates the layer of all shapes.
   */
  Map<Integer, Map<String, AShape>> convertToLayers();

  Integer getLayerToEdit();

  void findShapes(Integer layer);
}
