/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations;

import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
//import javafx.scene.control.TextAreaBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    
    
    @FXML
    private ImageView cartoonSpaceBackground;
    @FXML
    private Button btnReset = new Button();
    @FXML
    private Button btnReSubmit = new Button();
    @FXML
    private Button btnForward = new Button();
    @FXML
    private Button btnGoBack = new Button();
    @FXML
    private ScrollPane imagePane = new ScrollPane();
    
    @FXML
    private Rectangle stage1 = new Rectangle();
    @FXML
    private Rectangle stage2 = new Rectangle();
    @FXML
    private Rectangle stage3 = new Rectangle();
    @FXML
    private Rectangle stage4 = new Rectangle();
    @FXML
    private Rectangle stage5 = new Rectangle();
    
    DropShadow shadow = new DropShadow();
    
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
        stage1.setFill(Color.GRAY);
    }
        
    @FXML
    private void handleMouseExitedButton(ActionEvent event){
        Button clickedButton = (Button)event.getSource();
        clickedButton.setEffect(null);
    }
    
    @FXML
    private void handleMouseEnteredButton(ActionEvent event){
        Button clickedButton = (Button)event.getSource();
        clickedButton.setEffect(shadow);
    }
    
    @FXML
    private void handleBtnGoBack(ActionEvent event)
    {
        System.out.println("Test Go Back Button");
    }
    
    @FXML
    private void handleBtnForward(ActionEvent event)
    {
        System.out.println("Test Forward Button");
    }
    
    @FXML
    private void handleBtnReset(ActionEvent event)
    {
        System.out.println("Test Reset Button");
    }
    
    @FXML
    private void handleBtnReSubmit(ActionEvent event)
    {
        System.out.println("Test Re-Submit Button");
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
