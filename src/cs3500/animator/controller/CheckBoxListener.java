package cs3500.animator.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

/**
 * Represents a custom ItemListener for listening to checkbox item events (ie. checkbox is selected
 * or deselected). Contains functionality for setting a map of Item Events (Integers) to Runnables.
 */
public class CheckBoxListener implements ItemListener {
  Map<Integer, Runnable> checkboxActions;

  /**
   * Empty default constructor.
   */
  public CheckBoxListener() {
    // default constructor for checkboxlistener
  }

  /**
   * Set the map for Item Events. Item events (ie. selected and deselected) are Integers.
   */
  public void setCheckboxActionsMap(Map<Integer, Runnable> map) {
    checkboxActions = map;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      checkboxActions.get(e.getStateChange()).run();
    } else {
      checkboxActions.get(e.getStateChange()).run();
    }
  }
}
