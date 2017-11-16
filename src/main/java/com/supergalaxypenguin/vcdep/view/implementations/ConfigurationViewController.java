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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import jfxtras.lab.util.event;

public class ConfigurationViewController implements Initializable {

    private int progress = 0;
    private String[] stages;
    public static ConfigurationViewController instance;
    private static iMainController controller;
    private Stage stage;
    
    /**
     * Creates the ConfigurationViewController
     */
    public ConfigurationViewController()
    {
        instance = this;
    }
    
    /**
     * Sets the controller variable to the interface of the MainController,
     * This must be done before the view can be used.
     * @param _controller interface of the MainController
     */
    public void setMainControllerInterface(iMainController _controller)
    {
        this.controller = _controller;
    }
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
        
        //MouseControlUtil.makeDraggable(deploy);
    }
    
    @FXML
    private ChoiceBox<String> lang;
    @FXML
    private Label label;
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
    /*
    //******Progress bar to be implemented on a later sprint//
    @FXML
    final ProgressBar pb = new ProgressBar(0);
    @FXML
    final ProgressIndicator pi = new ProgressIndicator(0);
    */
    
    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        System.out.println("Opening Pipline Viewer Window");
        //testLabel.setText(gitUrl.getText()+"\n"+branch.getText()+"\n"+jenkins.getText());
        // this area will change
        //System.out.println(gitUrl.getText()+"\n"+branch.getText()+"\n"+jenkins.getText());
        //Check that all inputs are entered properly...
        //How to do that?
        //Set all inputs in Controller and runs the pipeline
        try
        {
            controller.runPipeline(gitUrl.getText(), lang.getValue(), localGitRepo.getText(), jenkins.getText(), branch.getText(), stages);
        }
        catch(Exception e)
        {
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
    private void handleBrowseButton(ActionEvent event) 
    {
        File selectedDirectory = controller.displayDirectoryChooser();
        if(selectedDirectory == null)
        {
            localGitRepo.setText("No Directory selected");
        }
        else
        {
            localGitRepo.setText(selectedDirectory.getAbsolutePath());
        }
    }
    public void registerDragEvent(){
        //Image deploy = new Image(getClass().getResourceAsStream("/com/lynden/planning/ui/container2.png"));
         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
     lang.getItems().add("php");
     lang.getItems().add("java");
     lang.setValue("java");
    }    
}
