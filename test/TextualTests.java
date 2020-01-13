//import org.junit.Test;
//
//import cs3500.animator.model.AShape;
//import cs3500.animator.model.Keyframe;
//import cs3500.animator.model.KeyframeImpl;
//import cs3500.animator.model.OurColor;
//import cs3500.animator.model.Animator;
//import cs3500.animator.model.AnimatorImpl;
//import cs3500.animator.model.Circle;
//import cs3500.animator.model.ROAnimator;
//import cs3500.animator.view.TextViewImpl;
//import cs3500.animator.view.View;
//
//import static junit.framework.TestCase.assertEquals;

/**
 * Represents tests for textual view.
 *
 * public class TextualTests {
 *
 * @Test public void testGeneral() {
 *
 * AShape cir = new Circle("cir",0,0,3,new OurColor(2,2,2)); AShape p = new Circle("p",0,0,3,new
 * OurColor(2,2,2));
 *
 *
 * Animator kingText = new AnimatorImpl(); ROAnimator f = kingText; Keyframe re = new
 * KeyframeImpl(0,3,3,3); Keyframe rc = new KeyframeImpl(2,3,new OurColor(9,9,9));
 *
 *
 *
 * kingText.createShape(cir,0); kingText.createShape(p,2);
 *
 * //kingText.transform(re,"cir"); kingText.transform(rc,"p"); View t = new TextViewImpl(f);
 *
 * String dd = ((TextViewImpl) t).print();
 *
 * assertEquals("shape p circle\n" + "Recolor p 2 0 0 6 6 2 2 2  3 0 0 6 6 2 2 2\n" + "\n" + "shape
 * cir circle\n" + "Resize cir 0 0 0 6 6 2 2 2  3 0 0 6 6 2 2 2\n",dd);
 *
 *
 *
 *
 *
 * }
 *
 *
 * //To see whether the model still works
 * @Test(expected = IllegalArgumentException.class) public void testGeneral1() {
 *
 * AShape cir = new Circle("cir",0,0,3,new OurColor(2,2,2)); AShape p = new Circle("p",0,0,3,new
 * OurColor(2,2,2));
 *
 *
 * Animator kingText = new AnimatorImpl(); ROAnimator f = kingText; Transformation k = new
 * Resize(0,3,3,3); Transformation d = new Recolor(0,3,new OurColor(9,9,9));
 *
 *
 *
 * kingText.createShape(cir,0); kingText.createShape(p,2);
 *
 * kingText.transform(k,"cir"); kingText.transform(d,"p"); View t = new TextViewImpl(f);
 *
 * String dd = ((TextViewImpl) t).print();
 *
 * assertEquals("shape p circle\n" + "Recolor p 2 0 0 6 6 2 2 2  3 0 0 6 6 2 2 2\n" + "\n" + "shape
 * cir circle\n" + "Resize cir 0 0 0 6 6 2 2 2  3 0 0 6 6 2 2 2\n",dd);
 *
 * }
 *
 *
 *
 *
 *
 *
 * }
 */