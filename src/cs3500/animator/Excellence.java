package cs3500.animator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.Animator;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationBuilderImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.CompositeViewImpl;
import cs3500.animator.view.SVGViewImpl;
import cs3500.animator.view.TextViewImpl;
import cs3500.animator.view.View;
import cs3500.animator.view.VisualViewImpl;

public final class Excellence {

  /**
   * Runs the animation based on user input.
   *
   * @param args Allows a user to input commands.
   */
  public static void main(String[] args) {

    String fileName = "";
    String outFile = "";
    String view = "";
    double speed = 10;

    int size = args.length;

    for (int i = 0; i < size; i += 2) {

      switch (args[i]) {
        case "-in":
          fileName = args[i + 1];
          break;
        case "-out":
          outFile = args[i + 1];
          break;
        case "-view":
          view = args[i + 1];
          break;
        case "-speed":
          speed = Double.parseDouble(args[i + 1]);
          break;
        default:
          throw new IllegalStateException("invalid input");
      }
    }

    try {
      // For the input file. Checking that there is a input file being given.
      if (fileName.equals("")) {

        JOptionPane.showMessageDialog(null,
                "Needs an input file",
                "Input File  error",
                JOptionPane.ERROR_MESSAGE);
      }


      if (view.equals("")) {
        JOptionPane.showMessageDialog(null,
                "Needs a valid view",
                "View not Provided",
                JOptionPane.ERROR_MESSAGE);
      }

      FileReader inputFile = new FileReader(fileName);
      AnimationBuilder<Animator> builder = new AnimationBuilderImpl();
      Animator model = AnimationReader.parseFile(inputFile, builder);

      if (view.equals("text")) {
        View txt = new TextViewImpl(model, System.out);
        txt.beginAnimation();
      }

      Appendable outPut;
      if (!outFile.equals("")) {
        System.out.println(outFile);
        outPut = new FileWriter(outFile);
      } else {
        outPut = System.out;
      }
      if (view.equals("svg")) {
        View svg = new SVGViewImpl(speed, 1000, 1000, model, outPut);
        svg.beginAnimation();
      }

      if (view.equals("visual")) {
        View visual = new VisualViewImpl(model, (int) speed, 1000, 800);
        visual.beginAnimation();
      }

      if (view.equals("composite")) {
        CompositeViewImpl composite = new CompositeViewImpl(model, 800, 1000);
        //   composite.beginAnimation();
        IController controller = new Controller(model, composite);
        controller.runAnimation();

      }

    } catch (IOException e) {
      throw new IllegalStateException("invalid input: " + e);
    }
  }
}




