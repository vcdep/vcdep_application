/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation;

import com.supergalaxypenguin.vcdep.domain.StageInfo;
import com.supergalaxypenguin.vcdep.domain.StageType;
import java.util.HashMap;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This Class contains all the necessary information to create
 * a modular animation and control it throughout it's life-cycle.
 * @author Howtoon
 */
public abstract class StageAnimation{

    /**
     * Enum of the StageType associated with this stage
     */
    protected StageType type;         //What kind of Stage is it? Only one of each type is allowed

    /**
     * The order in which this stage is to be displayed
     * Starts at 0
     */
    protected int orderNumber;            //what number in the list of stages EG. 1st? 2nd? Starts at 0

    /**
     * Whether or not this stage passed
     */
    protected boolean passed;         //did this stage fail?

    /**
     * This is the distance between the middle of stage backgrounds
     * It is used to move each animation into position
     */
    protected static final int OFFSET = 85;       //Space between middle of other stage backgrounds

    /**
     * The background associated with this stage
     * It is dependent on the order that this stage is to be displayed in.
     */
    protected Rectangle backGround;//The background Area

    /**
     * The help button associated with this stage
     */
    protected Button helpButton;

    /**
     * The images that are necessary to animate this stage
     */
    protected ImageView[] images;

    /**
     * The image to be displayed if this stage passed
     */
    protected ImageView passImage;

    /**
     * The image to be displayed if this stage failed
     */
    protected ImageView failImage;
//        private static final int ORIGIN = 137; 
//        private static final int stdHeight = 54;
//        private static final int stdWidth = 48;
//        private static final int wideWidth = 104;
//        private static final int firstColumnX = 520;
//        private static final int secondColumnX = 654;
//        private static final int thirdColumnX = 791;
//        private static final int passFailColumnX = 868;
//        private static final int integrationMiddleColumnX = 680;
//        private static final int deployMiddleColumnX = 707;


    /**
     * Creates a Stage Animation
     * @param info StageInfo class containing stage information
     * @param animationIcons    All animation images
     * @param backGround    The background that corresponds to this stage animation
     * @return 
     */
    public static StageAnimation getInstance(StageInfo info, HashMap<String,ImageView> animationIcons, Rectangle backGround)
    {
        
        if (info.getType() == StageType.CHECKOUT){
                return new CheckoutAnimation(info, animationIcons, backGround);
        }
        if (info.getType() == StageType.BUILD){
                return new BuildAnimation(info, animationIcons, backGround);
        }
        if (info.getType() == StageType.DEPLOY){
                return new DeployAnimation(info, animationIcons, backGround);
        }
        if (info.getType() == StageType.INTEGRATION){
            return new IntegrationAnimation(info, animationIcons, backGround);
        }
        if (info.getType() == StageType.STATIC){
            return new StaticAnimation(info, animationIcons, backGround);
        }
        else{
            return new UnitAnimation(info, animationIcons, backGround);
        }
    }

    /**
     * Param Constructor
     * @param info
     * @param backGround
     */
    protected StageAnimation(StageInfo info, Rectangle backGround)
    {
        this.orderNumber = info.getOrderNumber();
        this.passed = info.isPassed();
        this.type = info.getType();
        this.helpButton = info.getHelpButton();
        this.backGround = backGround;
    }

    /**
     * Returns an moving animation
     * @param _image image to be moved
     * @param X how far in the X direction to move the image
     * @param duration how long the animation should last
     * @return
     */
    protected static TranslateTransition getArrowAnimation(ImageView _image, int X, int duration)
    {
        final TranslateTransition arrow = new TranslateTransition();
        final ImageView image = _image;
        
        image.setVisible(true);
        arrow.setNode(image);
        arrow.setToX(X);
        arrow.setDuration(Duration.seconds(duration));
        arrow.cycleCountProperty().setValue(1);
        return arrow;
    }
    
    /**
     * resets the image to its original position
     * @param image
     */
    protected static void resetArrow(ImageView image)
    {
        TranslateTransition arrow1;
        arrow1 = new TranslateTransition();
        arrow1.setNode(image);
        arrow1.setToX(0);
        arrow1.setDuration(Duration.seconds(0.0001));
        arrow1.play();
    }

    /**
     * Moves all Icons and the button to the proper stage position
     */
    protected void moveToStart()
    {
        TranslateTransition moveToStart;
        this.backGround.setVisible(true);

        moveToStart = new TranslateTransition();
        moveToStart.setNode(this.helpButton);
        moveToStart.setToY(this.orderNumber*OFFSET);
        moveToStart.setDuration(Duration.seconds(0.0001));
        moveToStart.play();
        this.helpButton.setVisible(true);

        for (ImageView i : images)
        {
            moveToStart = new TranslateTransition();
            moveToStart.setNode(i);
            moveToStart.setToY(this.orderNumber*OFFSET);
            moveToStart.setDuration(Duration.seconds(0.0001));
            if (i == this.passImage)
            {
                i.setVisible(this.passed);
            }
            else if (i == this.failImage)
            {
                i.setVisible(!this.passed);
            }
            else{
                i.setVisible(true);
            }
            moveToStart.play();
        }
    }
    /**
     * Move Icons and button back to their original positions
     */
    public void moveToEnd()
    {
        TranslateTransition moveToEnd;
        moveToEnd = new TranslateTransition();
        moveToEnd.setNode(this.helpButton);
        moveToEnd.setToY(0);
        moveToEnd.setDuration(Duration.seconds(0.0001));
        moveToEnd.play();
        this.helpButton.setVisible(false);
        this.backGround.setVisible(false);
        for (ImageView i : this.images){
            moveToEnd = new TranslateTransition();
            moveToEnd.setNode(i);
            moveToEnd.setToY(0);
            moveToEnd.setDuration(Duration.seconds(0.0001));
            moveToEnd.play();
            i.setVisible(false);
        }
    }
    
    /**
     * plays the animation
     */
    public abstract void play();
    
    /**
     * stops the animation
     */
    public abstract void stop();
}