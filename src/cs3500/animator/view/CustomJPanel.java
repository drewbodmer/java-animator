package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

import cs3500.animator.model.AShape;

/**
 * This is a custom JPanel built to meet our specifications.
 */

public final class CustomJPanel extends JPanel {

  private Map<Integer, Map<String, AShape>> k = new LinkedHashMap<>();


  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    setSize(1000, 800);
    setLocation(0, 0);
    AffineTransform at = g2d.getTransform();

    for (int i = this.getBottomLayer() - 1; i <= this.getTopLayer() + 1; i++) {
      if (k.get(i) != null) {
        for (AShape shape : k.get(i).values()) {
          g2d.setColor(shape.getC());
          g2d.rotate(Math.toRadians(shape.getRot()), shape.getX() + shape.getW() / 2,
                  shape.getY() + shape.getH() / 2);
          switch (shape.shapeName()) {
            case "circle":
              g2d.fillOval(shape.getX(), shape.getY(), shape.getW(), shape.getH());
              g2d.setTransform(at);
              break;
            case "rectangle":
              g2d.fillRect(shape.getX(), shape.getY(), shape.getW(), shape.getH());
              g2d.setTransform(at);
              break;
            case "ellipse":
              g2d.fillOval(shape.getX(), shape.getY(), shape.getW(), shape.getH());
              g2d.setTransform(at);
              break;
            case "square":
              g2d.fillRect(shape.getX(), shape.getY(), shape.getW(), shape.getH());
              g2d.setTransform(at);
              break;
            default:
              throw new IllegalArgumentException("invalid shape name: " + shape.shapeName());
          }
        }
      }
    }
  }


  /**
   * It sets the map input to K so that the paint component can iterate over it and draw the
   * objects.
   *
   * @param sh the map that k is supposed to be set it too.
   */
  void setTheMap(Map<String, AShape> sh) {
    this.k = new LinkedHashMap<>();
    for (AShape s : sh.values()) {
      if (k.get(s.getLayer()) != null) {
        k.get(s.getLayer()).put(s.getName(), s);
      } else {
        Map<String, AShape> newshapes = new LinkedHashMap<>();
        newshapes.put(s.getName(), s);
        k.put(s.getLayer(), newshapes);
      }
    }
  }

  private int getBottomLayer() {
    //cannot have more than 1000 layers!
    int bottom = 500;
    for (Integer integer : k.keySet()) {
      bottom = Integer.min(bottom, integer);
    }
    return bottom;
  }

  private int getTopLayer() {
    //cannot have more than 1000 layers!
    int top = -500;
    for (Integer integer : k.keySet()) {
      top = Integer.max(top, integer);
    }
    return top;
  }
}