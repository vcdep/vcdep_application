/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations;

import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
//import javafx.scene.control.TextAreaBuilder;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Howtoon
 */
public class PipelineSceneController implements Initializable {

    private String gitTxt;
    private String branchTxt;
    private String jenkinsTxt;
    private String status;
    private static PipelineSceneController instance;
    private static iMainController controller;
    private Stage stage;
    Scanner logfile;
    String log = "";
    FileChooser filechooser = new FileChooser();
    
    
    @FXML
    private ImageView background2;
    @FXML
    private ImageView background21;
    @FXML
    private Button displayLog;
    @FXML
    private ScrollPane imagePane = new ScrollPane();
    
    @FXML
    private ScrollPane scrollPane = new ScrollPane();
    final TextArea textArea= new TextArea();
    private Label label = new Label();
    
    /**
     * Initializes the controller class.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(5));
        transition.setNode(background21);
        
        transition.setToX(-450);
        
        transition.play();
        
    }
    
    @FXML
    private void handleDisplayLogAction(ActionEvent event) throws IOException{
        /*
        try {
            File logPath = filechooser.showOpenDialog(null);
            if (logPath != null)
            {
                logfile = new Scanner(new FileReader(logPath));
            }
            // TODO
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(PipelineSceneController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File Not found");
        }
        while (logfile.hasNextLine())
        {
            log = log + logfile.nextLine()+ " ";
        }
        label.setWrapText(true);
        label.setText(log);
        label.setPrefWidth(365);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefSize(375, 385);
        //textArea.setText("THIS IS MY LOG FILE");
        
        /*textArea = TextAreaBuilder.create()
                .prefWidth(400)
                .wrapText(true)
                .build();
        
        
        scrollPane.setContent(label);
        */
        controller.updateStatusToView(controller.getLogFile());
        //controller.updateStatusToView("Updated properly");
    }
    
    /**
     * Updates the message in the scroll pane window
     * @param message 
     */
    public void updateScrollPane(String message)
    {
        status = message;
        try
        {
            label.setWrapText(true);
            label.setText(message);
            label.setPrefWidth(365);
            scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            scrollPane.setPrefSize(375, 385);
            scrollPane.setContent(label);
        }
        catch(Exception e)
        {
            
        }
    }
    
    /**
     * Creates the ConfigurationViewController
     */
    public PipelineSceneController()
    {
        instance = this;
    }
    
    /**
     * Creates the PipelineSceneController
     * @return returns instance of PipelineSceneController
     */
    public static PipelineSceneController getInstance()
    {
        
        if (instance == null)
            instance = new PipelineSceneController();

        return instance;
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
    
    /**
     * Gets the current status message
     * @return 
     */
    public String getStatusMessage()
    {
        return status;
    }
}
