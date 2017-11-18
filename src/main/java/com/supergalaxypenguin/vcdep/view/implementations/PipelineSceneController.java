/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations;



import com.sun.media.jfxmedia.logging.Logger;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import java.net.URL;
import java.util.HashMap;
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
//import javafx.scene.control.TextAreaBuilder;
import javafx.scene.image.ImageView;
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
    private StageType type;
    private int orderNumber;
    private boolean passed;
    private static final int OFFSET = 85;
    private static final int ORIGIN = 137;
    private int yPos;
    private static final int stdHeight = 54;
    private static final int stdWidth = 48;
    private static final int wideWidth = 104;
    private static final int firstColumnX = 520;
    private static final int secondColumnX = 654;
    private static final int thirdColumnX = 791;
    private static final int passFailColumnX = 868;
    private static final int integrationMiddleColumnX = 680;
    private static final int deployMiddleColumnX = 707;
    
    private HashMap<String,ImageView> animationIcons = new HashMap<>();
    
    @FXML
    private ImageView cartoonSpaceBackground;
    @FXML
    public ImageView chkoutImage1;
    @FXML
    private ImageView chkoutImage2;
    @FXML
    private ImageView chkoutImage3;
    @FXML
    private ImageView chkoutImagePassed;
    @FXML
    private ImageView chkoutImageFailed;
    @FXML
    private ImageView SAImage1;
    @FXML
    private ImageView SAImage2;
    @FXML
    private ImageView SAImage3;
    @FXML
    private ImageView SAImagePassed;
    @FXML
    private ImageView SAImageFailed;
    @FXML
    private ImageView UnitImage1;
    @FXML
    private ImageView UnitImage2;
    @FXML
    private ImageView UnitImage3;
    @FXML
    private ImageView UnitImagePassed;
    @FXML
    private ImageView UnitImageFailed;
    @FXML
    private ImageView IntegrationImage1;
    @FXML
    private ImageView IntegrationImage2;
    @FXML
    private ImageView IntegrationImagePassed;
    @FXML
    private ImageView IntegrationImageFailed;
    @FXML
    private ImageView DeployImage1;
    @FXML
    private ImageView DeployImage2;
    @FXML
    private ImageView DeployImagePassed;
    @FXML
    private ImageView DeployImageFailed;
    
    @FXML
    private ImageView BuildImage1;
    @FXML
    private ImageView BuildImage2;
    @FXML
    private ImageView BuildImagePassed;
    @FXML
    private ImageView BuildImageFailed;
    
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
    
    @FXML
    private StageAnimation newAnimation;
    /**
     * Initializes the controller class.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stage1.setFill(Color.GRAY);
        
        this.chkoutImage1.setVisible(false);
        this.chkoutImage2.setVisible(false);
        this.chkoutImage3.setVisible(false);
        this.chkoutImageFailed.setVisible(false);
        this.chkoutImagePassed.setVisible(false);
        this.SAImage1.setVisible(false);
        this.SAImage2.setVisible(false);
        this.SAImage3.setVisible(false);
        this.SAImageFailed.setVisible(false);
        this.SAImagePassed.setVisible(false);
        this.UnitImage1.setVisible(false);
        this.UnitImage2.setVisible(false);
        this.UnitImage3.setVisible(false);
        this.UnitImageFailed.setVisible(false);
        this.UnitImagePassed.setVisible(false);
        this.IntegrationImage1.setVisible(false);
        this.IntegrationImage2.setVisible(false);
        this.IntegrationImageFailed.setVisible(false);
        this.IntegrationImagePassed.setVisible(false);
        this.DeployImage1.setVisible(false);
        this.DeployImage2.setVisible(false);
        this.DeployImageFailed.setVisible(false);
        this.DeployImagePassed.setVisible(false);
        //this.BuildImage1.setVisible(false);
        //this.BuildImage2.setVisible(false);
        this.BuildImageFailed.setVisible(false);
        this.BuildImagePassed.setVisible(false);
        
        this.animationIcons.put("chkoutImage1",this.chkoutImage1);
        this.animationIcons.put("chkoutImage2",this.chkoutImage2);
        this.animationIcons.put("chkoutImage2",this.chkoutImage2);
        this.animationIcons.put("chkoutImageFailed",this.chkoutImageFailed);
        this.animationIcons.put("chkoutImagePassed",this.chkoutImagePassed);
        this.animationIcons.put("SAImage1",this.SAImage1);
        this.animationIcons.put("SAImage2",this.SAImage2);
        this.animationIcons.put("SAImage3",this.SAImage3);
        this.animationIcons.put("SAImageFailed",this.SAImageFailed);
        this.animationIcons.put("SAImagePassed",this.SAImagePassed);
        this.animationIcons.put("UnitImage1",this.UnitImage1);
        this.animationIcons.put("UnitImage2",this.UnitImage2);
        this.animationIcons.put("UnitImage3",this.UnitImage3);
        this.animationIcons.put("UnitImageFailed",this.UnitImageFailed);
        this.animationIcons.put("UnitImagePassed",this.UnitImagePassed);
        this.animationIcons.put("IntegrationImage1",this.IntegrationImage1);
        this.animationIcons.put("IntegrationImage2",this.IntegrationImage2);
        this.animationIcons.put("IntegrationImageFailed",this.IntegrationImageFailed);
        this.animationIcons.put("IntegrationImagePassed",this.IntegrationImagePassed);
        this.animationIcons.put("DeployImage1",this.DeployImage1);
        this.animationIcons.put("DeployImage1",this.DeployImage1);
        this.animationIcons.put("DeployImageFailed",this.DeployImageFailed);
        this.animationIcons.put("DeployImagePassed",this.DeployImagePassed);
        this.animationIcons.put("BuildImage1",this.BuildImage1);
        this.animationIcons.put("BuildImage2",this.BuildImage2);
        this.animationIcons.put("BuildImageFailed",this.BuildImageFailed);
        this.animationIcons.put("BuildImagePassed",this.BuildImagePassed);
        
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
        this.DeployImage1.setVisible(true);
        
        TranslateTransition stage1 = new TranslateTransition();
        stage1.setNode(this.chkoutImage1);
        stage1.setToY(0);
        stage1.setDuration(Duration.seconds(0.001));
        stage1.play();
        this.chkoutImage1.setVisible(true);
        this.chkoutImage2.setVisible(true);
        this.animationIcons.get("UnitImage2").setVisible(true);
        newAnimation = getNextAnimation();
        newAnimation.start();
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
    public StageAnimation getNextAnimation()
    {
        newAnimation = new StageAnimation(new StageInfo(StageType.CHECKOUT, 2, true), this.animationIcons);
        
        return newAnimation;
    }
    


/**
 *
 * @author Howtoon
 */
    class StageAnimation extends Thread implements Runnable{
        private StageType type;
        private int orderNumber;
        private boolean passed;
        private static final int OFFSET = 85;
        private static final int ORIGIN = 137;
        private int yPos;
        private static final int stdHeight = 54;
        private static final int stdWidth = 48;
        private static final int wideWidth = 104;
        private static final int firstColumnX = 520;
        private static final int secondColumnX = 654;
        private static final int thirdColumnX = 791;
        private static final int passFailColumnX = 868;
        private static final int integrationMiddleColumnX = 680;
        private static final int deployMiddleColumnX = 707;
        private HashMap<String,ImageView> animationIcons;

        ImageView[] images;

        public StageType getType() {
            return type;
        }

        public void setType(StageType type) {
            this.type = type;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public boolean isPassed() {
            return passed;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }

        public StageAnimation(StageInfo info, HashMap<String,ImageView> animationIcons)
        {
            this.orderNumber = info.getOrderNumber();
            this.passed = info.isPassed();
            this.type = info.getType();
            this.yPos = ORIGIN + (this.orderNumber*OFFSET);
            this.animationIcons = animationIcons;

            if (this.type == StageType.BUILD)
            {
                this.BuildAnimation();
            }
            else if (this.type == StageType.CHECKOUT)
            {
                this.CheckoutAnimation();
            }
            else if (this.type == StageType.DEPLOY)
            {
                this.DeployAnimation();
            }
            else if (this.type == StageType.INTEGRATION)
            {
                this.IntegrationAnimation();
            }
            else if (this.type == StageType.STATIC)
            {
                this.StaticAnimation();
            }
            else if (this.type == StageType.UNIT)
            {
                this.UnitAnimation();
            }
            Logger.logMsg(0, "Logger.ERROR");
        }

        private void BuildAnimation()
        {

        }

        private void CheckoutAnimation()
        {
                System.out.println("StageAnimation.CheckoutAnimation()");
                //animationIcons.get("chkoutImage1").setFitHeight(this.stdHeight);
                //animationIcons.get("chkoutImage1").setFitWidth(this.stdWidth);
                //animationIcons.get("chkoutImage1").setX(this.firstColumnX);
                //animationIcons.get("chkoutImage1").setY(this.yPos);
                animationIcons.get("chkoutImage1").setVisible(true);
                animationIcons.get("chkoutImage2").setVisible(true);
                animationIcons.get("SAImage1").setVisible(true);
                chkoutImage3.setVisible(true);
                chkoutImageFailed.setVisible(true);
                //animationIcons.get("chkoutImage1").toFront();
        }

        private void DeployAnimation()
        {

        }

        private void IntegrationAnimation()
        {

        }

        private void StaticAnimation()
        {

        }

        private void UnitAnimation()
        {

        }
    }
}
