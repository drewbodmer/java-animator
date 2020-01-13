import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Map;

import cs3500.animator.controller.ListSelectListener;
import cs3500.animator.model.AShape;
import cs3500.animator.model.Keyframe;
import cs3500.animator.view.CompositeView;

public class Dummy implements CompositeView {
  Appendable ap;

  public Dummy() {
    this.ap = new StringBuilder();
  }

  @Override
  public void beginAnimation() {
    appendStuff("beginAnimation");
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    appendStuff("addActionListener");

  }

  @Override
  public void addItemListener(ItemListener itemListener) {
    appendStuff("addItemListener");

  }

  @Override
  public void addListSelectionListener(ListSelectListener listSelectListener) {
    appendStuff("addListSelectionListener");

  }

  @Override
  public void pauseAnimation() {
    appendStuff("pauseAnimation");

  }

  @Override
  public void resumeAnimation() {
    appendStuff("resumeAnimation");

  }

  @Override
  public void restartAnimation() {
    appendStuff("restartAnimation");
  }

  @Override
  public void enableLoop() {
    appendStuff("enableLoop");
  }

  @Override
  public void disableLoop() {
    appendStuff("disableLoop");
  }

  @Override
  public void increaseSpeed() {
    appendStuff("increaseSpeed");
  }

  @Override
  public void decreaseSpeed() {
    appendStuff("decreaseSpeed");
  }

  /**
   * Creates a dialog box that takes in the input needed to add a shape to the animation.
   */
  @Override
  public void addShapeDialog() {
    appendStuff("addShapeDialog");
  }

  @Override
  public int getAddShapeTimeInput() {
    appendStuff("getAddShapeTimeInput");
    return 0;
  }

  @Override
  public AShape getShapeToEdit() {
    appendStuff("getShapeToEdit");
    return null;
  }

  @Override
  public AShape getNewShapeInput() {
    appendStuff("getNewShapeInput");
    return null;
  }

  @Override
  public Keyframe getKFToAdd() {
    appendStuff("getKFToAdd");
    return null;
  }

  @Override
  public Keyframe getEditedKF() {
    appendStuff("getEditedKF");
    return null;
  }

  @Override
  public Keyframe getKFToEdit() {
    appendStuff("getKFtoEdit");
    return null;
  }

  @Override
  public void updateLists() {
    appendStuff("updatelists");
  }

  @Override
  public void findKeyframes(AShape s) {
    appendStuff("findkeyframe");
  }

  @Override
  public void addKeyframeDialog() {
    appendStuff("addKeyframeDialogue");
  }

  @Override
  public void editKeyframeDialog() {
    appendStuff("editKeyframeDialogue");
  }

  @Override
  public Map<Integer, Map<String, AShape>> convertToLayers() {
    return null;
  }

  @Override
  public Integer getLayerToEdit() {
    return null;
  }

  @Override
  public void findShapes(Integer layer) {
    //Doesn't need to do anything
  }


  private void appendStuff(String other) {
    try {
      ap.append("\n").append(other);
    } catch (IOException e) {
      throw new IllegalStateException("can't be here!");
    }
  }

}

