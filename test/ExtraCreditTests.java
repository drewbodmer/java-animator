import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.animator.model.AShape;
import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.ROAnimator;
import cs3500.animator.model.Rectangle;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationBuilderImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.CompositeViewMock;

import static junit.framework.TestCase.assertEquals;

public class ExtraCreditTests {
  private AnimationBuilder<Animator> builder = new AnimationBuilderImpl();
  private Readable in;

  @Before
  public void init() {
    in = new StringReader("");
  }

  @Test
  public void testRotateInModel() {
    /* Checks that constructing a shape without a rotation sets the rotation to 0, and
     that constructing a shape with a rotation actually sets the rotation*/
    AShape rect =
            new Rectangle("R", 100, 100, 100, 200,
                    new OurColor(0, 200, 0));
    assertEquals(0, rect.getRot());
    AShape rectRot =
            new Rectangle("R", 0, 0, 200, 100,
                    new OurColor(0, 200, 0), 120);
    assertEquals(120, rectRot.getRot());

    /* Same check as above, but for keyframes*/
    Keyframe kf = new KeyframeImpl(0, 100, 100, 100, 200, 200, 0, 0);
    assertEquals(0, kf.getRot());
    Keyframe kfRot = new KeyframeImpl(100, 100, 100, 100, 200, 200, 0, 0, 80);
    assertEquals(80, kfRot.getRot());

    /*Checks that adding the keyframe to a shape adds data for its rotation at a certain keyframe*/
    rect.addToDir(kf);
    assertEquals(0, rect.getDirections().get(0).getRot());
    rect.addToDir(kfRot);
    assertEquals(80, rect.getDirections().get(1).getRot());
  }


  /*Checks that the builder can read in files that contain rotation info for some motions, and
  not contain that info for other motions, and still create the correct model*/
  @Test
  public void testRotateInBuilder() {
    in = new StringReader("shape C ellipse\n" +
            "motion C 6  440 70 120 60 0 0 255      20 440 70 120 60 0 0 255 \n" +
            "motion C 20 440 70 120 60 0 0 255 0     50 440 250 120 60 0 0 255 90\n" +
            "motion C 50 440 250 120 60 0 0 255     70 440 370 120 60 0 170 85\n\n" +
            "shape R rectangle\n" +
            "motion R 0  100 100 50 200 0 0 70 90     0  100 100 50 200 0 0 70 90\n");
    Animator model = AnimationReader.parseFile(in, builder);
    assertEquals(2, model.getShapes().size());
    assertEquals(70, model.getLastTick());
    assertEquals(0,
            model.getFrame(model.getLastTick()).get("C").getDirections().get(0).getRot());
    assertEquals(90,
            model.getFrame(model.getLastTick()).get("C").getDirections().get(1).getRot());
    assertEquals(0,
            model.getFrame(model.getLastTick()).get("C").getDirections().get(2).getRot());
    assertEquals(90,
            model.getFrame(model.getLastTick()).get("R").getDirections().get(0).getRot());
  }


  /*Can't test swing for rotation*/

  @Test
  public void testScrub() {
    /*Checks if view knows when scubber is being adjusted*/
    Animator mod = new AnimatorImpl();
    mod.createShape(new Rectangle("R", 0, 0, 100, 200,
            new OurColor(200, 0, 0)), 0);
    mod.addKF("R", new KeyframeImpl(50, 0, 0, 200, 100, 0, 200, 0));
    ROAnimator model = mod;
    CompositeViewMock mock = new CompositeViewMock(model);
    assertEquals(1, mock.getTime());
    mock.getScrub().setValueIsAdjusting(true);
    mock.isScrubbing();
    assertEquals("Animation is scrubbing.\n", mock.ap.toString());
  }

}
