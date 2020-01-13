package cs3500.animator.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import cs3500.animator.model.AShape;
import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.Circle;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Square;

/**
 * The Builder that converts the inputs from the input file so that our model can interpret it.
 */
public class AnimationBuilderImpl implements AnimationBuilder<Animator> {
  private Map<String, AShape> shapeMap = new LinkedHashMap<>();
  private Map<String, AShape> orderedshapeMap = new LinkedHashMap<>();
  private Map<String, String> shapeFramework = new LinkedHashMap<>();
  private ArrayList<String> shapeorderedFramework = new ArrayList<>();

  @Override
  public Animator build() {
    for (String str : shapeorderedFramework) {
      orderedshapeMap.put(str, shapeMap.get(str));
    }
    return new AnimatorImpl(orderedshapeMap);
  }

  @Override
  public AnimationBuilder<Animator> setBounds(int x, int y, int width, int height) {
    return this;
  }

  @Override
  public AnimationBuilder<Animator> declareShape(String name, String type) {
    shapeFramework.put(name, type);
    shapeorderedFramework.add(name);
    return this;
  }

  @Override
  public AnimationBuilder<Animator> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                              int r1, int g1, int b1, int t2, int x2, int y2, int
                                                      w2, int h2, int r2, int g2, int b2) {
    AShape temp = shapeMap.get(name);
    if (temp != null) {
      this.addKeyFrame(temp, t2, x2, y2, w2, h2, r2, g2, b2);
    } else {
      this.addNewKeyFrame(name, t1, x1, y1, w1, h1, r1, g1, b1);
    }

    return this;
  }

  @Override
  public AnimationBuilder<Animator> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                              int r1, int g1, int b1, int rot1, int t2, int x2,
                                              int y2, int w2, int h2, int r2, int g2, int b2,
                                              int rot2) {
    AShape temp = shapeMap.get(name);
    if (temp != null) {
      this.addKeyFrame(temp, t2, x2, y2, w2, h2, r2, g2, b2, rot2);
    } else {
      this.addNewKeyFrame(name, t1, x1, y1, w1, h1, r1, g1, b1, rot1);
    }

    return this;
  }

  // Adaptor kind of thing used here because in our model we require the user to
  // add initial values for a shape.
  @Override
  public AnimationBuilder<Animator> addNewKeyFrame(String name, int t, int x, int y, int w, int h,
                                                   int r, int g, int b) {
    switch (shapeFramework.get(name)) {
      case "rectangle":
        shapeFramework.remove(name);
        AShape rect = new Rectangle(name, x, y, w, h, new OurColor(r, g, b));
        shapeMap.put(name, rect);
        addKeyFrame(rect, t, x, y, w, h, r, g, b);
        break;
      case "square":
        shapeFramework.remove(name);
        AShape sqr = new Square(name, x, y, w, new OurColor(r, g, b));
        shapeMap.put(name, sqr);
        addKeyFrame(sqr, t, x, y, w, h, r, g, b);
        break;
      case "ellipse":
        shapeFramework.remove(name);
        AShape ellipse = new Oval(name, x, y, w, h, new OurColor(r, g, b));
        shapeMap.put(name, ellipse);
        addKeyFrame(ellipse, t, x, y, w, h, r, g, b);
        break;
      case "circle":
        shapeFramework.remove(name);
        AShape circle = new Circle(name, x, y, w / 2, new OurColor(r, g, b));
        shapeMap.put(name, circle);
        addKeyFrame(circle, t, x, y, w, h, r, g, b);
        break;

      default:
        throw new IllegalArgumentException("Invalid argument in addkeyframe: "
                + shapeFramework.get(name));
    }
    return this;
  }

  @Override
  public AnimationBuilder<Animator> addNewKeyFrame(String name, int t, int x, int y, int w, int h,
                                                   int r, int g, int b, int rot) {
    switch (shapeFramework.get(name)) {
      case "rectangle":
        shapeFramework.remove(name);
        AShape rect = new Rectangle(name, x, y, w, h, new OurColor(r, g, b), rot);
        shapeMap.put(name, rect);
        addKeyFrame(rect, t, x, y, w, h, r, g, b, rot);
        break;
      case "square":
        shapeFramework.remove(name);
        AShape sqr = new Square(name, x, y, w, new OurColor(r, g, b), rot);
        shapeMap.put(name, sqr);
        addKeyFrame(sqr, t, x, y, w, h, r, g, b, rot);
        break;
      case "ellipse":
        shapeFramework.remove(name);
        AShape ellipse = new Oval(name, x, y, w, h, new OurColor(r, g, b), rot);
        shapeMap.put(name, ellipse);
        addKeyFrame(ellipse, t, x, y, w, h, r, g, b, rot);
        break;
      case "circle":
        shapeFramework.remove(name);
        AShape circle = new Circle(name, x, y, w / 2, new OurColor(r, g, b), rot);
        shapeMap.put(name, circle);
        addKeyFrame(circle, t, x, y, w, h, r, g, b, rot);
        break;

      default:
        throw new IllegalArgumentException("Invalid argument in addkeyframe: "
                + shapeFramework.get(name));
    }
    return this;
  }

  /**
   * The parameters here are the parameters of the transformations to be added to map so that they
   * be animated.
   */
  private void addKeyFrame(AShape s, int time, int x, int y, int w, int h, int r, int g, int b) {
    Keyframe kf = new KeyframeImpl(time, x, y, w, h, r, g, b);
    s.addToDir(kf);
  }

  private void addKeyFrame(AShape s, int time, int x, int y, int w, int h, int r, int g, int b,
                           int rot) {
    Keyframe kf = new KeyframeImpl(time, x, y, w, h, r, g, b, rot);
    s.addToDir(kf);
  }
}
