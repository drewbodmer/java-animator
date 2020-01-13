package cs3500.animator.view;

/**
 * View allows a user to display an animation of an Animator.
 *
 * <p>Invariants: The given Animator cannot be null.</p>
 */
public interface View {

  /**
   * Initiates the animation, whether it is visual, SVG, or text.
   **/
  void beginAnimation();


}

