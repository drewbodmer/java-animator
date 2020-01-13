README - Assignment 9 by Drew Bodmer and Karli Young

The features we were able to implement include scrubbing and rotation. They are described in more detail below. 

SCRUBBING -
Files affected:
- CompositeViewImpl: changes are all in the timer, which is in the constructor

ROTATION -
Files affected: 
- CompositeViewImpl: added JTextFields for inputting rotation data
- AShape: added constructor that takes in data for the rotation of a shape, and getter method for getting rotation of a shape
- Classes that extend AShape (Circle, Oval, Rectangle, Square) also implement the new constructor by calling super. 
- KeyframeImpl: added constructor that takes in data for the rotation of a shape in a Keyframe, and a getter method for getting the rotation of the keyframe. Also updated the getSVG method to print out SVG tags for when rotation occurs. 
- AnimationReader: Reads in arguments that either have rotation information, or not (changes were in readMotion method); there are either 16 arguments (without rotation) or 18 (with rotation)
- AnimationBuilder and AnimationBuilderImpl: now supports adding motions/keyframes that have rotations (while still supporting adding motions that do not include a rotation) 

Also added tests for both features in ExtraCreditTests class.