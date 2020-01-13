package cs3500.animator.controller;

public enum typeOfButtonPressed {

  pause("pause"),
  play("play"),
  enablelooping("looping"),
  disablelooping("not looping"),
  restart("restart"),
  faster("faster"),
  slower("slower"),
  addrect("add rectangle"),
  addellipse("add ellipse"),
  removeellipse("remove ellipse"),
  removerect("remove rectangle"),
  addkeyframetoellipse("add keyframe to circle"),
  addkeyframetorect("add keyframe to rectangle"),
  removekeyframefromrect("remove keyframe from rectangle"),
  removekeyframefromellipse("remove keyframe from circle"),
  editellipsekeyframe("edit ellipse keyframe"),
  editrectkeyframe("edit rectangle keyframe");


  private final String buttontype;

  /**
   * Sets the string representation of a ShapeType.
   *
   * @param strType the string representation
   */
  typeOfButtonPressed(String strType) {
    this.buttontype = strType;
  }

  @Override
  public String toString() {
    return buttontype;
  }


}
