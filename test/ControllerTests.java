import org.junit.Before;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.controller.ButtonListener;

public class ControllerTests {
  Appendable app;
  private Dummy view;
  private ActionListener listener = new ButtonListener();

  ActionEvent addShape = new ActionEvent(listener, ActionEvent.ACTION_FIRST, "Add shape");

  @Before
  public void initData() {
    view = new Dummy();
    Map<String, Runnable> runnables = new HashMap<String, Runnable>();
    runnables.put("Add shape", () -> view.addShapeDialog());
  }

}
