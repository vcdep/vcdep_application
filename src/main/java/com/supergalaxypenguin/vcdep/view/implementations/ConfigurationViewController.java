package com.supergalaxypenguin.vcdep.view.implementations;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.ClipboardContent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.scene.paint.Color;

//import jfxtras.lab.util.event;

/**
 * The configuration view controller is the ViewController that hold the logic for the first scene of the application
 * In this window, the user will fill out the information necessary to run the pipeline 
 * @author Howtoon
 */
public class ConfigurationViewController implements Initializable {

    private int progress = 0;
    private String[] stages = new String[6];
    public static ConfigurationViewController instance;
    private static iMainController controller;
    private Stage stage;

    @FXML
    private ChoiceBox<String> lang;

    private String language;

    @FXML
    private Label testLabel;
    @FXML
    private TextField gitUrl;
    @FXML
    private TextField branch;
    @FXML
    private TextField jenkins;
    @FXML
    private TextField localGitRepo;
    @FXML
    private Button submit;
    @FXML
    private Button browse;
    @FXML
    private ImageView penguin;
    @FXML
    private ImageView background;
    @FXML
    private Rectangle target_1;
    @FXML
    private Rectangle target_2;
    @FXML
    private Rectangle target_3;
    @FXML
    private Rectangle target_4;
    @FXML
    private Label deployment;
    @FXML
    private Label integration;
    @FXML
    private Label static_a;
    @FXML
    private Label unit_test;
    @FXML
    private ImageView cleanUp;
    @FXML 
    private CheckBox java;
    @FXML
    private CheckBox php;
    @FXML
    private Button stageReset;
    @FXML
    private ImageView buildImage;
    @FXML
    private Label build;
    @FXML
    private Rectangle target_5;
    
    private String languageSelection = "";
    
    /**
     * Default layout coordinates for resetting the icons to their original positions
     */
    public final double deployX = 238.0;
    public final double deployY = 411.0;
    public final double integrateX = 358.0;
    public final double integrateY = 411.0;
    public final double staticX = 480.0;
    public final double staticY = 411.0;
    public final double unitX = 597.0;
    public final double unitY = 411.0;
    public final double buildX = 714.0;
    public final double buildY = 411.0;
    
    String payloadOne = "";
    String payloadTwo = "";
    String payloadThree = "";
    String payloadFour = "";
    String payloadFive = "";
    
    

    private ClipboardContent content = new ClipboardContent();
    
    /**
     * Creates the ConfigurationViewController
     */
    public ConfigurationViewController() {
        instance = this;
    }

    /**
     * Sets the controller variable to the interface of the MainController, This
     * must be done before the view can be used.
     *
     * @param _controller interface of the MainController
     */
    public void setMainControllerInterface(iMainController _controller) {
        this.controller = _controller;
    }

    /**
     * Sets the JavaFX stage being used by the application
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        //MouseControlUtil.makeDraggable(deploy);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Opening Pipline Viewer Window");
        
        System.out.println(Arrays.toString(stages));
        // this area will change
        //Check that all inputs are entered properly...
        //How to do that?
        //Set all inputs in Controller and runs the pipeline
        
        ArrayList<String> stagesList = new ArrayList<>();
        for (int i = 0; i< stages.length; i++)
        {
            if (stages[i]!=null)
            {
                stagesList.add(stages[i]);
            }
        }
        
        //String[] stages = new String[stagesList.size()];
        //stages = (String[])stagesList.toArray();
        
        System.out.println(Arrays.toString(stages));
        
        try {
            controller.runPipeline(gitUrl.getText(), languageSelection, localGitRepo.getText(), jenkins.getText(), branch.getText(), Arrays.copyOf(stagesList.toArray(), stagesList.size(), String[].class));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in controller.runpipline(params)");
        }
        try {
            controller.displayPipelineScene();
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationViewController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    @FXML
    private void handleBrowseButton(ActionEvent event) {
        File selectedDirectory = controller.displayDirectoryChooser();
        if (selectedDirectory == null) {
            localGitRepo.setText("No Directory selected");
        } else {
            localGitRepo.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     * Resets the staging area icons to their default positions
     * @param event
     */
    public void handleStageReset(ActionEvent event) {
        deployment.setLayoutX(deployX);
        deployment.setLayoutY(deployY);
        integration.setLayoutX(integrateX);
        integration.setLayoutY(integrateY);
        static_a.setLayoutX(staticX);
        static_a.setLayoutY(staticY);
        unit_test.setLayoutX(unitX);
        unit_test.setLayoutY(unitY);
        build.setLayoutX(buildX);
        build.setLayoutY(buildY);
        for(int i = 0; i<stages.length; i++)
        {
            stages[i] = null;
        }
    }

    /**
     * Not Used
     * @param event
     */
    public void handleDropDownTouchReleased(ActionEvent event)
    {
        System.out.println("Test Drop Down Touch Released");
    }
    
    /**
     * Sets the preferred language and whether or not the target_5 "build" is visible
     * @param event
     */
    @FXML
    public void handleCheckBox(ActionEvent event){
        System.out.println("box checked");
        CheckBox target = (CheckBox)event.getSource();
        
        if(java == target){
            php.setSelected(false);
            build.setVisible(true);
            target_5.setVisible(true);
            languageSelection = "java";
                    
        }else if(php == target){
            java.setSelected(false);
            build.setVisible(false);
            target_5.setVisible(false);
            languageSelection = "php";
        }
        System.out.println(languageSelection);
    }
    
    /**
     * Enables drag_over and sets the array of stages to their correct position
     * @param event
     */
    @FXML
    public void handleOnDragOver(DragEvent event) 
    {
        Rectangle target = null;
        /* data is dragged over the target */
        /* accept it only if it is not dragged from the same node 
         * and if it has a string data */
        if (event.getGestureSource() != event.getGestureTarget()
                && event.getDragboard().hasString()) 
        {
            target = (Rectangle)event.getTarget();
            /* allow for moving */
            event.acceptTransferModes(TransferMode.MOVE);
            Label label = (Label)event.getGestureSource();
            label.setLayoutX(target.getLayoutX());
            label.setLayoutY(target.getLayoutY()-11);
        }
        event.consume();
        System.out.println(content.getString());
        System.out.println(target);
        stages[0] = "checkout";
        if (target == target_1){
            payloadOne = content.getString();
            stages[1] = payloadOne;
        }else if (target == target_2){
            payloadTwo = content.getString();
            stages[2] = payloadTwo;
        }else if (target == target_3){
            payloadThree = content.getString();
            stages[3] = payloadThree;
        }else if (target == target_4){
            payloadFour = content.getString();
            stages[4] = payloadFour;
        }else if (target == target_5){
            payloadFive = content.getString();
            stages[5] = payloadFive;
        }
        System.out.println(Arrays.toString(stages));
    }
    
    /**
     * Not Used because image is set into correct position
     * @Deprecated
     * @param event
     */
    @FXML
    public void handleOnDragEntered(DragEvent event) {
        /* the drag-and-drop gesture entered the target */
        /* show to the user that it is an actual gesture target */
        if (event.getGestureSource() != event.getGestureTarget()
                && event.getDragboard().hasString()) {
            Rectangle target = (Rectangle)event.getTarget();
            target.setFill(Color.GREEN);
        }
        event.consume();
        System.out.println("Drag Entered");
    }
    
    /**
     * Not Used because image is set into correct position
     * @Deprecated
     * @param event
     */
    @FXML
    public void handleOnDragExited(DragEvent event) {
    /* mouse moved away, remove the graphical cues */
        Rectangle target = (Rectangle)event.getTarget();
        target.setFill(Color.BLACK);
        event.consume();
        System.out.println("Drag Exited");
    }
    
    /**
     * Consumes the payload of the drag and drop content
     * @param event
     */
    @FXML
    public void handleOnDragDropped(DragEvent event) {
        /* data dropped */
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            //target_1.setText(db.getString());
            success = true;
        }
        /* let the source know whether the string was successfully 
        * transferred and used */
        event.setDropCompleted(success);
        event.consume();
        System.out.println("Drag Dropped");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        build.setVisible(false);
        target_5.setVisible(false);
        deployment.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = deployment.startDragAndDrop(TransferMode.MOVE);

                content.putString("Deploy");
                db.setContent(content);
                System.out.println("drag detected");
                event.consume();

            }
        });
        integration.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = integration.startDragAndDrop(TransferMode.MOVE);

                content.putString("Integration");
                db.setContent(content);
                System.out.println("drag detected");
                event.consume();

            }
        });
        static_a.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = static_a.startDragAndDrop(TransferMode.MOVE);

                content.putString("Static");
                db.setContent(content);
                System.out.println("drag detected");
                event.consume();

            }
        });
        unit_test.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = unit_test.startDragAndDrop(TransferMode.MOVE);

                content.putString("Unit");
                db.setContent(content);
                System.out.println("drag detected");
                event.consume();
            }
        });
        build.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = build.startDragAndDrop(TransferMode.MOVE);

                content.putString("build");
                db.setContent(content);
                System.out.println("drag detected");
                event.consume();
            }
        });
        
    }
}
