package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JScrollBar;

import cs3500.animator.controller.ListSelectListener;
import cs3500.animator.model.AShape;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.ROAnimator;

public class CompositeViewMock extends CompositeViewImpl implements CompositeView {
  public final Appendable ap;
  public int speed;
  private boolean playing = true;
  private boolean looping;
  private boolean isScrubbing = false;

  /**
   * Represents a mock for our composite view adapter. It has the same methods as CompositeView, but
   * has different functionality that allows testing.
   *
   * @param roa a Read Only Animator given to the view to allow the view to get info from the
   *            model.
   */
  public CompositeViewMock(ROAnimator roa) {
    super(roa, 800, 800);
    this.ap = new StringBuilder();
    this.speed = 5;
  }

  private void app(String str) {
    try {
      ap.append(str).append("\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("invalid input");
    }
  }


  @Override
  public void addActionListener(ActionListener actionListener) {
    app("adding action listener");
  }

  @Override
  public void addItemListener(ItemListener itemListener) {
    app("adding item listener");
  }

  @Override
  public void addListSelectionListener(ListSelectListener listSelectListener) {
    app("adding list select listener");
  }

  @Override
  public void pauseAnimation() {
    app("pausing animation");
    this.playing = false;
  }

  @Override
  public void resumeAnimation() {
    app("resuming animation");
    this.playing = true;
  }

  @Override
  public void restartAnimation() {
    app("restarting animation");
    this.playing = true;
  }

  @Override
  public void enableLoop() {
    app("enabled looping");
    this.looping = true;
  }

  @Override
  public void disableLoop() {
    app("disabled looping");
    this.looping = false;
  }

  @Override
  public void increaseSpeed() {
    app("increasing speed");
    this.speed++;
  }

  @Override
  public void decreaseSpeed() {
    app("decreasing speed");
    this.speed--;
  }

  @Override
  public void addShapeDialog() {
    // not used
  }

  @Override
  public int getAddShapeTimeInput() {
    return 0;
  }

  @Override
  public AShape getShapeToEdit() {
    return null;
  }

  @Override
  public AShape getNewShapeInput() {
    return null;
  }

  @Override
  public Keyframe getKFToAdd() {
    return null;
  }

  @Override
  public Keyframe getEditedKF() {
    return null;
  }

  @Override
  public Keyframe getKFToEdit() {
    return null;
  }

  @Override
  public void updateLists() {
    // not used
  }

  @Override
  public void findKeyframes(AShape s) {
    // not used
  }

  @Override
  public void addKeyframeDialog() {
    // not used
  }

  @Override
  public void editKeyframeDialog() {
    // not used
  }

  @Override
  public void beginAnimation() {
    // not used
  }

  public JScrollBar getScrub() {
    return this.scrub;
  }

  public int getTime() {
    return this.time;
  }

  /**
   * Appends to the appendable if scrubbing is occurring.
   */
  public void isScrubbing() {
    if (scrub.getValueIsAdjusting()) {
      isScrubbing = true;
    } else {
      isScrubbing = false;
    }

    if (isScrubbing) {
      app("Animation is scrubbing.");
    }
  }
}
