import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.AShape;
import cs3500.animator.model.Animator;
import cs3500.animator.model.AnimatorImpl;
import cs3500.animator.model.Circle;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.Rectangle;

import static org.junit.Assert.assertEquals;

public final class AnimatorModelImplTest {
  private Animator a;
  private AShape c1;
  private AShape r1;
  private AShape o1;
  private OurColor white = new OurColor(255, 255, 255);
  private OurColor black = new OurColor(0, 0, 0);


  @Before
  public void initData() {
    a = new AnimatorImpl();
    c1 = new Circle("c1", 0, 0, 50, new OurColor(0, 0, 0));
    r1 = new Rectangle("r1", 100, 100, 50, 80, new OurColor(255, 255, 255));
  }

  @Test
  public void testAddToDir() {
    a.createShape(c1, 2);
    assertEquals(2, c1.getDirections().get(0).getTime());
    c1.addToDir(new KeyframeImpl(4, 20, 20, 30, 30, 10, 0, 0));
    assertEquals(4, c1.getDirections().get(1).getTime());
    c1.addToDir(new KeyframeImpl(3, 30, 30, 30, 30, 10, 0, 0));
    assertEquals(3, c1.getDirections().get(1).getTime());
  }

  @Test
  public void testGetFrameAt() {
    a.createShape(c1, 1);
    assertEquals(1, a.getFrame(a.getLastTick()).size());
    a.createShape(r1, 4);
    assertEquals(1, a.getFrame(2).size());
  }

  @Test
  public void testGetMaxTick() {
    a.createShape(c1, 1);
    assertEquals(1, a.getLastTick());
    a.createShape(r1, 3);
    assertEquals(3, a.getLastTick());
    a.addKF("r1", new KeyframeImpl(5, 100, 100, 100, 100, 0, 0, 0));
    assertEquals(5, a.getLastTick());
  }

  /**
   * The initial animator and shapes.

   @Before public void initData() {
   a = new AnimatorImpl();
   c1 = new Circle("c1", 0, 0, 50, white);
   r1 = new Rectangle("r1", 100, 100, 50, 80, black);
   o1 = new Oval("o1", 50, 50, 20, 25, black);
   }

   @Test public void testAnimatorConstructor() {
   Animator am = new AnimatorImpl();
   assertEquals("", am.printDesc());
   }

   @Test public void testCreateShape() {
   assertEquals("", a.printDesc());
   a.createShape(c1, 2);
   a.transform(new Move(2, 8, 0, 0), "c1");
   assertEquals("shape c1 Circle\n" +
   "Move c1 2 0 0 100 100 255 255 255  8 0 0 100 100 255 255 255\n",
   a.printDesc());
   }

   @Test public void testCreateShapeOfSameName() {
   try {
   a.createShape(c1, 0);
   a.createShape(c1, 3);
   } catch (IllegalArgumentException e) {
   assertEquals("AShape with that name already exists.", e.getMessage());
   }
   }

   @Test(expected = IllegalArgumentException.class)
   public void testCreateShapeAtInvalidTick() {
   a.createShape(c1, -3);
   }

   @Test public void testAddShape() {
   a.createShape(c1, 0);
   a.createShape(r1, 1);
   a.transform(new DoNothing(0, 1), "c1");
   a.transform(new DoNothing(1, 1), "r1");
   assertEquals("shape c1 Circle\n" +
   "Do Nothing c1 0 0 0 100 100 255 255 255  1 0 0 100 100 255 255 255\n" +
   "\n" +
   "shape r1 Rectangle\n" +
   "Do Nothing r1 1 100 100 50 80 0 0 0  1 100 100 50 80 0 0 0\n", a.printDesc());
   }

   @Test public void testRemoveShape() {
   a.createShape(c1, 0);
   a.transform(new DoNothing(0, 3), "c1");
   a.createShape(r1, 3);
   a.transform(new DoNothing(3, 4), "r1");
   assertEquals("shape c1 Circle\n" +
   "Do Nothing c1 0 0 0 100 100 255 255 255  3 0 0 100 100 255 255 255\n\n" +
   "shape r1 Rectangle\n"
   + "Do Nothing r1 3 100 100 50 80 0 0 0  4 100 100 50 80 0 0 0\n"
   , a.printDesc());
   a.removeShape("r1", 4);
   assertEquals("shape c1 Circle\n" +
   "Do Nothing c1 0 0 0 100 100 255 255 255  3 0 0 100 100 255 255 255\n" +
   "\n" +
   "shape r1 Rectangle\n" +
   "Do Nothing r1 3 100 100 50 80 0 0 0  4 100 100 50 80 0 0 0\n" +
   "Remove r1 4 100 100 50 80 0 0 0  4 100 100 50 80 0 0 0\n", a.printDesc());
   }

   @Test public void testRemoveShapeWhileOtherShapesExist() {
   a.createShape(c1, 0);
   a.transform(new Move(0, 12, 100, 100), "c1");
   a.transform(new Move(12, 14, 130, 130), "c1");
   a.transform(new Move(14, 18, 190, 10), "c1");
   a.createShape(r1, 20);
   a.transform(new Resize(20, 22, 100, 100), "r1");
   a.transform(new Resize(22, 24, 130, 130), "r1");
   a.transform(new Move(22, 29, 190, 10), "r1");
   a.transform(new Move(29, 34, 100, 100), "r1");
   a.removeShape("c1", 18);
   assertEquals("shape c1 Circle\n" +
   "Move c1 0 0 0 100 100 255 255 255  12 100 100 100 100 255 255 255\n" +
   "Move c1 12 100 100 100 100 255 255 255  14 130 130 100 100 255 255 255\n" +
   "Move c1 14 130 130 100 100 255 255 255  18 10 190 100 100 255 255 255\n" +
   "Remove c1 18 10 190 100 100 255 255 255  18 10 190 100 100 255 255 255\n" +
   "\n" +
   "shape r1 Rectangle\n" +
   "Resize r1 20 100 100 50 80 0 0 0  22 100 100 100 100 0 0 0\n" +
   "Resize r1 22 100 100 100 100 0 0 0  24 100 100 130 130 0 0 0\n" +
   "Move r1 22 100 100 130 130 0 0 0  29 10 190 130 130 0 0 0\n" +
   "Move r1 29 10 190 130 130 0 0 0  34 100 100 130 130 0 0 0\n", a.printDesc());
   }

   @Test public void testMove() {
   a.createShape(c1, 0);
   a.transform(move, "c1");
   assertEquals("shape c1 Circle\n" +
   "Move c1 0 0 0 100 100 255 255 255  5 100 200 100 100 255 255 255\n", a.printDesc());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testMoveNonExistingShape() {
   a.createShape(c1, 100);
   a.transform(move, "r1");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testResizeNonExistingShape() {
   a.createShape(c1, 100);
   a.transform(resize, "r1");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testRecolorNonExistingShape() {
   a.createShape(c1, 100);
   a.transform(recolor, "r1");
   }

   @Test public void testPrintSInfo() {
   a.createShape(c1, 0);
   a.createShape(o1, 0);
   a.createShape(r1, 5);
   a.transform(new Move(0, 10, 100, 100), "c1");
   a.transform(new DoNothing(0, 2), "o1");
   a.transform(new Move(2, 8, 100, 100), "o1");
   a.transform(new Resize(8, 10, 10, 10), "o1");
   assertEquals("shape o1 Oval\n" +
   "Do Nothing o1 0 100 100 10 10 0 0 0  2 100 100 10 10 0 0 0\n" +
   "Move o1 2 50 50 20 25 0 0 0  8 100 100 20 25 0 0 0\n" +
   "Resize o1 8 100 100 20 25 0 0 0  10 100 100 10 10 0 0 0\n", a.printSInfo("o1"));
   }

   @Test(expected = IllegalArgumentException.class)
   public void testPrintSInfoShapeDoesNotExist() {
   assertEquals("", a.printDesc());
   a.createShape(c1, 2);
   a.printSInfo("r1");
   }

   @Test public void testPrintDesc() {
   a.createShape(c1, 0);
   a.createShape(r1, 5);
   a.transform(new Move(0, 4, 100, 100), "c1");
   a.transform(recolor, "c1");
   a.transform(new Move(4, 6, 200, 200), "c1");
   a.transform(new Move(5, 7, 100, 100), "r1");
   a.transform(new Resize(6, 9, 150, 200), "r1");
   assertEquals("shape c1 Circle\n" +
   "Move c1 0 0 0 100 100 255 255 255  4 100 100 100 100 255 255 255\n" +
   "Recolor c1 3 100 100 100 100 255 255 255  7 100 100 100 100 0 0 0\n" +
   "Move c1 4 100 100 100 100 0 0 0  6 200 200 100 100 0 0 0\n" +
   "\n" +
   "shape r1 Rectangle\n" +
   "Move r1 5 100 100 50 80 0 0 0  7 100 100 50 80 0 0 0\n" +
   "Resize r1 6 100 100 50 80 0 0 0  9 100 100 150 200 0 0 0\n", a.printDesc());
   }


   @Test(expected = IllegalArgumentException.class)
   public void testStackMovesOverlapBegin() {
   a.createShape(c1, 0);
   a.transform(new Move(2, 6, 100, 100), "c1");
   a.transform(new Move(0, 3, 200, 200), "c1");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testStackMovesOverlapEnd() {
   a.createShape(c1, 0);
   a.transform(new Move(2, 6, 100, 100), "c1");
   a.transform(new Move(5, 7, 200, 200), "c1");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testStackMoves1OverlapCompletely() {
   a.createShape(c1, 0);
   a.transform(new Move(2, 6, 100, 100), "c1");
   a.transform(new Move(0, 7, 200, 200), "c1");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testStackMoves2OverlapCompletely() {
   a.createShape(c1, 0);
   a.transform(new Move(0, 7, 200, 200), "c1");
   a.transform(new Move(2, 6, 100, 100), "c1");
   }

   @Test public void testMoveEdge1() {
   a.createShape(c1, 0);
   a.transform(new Move(0, 4, 200, 200), "c1");
   a.transform(new Move(4, 6, 100, 100), "c1");
   assertEquals("shape c1 Circle\n" +
   "Move c1 0 0 0 100 100 255 255 255  4 200 200 100 100 255 255 255\n" +
   "Move c1 4 200 200 100 100 255 255 255  6 100 100 100 100 255 255 255\n",
   a.printDesc());
   }

   @Test public void testMoveEdge2() {
   a.createShape(c1, 0);
   a.transform(new Move(0, 4, 10, 10), "c1");
   a.transform(new Move(4, 6, 100, 100), "c1");
   a.transform(new Move(6,10,500,500), "c1");
   assertEquals("shape c1 Circle\n" +
   "Move c1 0 0 0 100 100 255 255 255  4 10 10 100 100 255 255 255\n" +
   "Move c1 4 10 10 100 100 255 255 255  6 100 100 100 100 255 255 255\n" +
   "Move c1 6 100 100 100 100 255 255 255  10 500 500 100 100 255 255 255\n"
   , a.printDesc());
   }

   @Test public void testNoStackedMove1() {
   a.createShape(c1, 0);
   a.transform(new Move(0, 5, 200, 200), "c1");
   a.transform(new Move(5, 9, 100, 100), "c1");
   assertEquals("shape c1 Circle\n" +
   "Move c1 0 0 0 100 100 255 255 255  5 200 200 100 100 255 255 255\n" +
   "Move c1 5 200 200 100 100 255 255 255  9 100 100 100 100 255 255 255\n",
   a.printDesc());
   }

   @Test public void testNoStackedMove2() {
   a.createShape(c1, 0);
   a.transform(new Move(0, 15, 100, 100), "c1");
   a.transform(new Move(15, 20, 200, 200), "c1");
   assertEquals("shape c1 Circle\n" +
   "Move c1 0 0 0 100 100 255 255 255  15 100 100 100 100 255 255 255\n" +
   "Move c1 15 100 100 100 100 255 255 255  20 200 200 100 100 255 255 255\n",
   a.printDesc());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testMoveExactSameInterval() {
   a.createShape(c1, 0);
   a.transform(new Move(0, 9, 100, 100), "c1");
   a.transform(new Move(0, 9, 200, 200), "c1");
   }

   @Test public void testGetFrame() {
   a.createShape(c1,0);
   assertEquals(c1.getX(), a.getFrame(0).get("c1").getX());

   }

   @Test public void testGetShapeStateXY() {
   a.createShape(c1, 0);
   a.transform(new Move(0,5,100,100),"c1");
   assertEquals(0, c1.getShapeState(0).getX());
   }

   @Test public void testGetShapeStateWH() {
   a.createShape(c1,0);
   a.transform(new Resize(0,4,20,20),"c1");
   assertEquals(100, c1.getShapeState(0).getW());
   }

   @Test public void testGetShapeStateColor() {
   a.createShape(c1,0);
   a.transform(new Recolor(0,5,new OurColor(200,200,200)), "c1");
   assertEquals("rgb(255,255,255)",c1.getShapeState(c1.getCT()).getC().formatSVG());
   }

   @Test public void testGetLastTick() {
   a.createShape(c1, 0);
   a.transform(new Move(0,5,100,100), "c1");
   a.createShape(r1, 3);
   a.transform(new Move(3,18, 200, 200), "r1");
   a.transform(new Recolor(5,8,new OurColor(100,100,100)), "c1");
   assertEquals(18, a.getLastTick());
   }*/

}