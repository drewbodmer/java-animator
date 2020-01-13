package cs3500.animator.model;


import java.util.LinkedHashMap;
import java.util.Map;


/**
 * AnimatorImpl represents an animation. It holds all the data for all the different shapes in it.
 */
public final class AnimatorImpl implements Animator {
  private Map<String, AShape> shapes;
  private StringBuilder str = new StringBuilder();

  public AnimatorImpl(Map<String, AShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Default constructor for an AnimatorModelImpl.
   */
  public AnimatorImpl() {
    shapes = new LinkedHashMap<>();
  }

  @Override
  public void createShape(AShape s, int time) {
    if (shapes.containsKey(s.getName()) || time < 0) {
      throw new IllegalArgumentException("AShape with that name already exists.");
    }
    s.setCT(time);
    shapes.put(s.getName(), s);
    shapes.get(s.getName()).addToDir(new KeyframeImpl(time, s.getX(), s.getY(), s.getW(), s.getH(),
            s.getC().getRed(), s.getC().getGreen(), s.getC().getBlue()));
  }

  @Override
  public void removeShape(String name, int time) throws IllegalArgumentException {
    shapes.remove(name);
  }

  /**
   * Adds the given keyframe to the given shape.
   *
   * @param name the name of the shape to add the keyframe to
   * @param kf   the keyframe to be added
   */
  @Override
  public void addKF(String name, Keyframe kf) {
    shapes.get(name).addToDir(kf);
  }


  @Override
  public int getLastTick() {
    int max = 0;
    for (AShape s : shapes.values()) {
      max = Math.max(max, s.getMaxTick());
    }
    return max;
  }

  @Override
  public String printSInfo(String name) {
    AShape s;
    if (shapes.get(name) == null) {
      throw new IllegalArgumentException("AShape does not exist.");
    }
    s = shapes.get(name);
    return "shape" + " " + s.getName() + " " + s.shapeName() + "\n" + s.getInfo();

  }

  @Override
  public String printDesc() {
    for (AShape s : shapes.values()) {
      if (str.length() < 1) {
        str.append(printSInfo(s.getName()));
      } else {
        str.append("\n").append(printSInfo(s.getName()));

      }
    }
    return str.toString();
  }

  @Override
  public Map<String, AShape> getFrame(int time) {
    Map<String, AShape> data = new LinkedHashMap<>();
    for (AShape s : shapes.values()) {
      if (s.getCT() < time) {
        data.put(s.getName(), s.getShapeState(time));
      }
    }
    return data;
  }


  @Override
  public Map<String, AShape> getShapes() {
    Map<String, AShape> shapescopy = this.shapes;
    return shapescopy;
  }
}

