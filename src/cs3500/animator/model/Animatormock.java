package cs3500.animator.model;

import java.io.IOException;
import java.util.Map;

public class Animatormock implements Animator {
  public Appendable ap = new StringBuilder();

  private void app(String str) {
    try {
      ap.append(str + "\n");
    } catch (IOException e) {

      throw new IllegalArgumentException("invalid input");
    }
  }

  @Override
  public void createShape(AShape s, int time) throws IllegalArgumentException {
    System.out.println("createshape");
    app("creating a shape " + s.getName() + " of type " + s.shapeName());
  }

  @Override
  public void removeShape(String name, int time) throws IllegalArgumentException {
    app("removing a shape " + name + " at time " + time);
  }

  @Override
  public void addKF(String name, Keyframe kf) {
    app("creating a shape " + " of type ");

  }

  @Override
  public Map<String, AShape> getShapes() {
    app("getting shapes");
    return null;
  }

  @Override
  public String printSInfo(String name) throws IllegalArgumentException {
    app("printing shape info");
    return null;
  }

  @Override
  public String printDesc() {
    app("printing the description");
    return null;
  }

  @Override
  public Map<String, AShape> getFrame(int time) {
    app("getting a frame of the animation at time " + time);
    return null;
  }

  @Override
  public int getLastTick() {
    return 0;
  }
}
