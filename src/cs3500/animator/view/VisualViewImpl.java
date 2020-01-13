package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import cs3500.animator.model.ROAnimator;

/**
 * Represents a Visual View. Its displays the animation in a visual format which is converted from a
 * text file.
 */
public final class VisualViewImpl extends JFrame implements View {
  private int time;
  private CustomJPanel panel;
  private Timer t;

  /**
   * Constructor for VisualViewImpl.
   *
   * @param a       read only animator to get data from.
   * @param speed   speed of animation.
   * @param hCanvas width of canvas.
   * @param wCanvas height of canvas.
   */
  public VisualViewImpl(ROAnimator a, int speed, int hCanvas, int wCanvas) {

    // Setting up the Canvas
    super();
    this.setTitle("Easy Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Speed

    // Setting up the borders
    this.setLayout(new BorderLayout());

    // would have to change a lot of code to get rid of these.

    // setting the size of the canvas
    this.setSize(wCanvas, hCanvas);


    // Creating the new Panel
    this.panel = new CustomJPanel();
    panel.setPreferredSize(new Dimension(wCanvas, hCanvas));
    JScrollPane pane = new JScrollPane(panel);
    this.add(pane);
    this.time = 1;
    this.pack();

    t = new Timer(speed, new ActionListener() {
      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.setTheMap(a.getFrame(time));
        VisualViewImpl.this.refresh();
        time++;
      }
    });

  }


  @Override
  public void beginAnimation() {
    this.makeVisible();
    t.start();
  }

  private void makeVisible() {
    this.setVisible(true);
  }

  private void refresh() {
    this.repaint();
  }

}

