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

/**
 * FXML Controller class
 *
 * @author Howtoon
 */
public class PipelineSceneController implements Initializable {

    private String gitTxt;
    private String branchTxt;
    private String jenkinsTxt;
    //private Label label;
    public static PipelineSceneController instance;
    private static iMainController controller;
    private Stage stage;
    Scanner logfile;
    String log = "";
    FileChooser filechooser = new FileChooser();
    
    
    @FXML
    private ImageView background1;
    @FXML
    private ImageView background2;
    @FXML
    private Button displayLog;
    
    
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
     
        
    }
    
    @FXML
    private void handleDisplayLogAction(ActionEvent event) throws IOException{
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
        */ 
        scrollPane.setContent(label);
    }
    
    /**
     * Updates the message in the scroll pane window
     * @param message 
     */
    public void updateScrollPane(String message){
        label.setWrapText(true);
        label.setText(message);
        label.setPrefWidth(365);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefSize(375, 385);
        scrollPane.setContent(label);
    }
    
    /**
     * Creates the ConfigurationViewController
     */
    public PipelineSceneController()
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
}
