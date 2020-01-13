package cs3500.animator.controller;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Represents a custom ListSelectionListener for listening to list selection events.
 */
public class ListSelectListener implements ListSelectionListener {
  private Runnable listAction = () -> {
  };
  private Runnable layerAction = () -> {
  };

  /**
   * Empty default constructor.
   */
  public ListSelectListener() {
    // default constructor for listselectlistener
  }

  /**
   * Sets the given Runnable as the action to run when a list select event occurs.
   *
   * @param r Runnable to set
   */
  public void setListActions(Runnable r, Runnable r2) {
    listAction = r;
    layerAction = r2;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    JList list = (JList) e.getSource();
    if (list.toString().charAt(18) == 's') {
      listAction.run();
    } else if (list.toString().charAt(18) == 'l') {
      layerAction.run();
    }
  }
}
