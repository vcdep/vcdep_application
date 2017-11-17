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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.scene.paint.Color;

//import jfxtras.lab.util.event;
public class ConfigurationViewController implements Initializable {

    private int progress = 0;
    private String[] stages;
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
    private ImageView deploy;
    @FXML
    private ImageView integration;
    @FXML
    private ImageView static_a;
    @FXML
    private ImageView unit_test;

    @FXML
    private Label deployment;

    private ClipboardContent content = new ClipboardContent();

    //private Image unitTest = new Image("..\\Images\\Icons\\UnitTest.png");
    /*
    //******Progress bar to be implemented on a later sprint//
    @FXML
    final ProgressBar pb = new ProgressBar(0);
    @FXML
    final ProgressIndicator pi = new ProgressIndicator(0);
     */

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
        //testLabel.setText(gitUrl.getText()+"\n"+branch.getText()+"\n"+jenkins.getText());
        // this area will change
        //System.out.println(gitUrl.getText()+"\n"+branch.getText()+"\n"+jenkins.getText());
        //Check that all inputs are entered properly...
        //How to do that?
        //Set all inputs in Controller and runs the pipeline
        //language.equalsIgnoreCase(lang.getValue());
        try {
            controller.runPipeline(gitUrl.getText(), lang.getValue(), localGitRepo.getText(), jenkins.getText(), branch.getText(), stages);
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

    public void registerDragEvent() {
        //Image deploy = new Image(getClass().getResourceAsStream("/com/lynden/planning/ui/container2.png"));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lang.getItems().add("PHP");
        lang.getItems().add("Java");
        lang.setValue("Java");
        deployment.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = deployment.startDragAndDrop(TransferMode.MOVE);

                content.putString("Deployment");
                db.setContent(content);
                System.out.println("drag detected");
                event.consume();

            }
        });
        target_1.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node 
                 * and if it has a string data */
                if (event.getGestureSource() != target_1
                        && event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                    //target_1.setFill(Color.BLACK);
                    //Pos pos = new Pos(target_1.getLayoutX(),target_1.getLayoutY());
                    Label movedLabel = (Label)event.getGestureSource();
                    movedLabel.setLayoutX(target_1.getLayoutX()+6);
                    movedLabel.setLayoutY(target_1.getLayoutY()+3);
                    

                }

                event.consume();
                System.out.println(content.getString());
            }
        });
    }

}
