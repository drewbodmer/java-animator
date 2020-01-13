package cs3500.animator.view;

import java.io.IOException;

import cs3500.animator.model.ROAnimator;

/**
 * Represents a Textual View. This View can write the given animation (given in its constructor) to
 * an appendable (also given in constructor) in a plain textual format when its beginAnimation
 * method is called.
 */
public final class TextViewImpl implements View {
  private ROAnimator roa;
  private Appendable ap;


  /**
   * Constructor for TextualViewImpl.
   *
   * @param roa read only model to get data from
   * @ap appendable to print the description to
   */
  public TextViewImpl(ROAnimator roa, Appendable ap) {
    this.roa = roa;
    this.ap = ap;

  }

  @Override
  public void beginAnimation() {
    try {
      ap.append(roa.printDesc());
    } catch (IOException e) {
      throw new IllegalStateException("Error trying to append to appendable.");
    }
  }

}

