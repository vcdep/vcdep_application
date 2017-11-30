/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations;

import com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.StageAnimation;
import com.supergalaxypenguin.vcdep.controller.implementations.MainController;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.domain.StageInfo;
import com.supergalaxypenguin.vcdep.domain.StageType;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
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
import javafx.scene.image.Image;
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
    private static PipelineSceneController instance;   //Singleton instance
    private static iMainController controller;          //Reference to the main controller (Used to Call to Model)
    private final DropShadow shadow = new DropShadow();     // A generic drop shadow effect
    private final HashMap<String,ImageView> animationIcons = new HashMap<>();  //master list of animation icons/images
    private String log = "";    //log file as a String?
    private ArrayList<StageInfo> stageInfo = new ArrayList<>();  //info about all stages from last pipe run 
    private int currentStage;   //The stage currently active (if no stage active = -1)
    private ArrayList<Rectangle> backGrounds = new ArrayList<>();    //list of rectangle backgrounds for animations
    private ArrayList<StageAnimation> animations = new ArrayList<>();  //list of currently active animations
    private HashMap<String, Boolean> passFail = new HashMap<>();
    private Timer timer;
    private boolean isPlaying = false;
    private boolean displayLog = true;
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
    
    public final double deployX = 320.0;
    public final double deployY = 14.0;
    public final double integrateX = 440.0;
    public final double integrateY = 14.0;
    public final double staticX = 560.0;
    public final double staticY = 14.0;
    public final double unitX = 680.0;
    public final double unitY = 14.0;
    public final double buildX = 800.0;
    public final double buildY = 14.0;
    
    /**
     * Initializes the controller class.
     * runs upon load of view
     * @param url
     * @param rb
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

        stages.put(0, "Checkout");
        stages.put(1, "Static");
        stages.put(2, "Unit");
        stages.put(4, "Integration");
        stages.put(3, "Deploy");
        
        this.passFail.put("Checkout", Boolean.TRUE);
        this.passFail.put("Static", Boolean.TRUE);
        this.passFail.put("Unit", Boolean.TRUE);
        this.passFail.put("Integration", Boolean.FALSE);
        this.passFail.put("Deploy", Boolean.TRUE);
        
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
        
        parseStages(stages);
        
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
    
    public void handleBtnCheckout(ActionEvent event)
    {
        System.out.println("Test btnCheckOut");
        //Open Help Window/Description
    }
    
    public void handleBtnSA(ActionEvent event)
    {
        System.out.println("Test btnSA");
        //Open Help Window/Description
    }
    
    public void handleBtnUnit(ActionEvent event)
    {
        System.out.println("Test btnUnit");
        //Open Help Window/Description
    }
    
    public void handleBtnIntegration(ActionEvent event)
    {
        System.out.println("Test btnIntegration");
        //Open Help Window/Description
    }
    
    public void handleBtnDeploy(ActionEvent event)
    {
        System.out.println("Test btnDeploy");
        //Open Help Window/Description
    }
    
    public void handleBtnBuild(ActionEvent event)
    {
        System.out.println("Test btnBuild");
        //Open Help Window/Description
    }
    
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
                if(currentStage == stageInfo.size()-1)
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
        this.label.setText("");
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
        this.label.setText("");
    }
    
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
     * Reset button should simply reset the drag and drop to its original position
     * @param event 
     */
    @FXML
    private void handleBtnReset(ActionEvent event)
    {
        System.out.println("Test Reset Button");
    }
    
    /**
     * ReSubmit button should clear all current animations and seek an updated log file to build new animations
     * @param event 
     */
    @FXML
    private void handleBtnReSubmit(ActionEvent event)
    {
        System.out.println("Test Re-Submit Button");
    }
    
    private void updateStatus()
    {
    
        if (stages.get(this.currentStage).equalsIgnoreCase("Checkout"))
        {
            if (this.displayLog)
                this.status = ((MainController) controller).getCheckoutStatus();
            else
                this.status = ((MainController) controller).parseCheckout();
        }
        else if (stages.get(this.currentStage).equalsIgnoreCase("Static"))
        {
            if (this.displayLog)
                this.status = ((MainController) controller).getStaticAnalysisStatus();
            else
                this.status = ((MainController) controller).parseStaticAnalysis();
        }
        else if (stages.get(this.currentStage).equalsIgnoreCase("Unit"))
        {
            if (this.displayLog)
                this.status = ((MainController) controller).getUnitTestStatus();
            else
                this.status = ((MainController) controller).parseUnitTests();
        }
        else if (stages.get(this.currentStage).equalsIgnoreCase("Integration"))
        {
            if (this.displayLog)
                this.status = ((MainController) controller).getIntegrationStatus();
            else
                this.status = ((MainController) controller).parseIntegration();
        }
        else if (stages.get(this.currentStage).equalsIgnoreCase("Deploy"))
        {
            if (this.displayLog)
                this.status = ((MainController) controller).getDeploymentStatus();
            else
                this.status = ((MainController) controller).parseDeployment();
        }
        else if (stages.get(this.currentStage).equalsIgnoreCase("Build"))
        {
            if (this.displayLog)
                this.status = ((MainController) controller).getBuildStatus();
            else
                this.status = ((MainController) controller).parseBuild();
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
        if (this.displayLog)
        {
            this.btnLogAndScript.textProperty().set("Script");
            this.updateScrollPane(this.status);
        }
        else
        {
            this.btnLogAndScript.textProperty().set("Log File");
            this.updateScrollPane(this.status);
        }
        this.displayLog = !this.displayLog;
        this.updateStatus();
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
        animations.add(StageAnimation.getInstance(stageInfo.get(i+1), this.animationIcons, backGrounds.get(i+1)));
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
        if (animations.size() > 0 && currentStage < stageInfo.size()-1)
        {
            animations.get(animations.size()-1).stop();
        }
        if (this.currentStage < stageInfo.size()-1)
        {
            createNewAnimations(this.currentStage);
            this.currentStage++;
            animations.get(animations.size()-1).play();
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
        }
    }
    /**
     * Create stage info from Stages List
     * @param stages List of stages
     */
    private void parseStages(HashMap<Integer, String> stages)
    {
            for (int i = 0; i<stages.size(); i++)
        {
            if (stages.get(i).compareToIgnoreCase("Checkout")==0)
            {   
                this.stageInfo.add(new StageInfo(StageType.CHECKOUT, i, this.passFail.get("Checkout"), backGrounds.get(i), this.helpButtons));
                if (!this.stageInfo.get(i).isPassed())
                {
                    break;
                }
            }
            else if(stages.get(i).compareToIgnoreCase("Static")==0)
            {
                this.stageInfo.add(new StageInfo(StageType.STATIC, i, this.passFail.get("Static"), backGrounds.get(i), this.helpButtons));
                if (!this.stageInfo.get(i).isPassed())
                {
                    break;
                }
            }
            else if(stages.get(i).compareToIgnoreCase("Unit")==0)
            {
                this.stageInfo.add(new StageInfo(StageType.UNIT, i, this.passFail.get("Unit"), backGrounds.get(i), this.helpButtons));
                if (!this.stageInfo.get(i).isPassed())
                {
                    break;
                }
            }
            else if(stages.get(i).compareToIgnoreCase("Integration")==0)
            {
                this.stageInfo.add(new StageInfo(StageType.INTEGRATION, i, this.passFail.get("Integration"), backGrounds.get(i), this.helpButtons));
                if (!this.stageInfo.get(i).isPassed())
                {
                    break;
                }
            }
            else if(stages.get(i).compareToIgnoreCase("Deploy")==0)
            {
                this.stageInfo.add(new StageInfo(StageType.DEPLOY, i, this.passFail.get("Deploy"), backGrounds.get(i), this.helpButtons));
                if (!this.stageInfo.get(i).isPassed())
                {
                    break;
                }
            }
            else if(stages.get(i).compareToIgnoreCase("Build")==0)
            {
                this.stageInfo.add(new StageInfo(StageType.BUILD, i, this.passFail.get("Build"), backGrounds.get(i), this.helpButtons));
                if (!this.stageInfo.get(i).isPassed())
                {
                    break;
                }
            }
        }
    }
}
    
