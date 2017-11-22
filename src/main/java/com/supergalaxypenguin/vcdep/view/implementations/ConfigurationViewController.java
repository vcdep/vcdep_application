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
public class ConfigurationViewController implements Initializable {

    private int progress = 0;
    private String[] stages = new String[5];
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
    
    private String languageSelection = "";
    
    double deployX = 238.0;
    double deployY = 411.0;
    double integrateX = 358.0;
    double integrateY = 411.0;
    double staticX = 480.0;
    double staticY = 411.0;
    double unitX = 597.0;
    double unitY = 411.0;
    
    String payloadOne = "";
    String payloadTwo = "";
    String payloadThree = "";
    String payloadFour = "";

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

    public void setStage(Stage stage) {
        this.stage = stage;
        //MouseControlUtil.makeDraggable(deploy);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Opening Pipline Viewer Window");
        
        System.out.println(Arrays.toString(stages));
        //testLabel.setText(gitUrl.getText()+"\n"+branch.getText()+"\n"+jenkins.getText());
        // this area will change
        //System.out.println(gitUrl.getText()+"\n"+branch.getText()+"\n"+jenkins.getText());
        //Check that all inputs are entered properly...
        //How to do that?
        //Set all inputs in Controller and runs the pipeline
        //language.equalsIgnoreCase(lang.getValue());
        
        ArrayList<String> stagesList = new ArrayList<>();
        for (int i = 0; i< stages.length; i++)
        {
            if (stages[i]!=null)
            {
                stagesList.add(stages[i]);
            }
        }
        String[] stages = new String[stagesList.size()];
        stages = stagesList.toArray(stages);
        
        System.out.println(Arrays.toString(stages));
        
        try {
            controller.runPipeline(gitUrl.getText(), languageSelection, localGitRepo.getText(), jenkins.getText(), branch.getText(), stages);
        } catch (Exception e) {
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

    public void handleStageReset(ActionEvent event) {
        deployment.setLayoutX(deployX);
        deployment.setLayoutY(deployY);
        integration.setLayoutX(integrateX);
        integration.setLayoutY(integrateY);
        static_a.setLayoutX(staticX);
        static_a.setLayoutY(staticY);
        unit_test.setLayoutX(unitX);
        unit_test.setLayoutY(unitY);
        for(int i = 0; i<stages.length; i++)
        {
            stages[i] = null;
        }
    }

    public void handleDropDownTouchReleased(ActionEvent event)
    {
        System.out.println("Test Drop Down Touch Released");
    }
    
    @FXML
    public void handleCheckBox(ActionEvent event){
        System.out.println("box checked");
        CheckBox target = (CheckBox)event.getSource();
        
        if(java == target){
            php.setSelected(false);
            cleanUp.setVisible(true);
            languageSelection = "java";
                    
        }else if(php == target){
            java.setSelected(false);
            cleanUp.setVisible(false);
            languageSelection = "php";
        }
        System.out.println(languageSelection);
    }
    
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
        if (target == target_1){
            payloadOne = content.getString();
            stages[0] = payloadOne;
        }else if (target == target_2){
            payloadTwo = content.getString();
            stages[1] = payloadTwo;
        }else if (target == target_3){
            payloadThree = content.getString();
            stages[2] = payloadThree;
        }else if (target == target_4){
            payloadFour = content.getString();
            stages[3] = payloadFour;
        }
        System.out.println(Arrays.toString(stages));
    }
    
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
    
    @FXML
    public void handleOnDragExited(DragEvent event) {
    /* mouse moved away, remove the graphical cues */
        Rectangle target = (Rectangle)event.getTarget();
        target.setFill(Color.BLACK);
        event.consume();
        System.out.println("Drag Exited");
    }
    
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
    }
}
