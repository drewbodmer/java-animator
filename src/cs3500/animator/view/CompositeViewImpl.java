package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import cs3500.animator.controller.ListSelectListener;
import cs3500.animator.model.AShape;
import cs3500.animator.model.Circle;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.OurColor;
import cs3500.animator.model.Oval;
import cs3500.animator.model.ROAnimator;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Square;

/**
 * Represents an Visual view with an editor. It ie the view where the animation can be edited on the
 * flow.You  add , delete shapes as well as keyframes.
 */
public class CompositeViewImpl extends JFrame implements CompositeView {
  private CustomJPanel panel;
  private ROAnimator roa;
  private int speed;
  protected int time;
  private boolean paused;
  private boolean looping;

  private JButton playButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;
  private JButton increasespeed;
  private JButton decreasespeed;
  private JButton addShape;
  private JButton removeShape;
  private JButton addKeyframe;
  private JButton removeKeyframe;
  private JButton editKeyframe;
  private JButton removelayer;

  private JCheckBox checkLooping;

  //lists of shapes and keyframes to choose from
  private JList listOfShapes;
  private JList listOfKeyframes;
  private JList listOfLayers;

  //addShape text fields
  private JComboBox<String> chooseShape;
  private JTextField addSName;
  private JTextField addSTime;
  private JTextField addX;
  private JTextField addY;
  private JTextField addW;
  private JTextField addH;
  private JTextField r;
  private JTextField g;
  private JTextField b;
  private JTextField addSRot;
  private JTextField addlayer;

  //addKeyframe text fields
  private JTextField newT;
  private JTextField newX;
  private JTextField newY;
  private JTextField newW;
  private JTextField newH;
  private JTextField newR;
  private JTextField newG;
  private JTextField newB;
  private JTextField newRot;
  private JTextField newLayer;

  //editKeyframe text fields
  private JTextField editX;
  private JTextField editY;
  private JTextField editW;
  private JTextField editH;
  private JTextField editR;
  private JTextField editG;
  private JTextField editB;
  private JTextField editRot;
  private JTextField editlayer;
  protected JScrollBar scrub;
  private JPanel selectionListPanel;
  private Timer t;
  Map<Integer, Map<String, AShape>> k;
  Map<String, AShape> shapes;


  /**
   * Constructor for Composite View.
   *
   * @param roa read only model
   */
  public CompositeViewImpl(ROAnimator roa, int canvasW, int canvasH) {

    // Setting up the Canvas
    super();

    this.shapes = roa.getFrame(roa.getLastTick());
    this.k = new LinkedHashMap<>();
    for (AShape s : shapes.values()) {
      if (k.get(s.getLayer()) != null) {
        k.get(s.getLayer()).put(s.getName(), s);
      } else {
        System.out.println(s.getLayer());
        Map<String, AShape> newshapes = new LinkedHashMap<>();
        newshapes.put(s.getName(), s);
        k.put(s.getLayer(), newshapes);
      }
    }
    this.setTitle("Animator Editor");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Speed
    this.speed = 3;
    this.roa = roa;

    this.paused = false;  //initially not paused
    this.looping = false; //default is not looping

    // Setting up the borders
    this.setLayout(new BorderLayout());

    // setting the size of the canvas
    this.setSize(canvasW, canvasH);

    //interactions
    JPanel interactions = new JPanel();
    interactions.setLayout(new BoxLayout(interactions, BoxLayout.LINE_AXIS));
    this.add(interactions, BorderLayout.PAGE_END);

    //contains play, pause, resume, restart, speed up, slow down and enable looping
    JPanel controls = new JPanel();
    // controls.setBorder(BorderFactory.createTitledBorder("Controls"));
    controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
    interactions.add(controls);

    scrub = new JScrollBar(0, 0, 0, 0, roa.getLastTick());
    controls.add(scrub);
    pack();

    //play button
    playButton = new JButton("Play");
    playButton.setActionCommand("Play Button");
    controls.add(playButton);
    pack();

    //pause button
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause Button");
    controls.add(pauseButton);
    pack();

    //resume button
    resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("Resume Button");
    controls.add(resumeButton);
    pack();

    //restart button
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");
    controls.add(restartButton);
    pack();

    //increase speed button
    increasespeed = new JButton("faster");
    increasespeed.setActionCommand("beginAnimation faster");
    controls.add(increasespeed);
    pack();

    //decrease speed button
    decreasespeed = new JButton("slower");
    decreasespeed.setActionCommand("beginAnimation slower");
    controls.add(decreasespeed);
    pack();

    //looping checkbox
    checkLooping = new JCheckBox("Enable Looping");
    checkLooping.setSelected(false);
    checkLooping.setActionCommand("Looping");
    controls.add(checkLooping);
    pack();


    //shape and keyframe selection panel
    selectionListPanel = new JPanel();
    selectionListPanel.setLayout(new BoxLayout(selectionListPanel, BoxLayout.X_AXIS));
    interactions.add(selectionListPanel);


    //List of layers
    DefaultListModel<Integer> layers = new DefaultListModel<>();
    for (Integer s : this.k.keySet()) {
      layers.addElement(s);
    }
    listOfLayers = new JList(layers);
    listOfLayers.setName("layers");
    selectionListPanel.add(new JScrollPane(listOfLayers));

    //list of shapes
    DefaultListModel<String> shapes = new DefaultListModel<>();
    for (AShape s : roa.getFrame(roa.getLastTick()).values()) {
      shapes.addElement(s.getName());
    }
    listOfShapes = new JList<>(shapes);
    listOfShapes.setName("shapes");
    listOfShapes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    selectionListPanel.add(new JScrollPane(listOfShapes));

    //list of keyframes
    listOfKeyframes = new JList<>();
    listOfKeyframes.setName("keyframes");
    selectionListPanel.add(new JScrollPane(listOfKeyframes));


    //edit actions panel
    JPanel editor = new JPanel();
    editor.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Animation"));
    editor.setLayout(new BoxLayout(editor, BoxLayout.PAGE_AXIS));
    interactions.add(editor);

    //addShape button
    addShape = new JButton("Add shape");
    addShape.setActionCommand("Add shape");
    editor.add(addShape);
    pack();

    //removeShape button
    removeShape = new JButton("Remove shape");
    removeShape.setActionCommand("Remove shape");
    editor.add(removeShape);
    pack();

    //addKeyframe button
    addKeyframe = new JButton("Add keyframe");
    addKeyframe.setActionCommand("Add keyframe");
    editor.add(addKeyframe);
    pack();

    //removeKeyframe button
    removeKeyframe = new JButton("Remove keyframe");
    removeKeyframe.setActionCommand("Remove keyframe");
    editor.add(removeKeyframe);
    pack();

    //editKeyframe button
    editKeyframe = new JButton("Edit keyframe");
    editKeyframe.setActionCommand("Edit keyframe");
    editor.add(editKeyframe);
    pack();

    // delete layer button
    removelayer = new JButton("remove layer");
    removelayer.setActionCommand("remove layer");
    editor.add(removelayer);
    pack();


    // Creating the new Panel
    this.panel = new CustomJPanel();
    panel.setPreferredSize(new Dimension(canvasW, canvasH));
    JScrollPane pane = new JScrollPane(panel);
    this.add(pane);
    this.time = 1;
    pack();


    t = new Timer(40, new ActionListener() {
      int lasttick = roa.getLastTick();

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.setTheMap(roa.getFrame(time));
        CompositeViewImpl.this.refresh();
        if (scrub.getValueIsAdjusting()) {
          time = scrub.getValue();
        } else {
          scrub.setValue(time);
          if (!paused) {
            time = time + speed;

            if (looping && time >= lasttick) {
              time = 0;
            }
          }
        }
      }
    });
    setVisible(true);

  }

  @Override
  public void beginAnimation() {
    this.makeVisible();
    t.start();
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    playButton.addActionListener(actionListener);
    pauseButton.addActionListener(actionListener);
    resumeButton.addActionListener(actionListener);
    restartButton.addActionListener(actionListener);

    increasespeed.addActionListener(actionListener);
    decreasespeed.addActionListener(actionListener);

    addShape.addActionListener(actionListener);
    removeShape.addActionListener(actionListener);
    addKeyframe.addActionListener(actionListener);
    removeKeyframe.addActionListener(actionListener);
    editKeyframe.addActionListener(actionListener);
    removelayer.addActionListener(actionListener);

  }

  @Override
  public void addItemListener(ItemListener i) {
    checkLooping.addItemListener(i);
  }

  @Override
  public void addListSelectionListener(ListSelectListener lslistener) {
    listOfShapes.addListSelectionListener(lslistener);
    listOfLayers.addListSelectionListener(lslistener);
  }

  private void refresh() {
    this.repaint();
  }

  private void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void pauseAnimation() {
    this.paused = true;
  }

  @Override
  public void resumeAnimation() {
    this.paused = false;
  }

  @Override
  public void restartAnimation() {
    this.time = 0;
    this.paused = false;
    panel.setTheMap(roa.getFrame(time));
    CompositeViewImpl.this.refresh();
    this.setVisible(true);
  }

  @Override
  public void enableLoop() {
    this.looping = true;
  }

  @Override
  public void disableLoop() {
    this.looping = false;
  }

  @Override
  public void increaseSpeed() {
    speed++;
  }

  @Override
  public void decreaseSpeed() {
    if (speed > 1) {
      speed--;
    }
  }

  @Override
  public void addShapeDialog() {
    JPanel addS = new JPanel();
    addS.setLayout(new FlowLayout());

    addS.add(new JLabel("Choose a shape:"));
    String[] options = {"Rectangle", "Circle", "Square", "Ellipse"};
    chooseShape = new JComboBox<>();
    chooseShape.setActionCommand("Shape options");
    for (String option : options) {
      chooseShape.addItem(option);
    }
    addS.add(chooseShape);

    addSTime = new JTextField(3);
    addS.add(new JLabel("TIME:"));
    addS.add(addSTime);
    addSName = new JTextField(5);
    addS.add(new JLabel("NAME:"));
    addS.add(addSName);
    addX = new JTextField(3);
    addS.add(new JLabel("X:"));
    addS.add(addX);
    addY = new JTextField(3);
    addS.add(new JLabel("Y:"));
    addS.add(addY);
    addW = new JTextField(3);
    addS.add(new JLabel("Width:"));
    addS.add(addW);
    addH = new JTextField(3);
    addS.add(new JLabel("Height:"));
    addS.add(addH);


    r = new JTextField(3);
    addS.add(new JLabel("r:"));
    addS.add(r);
    g = new JTextField(3);
    addS.add(new JLabel("g:"));
    addS.add(g);
    b = new JTextField(3);
    addS.add(new JLabel("b:"));
    addS.add(b);

    addSRot = new JTextField(3);
    addS.add(new JLabel("rotation"));
    addS.add(addSRot);
    addlayer = new JTextField(3);
    addS.add(new JLabel("Layer:"));
    addS.add(addlayer);

    JOptionPane.showConfirmDialog(null, addS, "Enter Values",
            JOptionPane.OK_CANCEL_OPTION);
  }

  @Override
  public int getAddShapeTimeInput() {
    return Integer.parseInt(this.addSTime.getText());
  }

  @Override
  public AShape getNewShapeInput() {
    String name = addSName.getText();
    int x = Integer.parseInt(addX.getText());
    int y = Integer.parseInt(addY.getText());
    int w = Integer.parseInt(addW.getText());
    int h = Integer.parseInt(addH.getText());
    OurColor col = new OurColor(Integer.parseInt(r.getText()), Integer.parseInt(g.getText()),
            Integer.parseInt(b.getText()));
    String shapeType = (String) chooseShape.getSelectedItem();
    int rot = Integer.parseInt(addSRot.getText());
    int layer = Integer.parseInt(addlayer.getText());

    assert shapeType != null;
    switch (shapeType) {
      case "Rectangle":
        AShape rect = new Rectangle(name, x, y, w, h, col, rot);
        rect.addLayer(layer);
        return rect;
      case "Circle":
        AShape circle = new Circle(name, x, y, w, col, rot);
        circle.addLayer(layer);
        return circle;
      case "Square":
        AShape sqr = new Square(name, x, y, w, col, rot);
        sqr.addLayer(layer);
        return sqr;
      case "Ellipse":
        AShape ellipse = new Oval(name, x, y, w, h, col, rot);
        ellipse.addLayer(layer);
        return ellipse;
      default:
        return null;
    }
  }

  @Override
  public Keyframe getKFToAdd() {
    int t = Integer.parseInt(newT.getText());
    int x = Integer.parseInt(newX.getText());
    int y = Integer.parseInt(newY.getText());
    int w = Integer.parseInt(newW.getText());
    int h = Integer.parseInt(newH.getText());
    int r = Integer.parseInt(newR.getText());
    int g = Integer.parseInt(newG.getText());
    int b = Integer.parseInt(newB.getText());
    int rot = Integer.parseInt(newRot.getText());

    return new KeyframeImpl(t, x, y, w, h, r, g, b, rot);
  }

  @Override
  public Keyframe getEditedKF() {
    int time = this.getKFToEdit().getTime();
    int x = Integer.parseInt(editX.getText());
    int y = Integer.parseInt(editY.getText());
    int w = Integer.parseInt(editW.getText());
    int h = Integer.parseInt(editH.getText());
    int r = Integer.parseInt(editR.getText());
    int g = Integer.parseInt(editG.getText());
    int b = Integer.parseInt(editB.getText());
    int rot = Integer.parseInt(editRot.getText());

    return new KeyframeImpl(time, x, y, w, h, r, g, b, rot);
  }

  @Override
  public AShape getShapeToEdit() {
    AShape shape = null;

    for (AShape s : roa.getFrame(roa.getLastTick()).values()) {
      if (s.getName().equals(this.listOfShapes.getSelectedValue())) {
        shape = s;
      }
    }
    return shape;
  }

  @Override
  public Integer getLayerToEdit() {
    // list of layers is a list of integers, so casting should be safe.
    return (Integer) this.listOfLayers.getSelectedValue();
  }

  @Override
  public Keyframe getKFToEdit() {
    Keyframe kf = null;
    AShape shape = this.getShapeToEdit();

    for (Keyframe k : shape.getDirections()) {
      if (this.listOfKeyframes.getSelectedValue() != null) {
        if (k.getTime() == (int) this.listOfKeyframes.getSelectedValue()) {
          kf = k;
        }
      }
    }

    return kf;
  }

  @Override
  public void updateLists() {
    this.convertToLayers();
    DefaultListModel<String> shapes = new DefaultListModel<>();
    for (AShape s : roa.getFrame(roa.getLastTick()).values()) {
      shapes.addElement(s.getName());
    }
    this.k = this.convertToLayers();
    DefaultListModel<Integer> layers = new DefaultListModel<>();
    for (Integer i : this.k.keySet()) {
      layers.addElement(i);
    }
    listOfShapes.setModel(shapes);
    listOfLayers.setModel(layers);

    selectionListPanel.updateUI();
  }

  @Override
  public void findKeyframes(AShape s) {
    DefaultListModel<Integer> keyframes = new DefaultListModel<>();
    for (Keyframe kf : s.getDirections()) {
      keyframes.addElement(kf.getTime());
    }
    listOfKeyframes.setModel(keyframes);
    listOfKeyframes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    selectionListPanel.updateUI();
  }

  @Override
  public void findShapes(Integer layer) {
    DefaultListModel<String> shapes = new DefaultListModel<>();
    for (AShape s : k.get(layer).values()) {
      shapes.addElement(s.getName());
    }
    listOfShapes.setModel(shapes);
    listOfShapes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    selectionListPanel.updateUI();
  }

  @Override
  public void addKeyframeDialog() {
    JPanel addKF = new JPanel();
    addKF.setLayout(new FlowLayout());
    newT = new JTextField(3);
    addKF.add(new JLabel("TIME:"));
    addKF.add(newT);

    newX = new JTextField(3);
    addKF.add(new JLabel("X:"));
    addKF.add(newX);
    newY = new JTextField(3);
    addKF.add(new JLabel("Y:"));
    addKF.add(newY);
    newW = new JTextField(3);
    addKF.add(new JLabel("Width:"));
    addKF.add(newW);
    newH = new JTextField(3);
    addKF.add(new JLabel("Height:"));
    addKF.add(newH);

    newR = new JTextField(3);
    addKF.add(new JLabel("r:"));
    addKF.add(newR);
    newG = new JTextField(3);
    addKF.add(new JLabel("g:"));
    addKF.add(newG);
    newB = new JTextField(3);
    addKF.add(new JLabel("b:"));
    addKF.add(newB);

    newRot = new JTextField(3);
    addKF.add(new JLabel("Rotation:"));
    addKF.add(newRot);

    JOptionPane.showConfirmDialog(null, addKF, "Enter Values",
            JOptionPane.OK_CANCEL_OPTION);
  }

  @Override
  public void editKeyframeDialog() {
    JPanel editKF = new JPanel();
    editKF.setLayout(new FlowLayout());

    editX = new JTextField(Integer.toString(this.getKFToEdit().getX()), 3);
    editKF.add(new JLabel("X:"));
    editKF.add(editX);
    editY = new JTextField(Integer.toString(this.getKFToEdit().getY()), 3);
    editKF.add(new JLabel("Y:"));
    editKF.add(editY);
    editW = new JTextField(Integer.toString(this.getKFToEdit().getW()), 3);
    editKF.add(new JLabel("Width:"));
    editKF.add(editW);
    editH = new JTextField(Integer.toString(this.getKFToEdit().getH()), 3);
    editKF.add(new JLabel("Height:"));
    editKF.add(editH);

    editR = new JTextField(Integer.toString(this.getKFToEdit().getR()), 3);
    editKF.add(new JLabel("r:"));
    editKF.add(editR);
    editG = new JTextField(Integer.toString(this.getKFToEdit().getG()), 3);
    editKF.add(new JLabel("g:"));
    editKF.add(editG);
    editB = new JTextField(Integer.toString(this.getKFToEdit().getB()), 3);
    editKF.add(new JLabel("b:"));
    editKF.add(editB);

    editRot = new JTextField(Integer.toString(this.getKFToEdit().getRot()), 3);
    editKF.add(new JLabel("Rotation:"));
    editKF.add(editRot);


    JOptionPane.showConfirmDialog(null, editKF, "Enter Values",
            JOptionPane.OK_CANCEL_OPTION);
  }

  @Override
  public Map<Integer, Map<String, AShape>> convertToLayers() {
    shapes = roa.getFrame(roa.getLastTick());
    for (AShape s : shapes.values()) {
      if (k.get(s.getLayer()) != null) {
        k.get(s.getLayer()).put(s.getName(), s);
      } else {
        System.out.println(s.getLayer());
        Map<String, AShape> newshapes = new LinkedHashMap<>();
        newshapes.put(s.getName(), s);
        k.put(s.getLayer(), newshapes);
      }
    }
    return this.k;
  }
}
