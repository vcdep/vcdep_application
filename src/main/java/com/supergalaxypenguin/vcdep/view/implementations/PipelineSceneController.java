/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations;

import com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.StageAnimation;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.domain.StageInfo;
import com.supergalaxypenguin.vcdep.domain.StageType;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Howtoon
 */
public class PipelineSceneController implements Initializable {

    private String status;   //currently displayed text?
    private String[] new_stages = new String[5];
    private static PipelineSceneController instance;   //Singleton instance
    private static iMainController controller;          //Reference to the main controller (Used to Call to Model)
    private final DropShadow shadow = new DropShadow();     // A generic drop shadow effect
    private final HashMap<String,ImageView> animationIcons = new HashMap<>();  //master list of animation icons/images
    private String log = "";    //log file as a String?
    private int currentStage;   //The stage currently active (if no stage active = -1)
    private ArrayList<Rectangle> backGrounds = new ArrayList<>();    //list of rectangle backgrounds for animations
    private ArrayList<StageAnimation> animations = new ArrayList<>();  //list of currently active animations
    private Timer timer;

    /**
     * List of StageInfo objects in the order that they will be displayed
     */
    public ArrayList<StageInfo> stageInfos;
    private boolean isPlaying = false;
    private boolean displayLog = false;
    private String language = "";

    /**
     * JavaFX elements
     */
    @FXML
    public ImageView chkoutImage1;
    @FXML
    private ImageView chkoutImage2;
    @FXML
    private ImageView chkoutImagePassed;
    @FXML
    private ImageView chkoutImageFailed;
    @FXML
    private ImageView chkoutArrow1;
    @FXML
    private ImageView SAImage1;
    @FXML
    private ImageView SAImage2;
    @FXML
    private ImageView SAImagePassed;
    @FXML
    private ImageView SAImageFailed;
    @FXML
    private ImageView SAArrow1;
    @FXML
    private ImageView SAArrow2;
    @FXML
    private ImageView UnitImage1;
    @FXML
    private ImageView UnitImage2;
    @FXML
    private ImageView UnitImagePassed;
    @FXML
    private ImageView UnitImageFailed;
    @FXML
    private ImageView UnitArrow1;
    @FXML
    private ImageView UnitArrow2;
    @FXML
    private ImageView IntegrationImage1;
    @FXML
    private ImageView IntegrationImage2;
    @FXML
    private ImageView IntegrationImagePassed;
    @FXML
    private ImageView IntegrationImageFailed;
    @FXML
    private ImageView IntegrationArrow1;
    @FXML
    private ImageView IntegrationArrow2;
    @FXML
    private ImageView DeployImage1;  //Jenkins icon
    @FXML
    private ImageView DeployImage2;
    @FXML
    private ImageView DeployImagePassed;
    @FXML
    private ImageView DeployImageFailed;
    @FXML
    private ImageView DeployArrow1;
    @FXML
    private ImageView BuildImage1;
    @FXML
    private ImageView BuildImage2;
    @FXML
    private ImageView BuildImagePassed;
    @FXML
    private ImageView BuildImageFailed;
    @FXML
    private ImageView BuildArrow1;
    @FXML
    private ImageView PauseImage;
    @FXML
    private ImageView PlayImage;
    @FXML
    private Rectangle stage0;
    @FXML
    private Rectangle stage1;
    @FXML
    private Rectangle stage2;
    @FXML
    private Rectangle stage3;
    @FXML
    private Rectangle stage4;
    @FXML
    private Rectangle stage5;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnCheckOut;
    @FXML
    private Button btnSA;
    @FXML
    private Button btnUnit;
    @FXML
    private Button btnIntegration;
    @FXML
    private Button btnDeploy;
    @FXML
    private Button btnBuild;
    @FXML
    private Button btnLogAndScript;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Label deployment;
    @FXML
    private Label integration;
    @FXML
    private Label static_a;
    @FXML
    private Label unit_test;
    @FXML
    private Label build;
    @FXML
    private Rectangle target_1;
    @FXML
    private Rectangle target_2;
    @FXML
    private Rectangle target_3;
    @FXML
    private Rectangle target_4;
    @FXML
    private Rectangle target_5;
    @FXML
    private Button stageReset;
    
    
          
    HashMap<StageType, Button> helpButtons = new HashMap<>();
    
    private Label label = new Label();
    
    private final HashMap<Integer, String> stages = new HashMap<>();
    
    private ClipboardContent content = new ClipboardContent();
    
    /**
     * Default layout coordinates for resetting the icons to their original positions
     */
    public final double deployX = 320.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double deployY = 14.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double integrateX = 440.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double integrateY = 14.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double staticX = 560.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double staticY = 14.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double unitX = 680.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double unitY = 14.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double buildX = 800.0;

   /**
    * Default layout coordinates for resetting the icons to their original positions
    */
   public final double buildY = 14.0;
    
    String payloadOne = "";
    String payloadTwo = "";
    String payloadThree = "";
    String payloadFour = "";
    String payloadFive = "";
    
    /**
     * Initializes the controller class.
     * runs upon load of view
     * @param event
     */
    @FXML
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
        if (target == target_1){
            payloadOne = content.getString();
            new_stages[0] = payloadOne;
        }else if (target == target_2){
            payloadTwo = content.getString();
            new_stages[1] = payloadTwo;
        }else if (target == target_3){
            payloadThree = content.getString();
            new_stages[2] = payloadThree;
        }else if (target == target_4){
            payloadFour = content.getString();
            new_stages[3] = payloadFour;
        }else if (target == target_5){
            payloadFive = content.getString();
            new_stages[4] = payloadFive;
        }
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

    /**
     * This gets run when the view is first initialized to set up the View
     * @param url
     * @param rb 
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        
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
        currentStage = -1;
        
        this.backGrounds.add(stage0);
        this.backGrounds.add(stage1);
        this.backGrounds.add(stage2);
        this.backGrounds.add(stage3);
        this.backGrounds.add(stage4);
        this.backGrounds.add(stage5);
        
        this.helpButtons.put(StageType.CHECKOUT, this.btnCheckOut);
        this.helpButtons.put(StageType.STATIC, this.btnSA);
        this.helpButtons.put(StageType.UNIT, this.btnUnit);
        this.helpButtons.put(StageType.INTEGRATION, this.btnIntegration);
        this.helpButtons.put(StageType.DEPLOY, this.btnDeploy);
        this.helpButtons.put(StageType.BUILD, this.btnBuild);
        
        for (int i = 0; i<backGrounds.size(); i++)
        {
            backGrounds.get(i).setVisible(false);
        }
        
        this.btnPause.setVisible(false);
        this.chkoutImage1.setVisible(false);
        this.chkoutImage2.setVisible(false);
        this.chkoutImageFailed.setVisible(false);
        this.chkoutImagePassed.setVisible(false);
        this.btnCheckOut.setVisible(false);
        this.chkoutArrow1.setVisible(false);
        this.SAImage1.setVisible(false);
        this.SAImage2.setVisible(false);
        this.SAImageFailed.setVisible(false);
        this.SAImagePassed.setVisible(false);
        this.btnSA.setVisible(false);
        this.SAArrow1.setVisible(false);
        this.SAArrow2.setVisible(false);
        this.UnitImage1.setVisible(false);
        this.UnitImage2.setVisible(false);
        this.UnitImageFailed.setVisible(false);
        this.UnitImagePassed.setVisible(false);
        this.btnUnit.setVisible(false);
        this.UnitArrow1.setVisible(false);
        this.UnitArrow2.setVisible(false);
        this.IntegrationImage1.setVisible(false);
        this.IntegrationImage2.setVisible(false);
        this.IntegrationImageFailed.setVisible(false);
        this.IntegrationImagePassed.setVisible(false);
        this.btnIntegration.setVisible(false);
        this.IntegrationArrow1.setVisible(false);
        this.IntegrationArrow2.setVisible(false);
        this.DeployImage1.setVisible(false);
        this.DeployImage2.setVisible(false);
        this.DeployImageFailed.setVisible(false);
        this.DeployImagePassed.setVisible(false);
        this.btnDeploy.setVisible(false);
        this.DeployArrow1.setVisible(false);
        this.BuildImage1.setVisible(false);
        this.BuildImage2.setVisible(false);
        this.BuildImageFailed.setVisible(false);
        this.BuildImagePassed.setVisible(false);
        this.BuildArrow1.setVisible(false);
        this.btnBuild.setVisible(false);
        
        this.animationIcons.put("chkoutImage1",this.chkoutImage1);
        this.animationIcons.put("chkoutImage2",this.chkoutImage2);
        this.animationIcons.put("chkoutImageFailed",this.chkoutImageFailed);
        this.animationIcons.put("chkoutImagePassed",this.chkoutImagePassed);
        this.animationIcons.put("chkoutArrow1", this.chkoutArrow1);
        this.animationIcons.put("SAImage1",this.SAImage1);
        this.animationIcons.put("SAImage2",this.SAImage2);
        this.animationIcons.put("SAImageFailed",this.SAImageFailed);
        this.animationIcons.put("SAImagePassed",this.SAImagePassed);
        this.animationIcons.put("SAArrow1",this.SAArrow1);
        this.animationIcons.put("SAArrow2",this.SAArrow2);
        this.animationIcons.put("UnitImage1",this.UnitImage1);
        this.animationIcons.put("UnitImage2",this.UnitImage2);
        this.animationIcons.put("UnitImageFailed",this.UnitImageFailed);
        this.animationIcons.put("UnitImagePassed",this.UnitImagePassed);
        this.animationIcons.put("UnitArrow1",this.UnitArrow1);
        this.animationIcons.put("UnitArrow2",this.UnitArrow2);
        this.animationIcons.put("IntegrationImage1",this.IntegrationImage1);
        this.animationIcons.put("IntegrationImage2",this.IntegrationImage2);
        this.animationIcons.put("IntegrationImageFailed",this.IntegrationImageFailed);
        this.animationIcons.put("IntegrationImagePassed",this.IntegrationImagePassed);
        this.animationIcons.put("IntegrationArrow1",this.IntegrationArrow1);
        this.animationIcons.put("IntegrationArrow2",this.IntegrationArrow2);
        this.animationIcons.put("DeployImage1",this.DeployImage1);
        this.animationIcons.put("DeployImage2",this.DeployImage2);
        this.animationIcons.put("DeployImageFailed",this.DeployImageFailed);
        this.animationIcons.put("DeployImagePassed",this.DeployImagePassed);
        this.animationIcons.put("DeployArrow1",this.DeployArrow1);
        this.animationIcons.put("BuildImage1",this.BuildImage1);
        this.animationIcons.put("BuildImage2",this.BuildImage2);
        this.animationIcons.put("BuildImageFailed",this.BuildImageFailed);
        this.animationIcons.put("BuildImagePassed",this.BuildImagePassed);
        this.animationIcons.put("BuildArrow1",this.BuildArrow1);
    }
    
    /**
     * Removes a drop shadow upon exiting a hover any button in the scene
     * @param event 
     */    
    @FXML
    private void handleMouseExitedButton(ActionEvent event){
        Button clickedButton = (Button)event.getSource();
        clickedButton.setEffect(null);
    }
    
    /**
     * Creates a drop shadow upon hovering over any button in the scene
     * @param event 
     */
    @FXML
    private void handleMouseEnteredButton(ActionEvent event){
        Button clickedButton = (Button)event.getSource();
        clickedButton.setEffect(shadow);
    }
    
    /**
     * Opens a web browser to the wiki page associated with this stage
     * @param event
     * @throws Exception 
     */
    @FXML
    public void handleBtnCheckout(ActionEvent event) throws Exception
    {
        System.out.println("Test btnCheckOut");
        Desktop.getDesktop().browse(new URI("http://vcdep.com/wiki/checkout"));
        //Open Help Window/Description
    }
    
    /**
     * Opens a web browser to the wiki page associated with this stage
     * @param event
     * @throws Exception 
     */
    @FXML
    public void handleBtnSA(ActionEvent event) throws Exception
    {
        System.out.println("Test btnSA");
        Desktop.getDesktop().browse(new URI("http://vcdep.com/wiki/static"));
        //Open Help Window/Description
    }
    
    /**
     * Opens a web browser to the wiki page associated with this stage
     * @param event
     * @throws Exception 
     */
    @FXML
    public void handleBtnUnit(ActionEvent event) throws Exception
    {
        System.out.println("Test btnUnit");
        Desktop.getDesktop().browse(new URI("http://vcdep.com/wiki/unit"));
        //Open Help Window/Description
    }
    
    /**
     * Opens a web browser to the wiki page associated with this stage
     * @param event
     * @throws Exception 
     */
    @FXML
    public void handleBtnIntegration(ActionEvent event) throws Exception
    {
        System.out.println("Test btnIntegration");
        Desktop.getDesktop().browse(new URI("http://vcdep.com/wiki/integration"));
        //Open Help Window/Description
    }

    /**
     * Opens a web browser to the wiki page associated with this stage
     * @param event
     * @throws Exception 
     */
    @FXML
    public void handleBtnDeploy(ActionEvent event) throws Exception
    {
        System.out.println("Test btnDeploy");
        Desktop.getDesktop().browse(new URI("http://vcdep.com/wiki/deploy"));
        //Open Help Window/Description
    }
    
    /**
     * Opens a web browser to the wiki page associated with this stage
     * @param event
     * @throws Exception 
     */
    @FXML
    public void handleBtnBuild(ActionEvent event) throws Exception
    {
        System.out.println("Test btnBuild");
        Desktop.getDesktop().browse(new URI("http://vcdep.com/wiki/build"));
        //Open Help Window/Description
    }
    
    /**
     * Opens a web browser to the wiki page associated with this stage
     * @param event 
     */
    @FXML
    public void handleBtnPause(ActionEvent event)
    {
        System.out.println("Pause Now");
        this.btnPause.setVisible(false);
        this.btnPlay.setVisible(true);
        timer.cancel();
        isPlaying = false;
    }
    
    /**
     * Play through the stages in a timed order
     * @param event 
     */
    @FXML
    public void handleBtnPlay(ActionEvent event)
    {
        System.out.println("Play Now");
        this.btnPlay.setVisible(false);
        this.btnPause.setVisible(true);
        isPlaying = true;
        timer = new java.util.Timer();
        timer.schedule(new TimerTask() 
        {
            public void run() 
            {
                if(currentStage == stageInfos.size()-1)
                {
                    this.cancel();
                }
                Platform.runLater(new Runnable() 
                {
                    public void run() 
                    {
                        getNextAnimation();
                        updateStatus();
                    }
                });
            }
        }, 1, 12000);
    }
    
    /**
     * Remove the current stage information in order to go back a stage
     * @param event 
     */
    @FXML
    private void handleBtnGoBack(ActionEvent event)
    {
        System.out.println("Test Go Back Button");
        if(isPlaying)
        {
            timer.cancel();
            this.btnPause.setVisible(false);
            this.btnPlay.setVisible(true);
            isPlaying = false;
        }
        this.getLastAnimation();
        this.updateStatus();
        
    }
    
    /**
     * Goto the next Stage and display the animation and text
     * @param event 
     */
    @FXML
    private void handleBtnForward(ActionEvent event)
    {
        System.out.println("Test Forward Button");
        if(isPlaying)
        {
            timer.cancel();
            this.btnPause.setVisible(false);
            this.btnPlay.setVisible(true);
            isPlaying = false;
        }
        this.getNextAnimation();
        this.updateStatus();
        
    }
    
    /**
     * Go back to the configuration scene
     * @param event 
     */
    @FXML
    private void handleBtnReturn(ActionEvent event)
    {
        System.out.println("Return to Config Scene");
        try{
            this.controller.displayConfigurationScene();
        }
        catch(Exception e)
        {
        }
    }
    
    /**
     * ReSubmit button should clear all current animations and seek an updated log file to build new animations
     * @param event 
     */
    @FXML
    private void handleBtnReSubmit(ActionEvent event)
    {
        
        System.out.println("Opening Pipline Viewer Window");
        
       // System.out.println(Arrays.toString(stages));
        // this area will change
        //Check that all inputs are entered properly...
        //How to do that?
        //Set all inputs in Controller and runs the pipeline
        
        ArrayList<String> stagesList = new ArrayList<>();
        stagesList.add("checkout");
        for (int i = 0; i< new_stages.length; i++)
        {
            if (new_stages[i]!=null)
            {
                stagesList.add(new_stages[i].toLowerCase());
            }
        }
        
        //String[] stages = new String[stagesList.size()];
        //stages = (String[])stagesList.toArray();
        
        //System.out.println(Arrays.toString(stages));
        
        try {
            controller.runPipeline(controller.getGitHubURL().toLowerCase(), controller.getLanguage().toLowerCase(), controller.getLocalRepo(), controller.getJenkinsURL(), controller.getBranchName().toLowerCase(), Arrays.copyOf(stagesList.toArray(), stagesList.size(), String[].class));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in controller.runpipline(params)");
        }
        try {
            controller.displayWaitScene();
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationViewController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        
        System.out.println("Test Re-Submit Button");
    }
    
    /**
     * sets the scrolling text box to the correct text
     */
    private void updateStatus()
    {
    
        if (this.currentStage < 0) {
            this.status = "";
            return;
        }
        if (this.displayLog)
        {
            this.status = this.stageInfos.get(currentStage).getLogChunk();
        }
        else
        {
            this.status = this.stageInfos.get(currentStage).getScript();
        }
    }
    
    /**
     * ReSubmit button should clear all current animations and seek an updated log file to build new animations
     * @param event 
     */
    @FXML
    private void handleBtnLogAndScript(ActionEvent event)
    {
        System.out.println("Test Logfile/Script Button");
        this.displayLog = !this.displayLog;
        this.updateStatus();
        if (this.displayLog)
        {
            this.btnLogAndScript.textProperty().set("Display Script");
            this.updateScrollPane(this.status);
        }
        else
        {
            this.btnLogAndScript.textProperty().set("Display Log File");
            this.updateScrollPane(this.status);
        }
    }
    
    /**
     * Updates the message in the scroll pane window
     * @param message 
     */
    public void updateScrollPane(String message)
    {
        //status = message;
        try
        {
            label.setWrapText(true);
            label.setText(message);
            label.setPrefWidth(595);
            scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
            scrollPane.setPrefSize(605, 499);
            scrollPane.setContent(label);
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        PipelineSceneController.controller = _controller;
    }
    
    /**
     * Gets the current status message
     * @return 
     */
    public String getStatusMessage()
    {
        return status;
    }
    
    /**
     * Adds a new animation to the animations list using the stage info and background
     * @param i currentStage
     */
    private void createNewAnimations(int i)
    {
        animations.add(StageAnimation.getInstance(stageInfos.get(i+1), this.animationIcons, backGrounds.get(i+1)));
    }
    
    /**
     * removes the last animation from the animations list
     * @param i 
     */
    private void removeAnimation(int i)
    {
        animations.get(i).stop();
        animations.get(i).moveToEnd();
        animations.remove(i);
        if (animations.size() >0)
        {
            animations.get(animations.size()-1).play();
            
        }
    }
    
    /**
     * increment currentStage if a next stage exists
     */
    private void getNextAnimation()
    {
        if (animations.size() > 0 && currentStage < stageInfos.size()-1)
        {
            animations.get(animations.size()-1).stop();
        }
        if (this.currentStage < stageInfos.size()-1)
        {
            createNewAnimations(this.currentStage);
            this.currentStage++;
            animations.get(animations.size()-1).play();
            this.updateStatus();
            this.updateScrollPane(status);
        }
    }
    
    /**
     * decrement currentStage till all are gone
     */
    private void getLastAnimation()
    {
        if (this.currentStage>-1)
        {
            removeAnimation(this.currentStage);
            this.currentStage--;
            this.updateStatus();
            this.updateScrollPane(status);
        }
        if (this.currentStage < 0)
        {
            status = "";
            this.updateScrollPane(status);
        }
    }
    
    /**
     * Sets the list of stages to be used to create the animations
     * @param stageInfos
     */
    public void setStageInfos(ArrayList<StageInfo> stageInfos)
    {
        this.stageInfos = stageInfos;
        this.parseStages();
    }

    /**
     * returns the language of the project
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     * determines whether the build icon should be visible or not based on the language chosen
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
        if(language == "java"){
           
            build.setVisible(true);
            target_5.setVisible(true);
            
                    
        }else if(language == "php"){
            
            build.setVisible(false);
            target_5.setVisible(false);
            
        }
        //language selection logic goes here
    }
    
    /**
     * Create stage info from Stages List
     * @param stages List of stages
     */
    private void parseStages()
    {
        for (int i = 0; i<this.stageInfos.size(); i++)
        {
            if (this.stageInfos.get(i).getType() == StageType.CHECKOUT)
            {   
                
                this.stageInfos.get(i).setBackGround(this.backGrounds.get(i));
                this.stageInfos.get(i).setOrderNumber(i);
                this.stageInfos.get(i).setHelpButton(this.btnCheckOut);
                if (!this.stageInfos.get(i).isPassed())
                {
                    break;
                }
            }
            else if(this.stageInfos.get(i).getType() == StageType.STATIC)
            {
                this.stageInfos.get(i).setBackGround(this.backGrounds.get(i));
                this.stageInfos.get(i).setOrderNumber(i);
                this.stageInfos.get(i).setHelpButton(this.btnSA);
                if (!this.stageInfos.get(i).isPassed())
                {
                    break;
                }
            }
            else if(this.stageInfos.get(i).getType() == StageType.UNIT)
            {
                this.stageInfos.get(i).setBackGround(this.backGrounds.get(i));
                this.stageInfos.get(i).setOrderNumber(i);
                this.stageInfos.get(i).setHelpButton(this.btnUnit);
                if (!this.stageInfos.get(i).isPassed())
                {
                    break;
                }
            }
            else if(this.stageInfos.get(i).getType() == StageType.INTEGRATION)
            {
                this.stageInfos.get(i).setBackGround(this.backGrounds.get(i));
                this.stageInfos.get(i).setOrderNumber(i);
                this.stageInfos.get(i).setHelpButton(this.btnIntegration);
                if (!this.stageInfos.get(i).isPassed())
                {
                    break;
                }
            }
            else if(this.stageInfos.get(i).getType() == StageType.DEPLOY)
            {
                this.stageInfos.get(i).setBackGround(this.backGrounds.get(i));
                this.stageInfos.get(i).setOrderNumber(i);
                this.stageInfos.get(i).setHelpButton(this.btnDeploy);
                if (!this.stageInfos.get(i).isPassed())
                {
                    break;
                }
            }
            else if(this.stageInfos.get(i).getType() == StageType.BUILD)
            {
                this.stageInfos.get(i).setBackGround(this.backGrounds.get(i));
                this.stageInfos.get(i).setOrderNumber(i);
                this.stageInfos.get(i).setHelpButton(this.btnBuild);
                if (!this.stageInfos.get(i).isPassed())
                {
                    break;
                }
            }
        }
    }
}
    
