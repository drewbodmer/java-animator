package cs3500.animator.controller;

import java.awt.event.ItemEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import cs3500.animator.model.AShape;
import cs3500.animator.model.Animator;
import cs3500.animator.model.Keyframe;
import cs3500.animator.view.CompositeView;


/**
 * Controller represents a controller for the animation. It allows the user to use buttons to
 * control the animation, including play, pause,restart, add and remove keyframes and shapes, and
 * enable/disable looping.
 */
public class Controller implements IController {
  private Animator model;
  private CompositeView view;

  /**
   * Constructor for a Controller.
   *
   * @param m model
   * @param v view
   */
  public Controller(Animator m, CompositeView v) {
    this.model = m;
    this.view = v;
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new LinkedHashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Play Button", () -> view.beginAnimation());
    buttonClickedMap.put("Pause Button", () -> view.pauseAnimation());
    buttonClickedMap.put("Resume Button", () -> view.resumeAnimation());
    buttonClickedMap.put("Restart Button", () -> view.restartAnimation());
    buttonClickedMap.put("beginAnimation faster", () -> view.increaseSpeed());
    buttonClickedMap.put("beginAnimation slower", () -> view.decreaseSpeed());

    buttonClickedMap.put("Add shape", () -> {
      view.addShapeDialog();

      AShape newShape = view.getNewShapeInput();
      int time = view.getAddShapeTimeInput();
      model.createShape(newShape, time);
      view.updateLists();
    });

    buttonClickedMap.put("Remove shape", () -> {
      AShape shape = view.getShapeToEdit();

      if (shape != null) {
        model.removeShape(shape.getName(), shape.getCT());
        view.updateLists();
      }
    });

    buttonClickedMap.put("remove layer", () -> {
      Map<Integer, Map<String, AShape>> shapes = view.convertToLayers();
      for (AShape s : shapes.get(view.getLayerToEdit()).values()) {
        model.removeShape(s.getName(), s.getCT());
      }
      view.updateLists();
    });

    buttonClickedMap.put("Add keyframe", new AddKeyframeButton());
    buttonClickedMap.put("Remove keyframe", new RemoveKeyframeButton());
    buttonClickedMap.put("Edit keyframe", new EditKeyframeButton());

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  private void configureItemListener() {
    Map<Integer, Runnable> checkedBoxMap = new LinkedHashMap<>();
    CheckBoxListener checkBoxListener = new CheckBoxListener();

    checkedBoxMap.put(ItemEvent.SELECTED, () -> view.enableLoop());
    checkedBoxMap.put(ItemEvent.DESELECTED, () -> view.disableLoop());

    checkBoxListener.setCheckboxActionsMap(checkedBoxMap);
    this.view.addItemListener(checkBoxListener);
  }

  private void configureListSelectListener() {
    ListSelectListener listSelectListener = new ListSelectListener();
    listSelectListener.setListActions(new ShowKeyframesAction(), () ->
            view.findShapes(view.getLayerToEdit()));

    this.view.addListSelectionListener(listSelectListener);
  }

  @Override
  public void runAnimation() {
    configureButtonListener();
    configureItemListener();
    configureListSelectListener();
  }

  class AddKeyframeButton implements Runnable {
    public void run() {
      AShape shape = view.getShapeToEdit();
      view.addKeyframeDialog();

      Keyframe newKF = view.getKFToAdd();

      model.addKF(shape.getName(), newKF);
      view.updateLists();
    }
  }

  class RemoveKeyframeButton implements Runnable {
    public void run() {
      AShape shape = view.getShapeToEdit();
      Keyframe kf = null;
      if (shape != null) {
        kf = view.getKFToEdit();
      }

      if (shape != null && kf != null) {
        for (AShape s : model.getShapes().values()) {
          s.removeKeyframe(kf);
          view.updateLists();
        }
      }
    }
  }

  class ShowKeyframesAction implements Runnable {
    public void run() {
      AShape shape = view.getShapeToEdit();
      if (shape != null) {
        view.findKeyframes(shape);
      }
    }
  }

  class EditKeyframeButton implements Runnable {
    public void run() {

      view.editKeyframeDialog();

      Keyframe kf = view.getEditedKF();
      AShape shape = view.getShapeToEdit();

      if (kf != null && shape != null) {
        shape.addToDir(kf);
      }
    }
  }
}
