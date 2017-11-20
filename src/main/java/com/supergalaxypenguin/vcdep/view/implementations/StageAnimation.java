/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations;

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
class StageAnimation{
    private StageType type;         //What kind of Stage is it? Only one of each type is allowed
    private int orderNumber;            //what number in the list of stages EG. 1st? 2nd? Starts at 0
    private boolean passed;         //did this stage fail?
    private static final int OFFSET = 85;       //Space between middle of other stage backgrounds
    //private static final int ORIGIN = 137;      
    private Rectangle backGround;//The background Area
    private Button helpButton;
    HashMap<String,ImageView> animationIcons;
//        private static final int stdHeight = 54;
//        private static final int stdWidth = 48;
//        private static final int wideWidth = 104;
//        private static final int firstColumnX = 520;
//        private static final int secondColumnX = 654;
//        private static final int thirdColumnX = 791;
//        private static final int passFailColumnX = 868;
//        private static final int integrationMiddleColumnX = 680;
//        private static final int deployMiddleColumnX = 707;
    private ImageView[] images;                             //necessary Animations

    /**
     * Creates a Stage Animation
     * @param info StageInfo class containing stage information
     * @param animationIcons    All animation images
     * @param backGround    The background that corresponds to this stage animation
     */
    public StageAnimation(StageInfo info, HashMap<String,ImageView> animationIcons, Rectangle backGround)
    {
        this.orderNumber = info.getOrderNumber();
        this.passed = info.isPassed();
        this.type = info.getType();
        this.backGround = backGround;
        this.helpButton = info.getHelpButton();
        this.animationIcons = animationIcons;

        //this.animationIcons = animationIcons;

        if (null != this.type)
        switch (this.type) {
            case BUILD:
                this.BuildAnimation(animationIcons);
                break;
            case CHECKOUT:
                this.CheckoutAnimation(animationIcons);
                break;
            case DEPLOY:
                this.DeployAnimation(animationIcons);
                break;
            case INTEGRATION:
                this.IntegrationAnimation(animationIcons);
                break;
            case STATIC:
                this.StaticAnimation(animationIcons);
                break;
            case UNIT:
                this.UnitAnimation(animationIcons);
                break;
            default:
                break;
        }
    }

    /**
     * Gathers images for the Build Animation
     * @param animationIcons All animation icons
     */
    private void BuildAnimation(HashMap<String, ImageView> animationIcons)
    {
        ImageView[] images = {animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1")
        };
        this.images = images;
        this.moveToStart(images);
    }

    /**
     * Gathers images for the Checkout Animation
     * @param animationIcons All animation icons
     */
    private void CheckoutAnimation(HashMap<String,ImageView> animationIcons)
    {
        ImageView[] images = {animationIcons.get("chkoutImage1"), 
            animationIcons.get("chkoutImage2"), 
            animationIcons.get("chkoutImage3"), 
            animationIcons.get("chkoutImagePassed"), 
            animationIcons.get("chkoutImageFailed")//,
            //animationIcons.get("chkoutArrow1"),
            //animationIcons.get("chkoutArrow1"),
            //animationIcons.get("chkoutArrow1")
        };
        this.images = images;
        this.moveToStart(images);
    }

    /**
     * Gathers images for the Deployment Animation
     * @param animationIcons All animation icons
     */
    private void DeployAnimation(HashMap<String,ImageView> animationIcons)
    {
        ImageView[] images = {animationIcons.get("DeployImage1"), 
            animationIcons.get("DeployImage2"), 
            animationIcons.get("DeployImagePassed"), 
            animationIcons.get("DeployImageFailed")//,
            //animationIcons.get("DeployArrow1"),
            //animationIcons.get("DeployArrow2")
        };
        this.images = images;
        this.moveToStart(images);
    }

    /**
     * Gathers images for the Integration Test Animation
     * @param animationIcons All animation icons
     */
    private void IntegrationAnimation(HashMap<String,ImageView> animationIcons)
    {
        ImageView[] images = {animationIcons.get("IntegrationImage1"), 
            animationIcons.get("IntegrationImage2"), 
            animationIcons.get("IntegrationImagePassed"), 
            animationIcons.get("IntegrationImageFailed")//,
            //animationIcons.get("IntegrationArrow1"),
            //animationIcons.get("IntegrationArrow2")
        };
        this.images = images;
        this.moveToStart(images);
    }

    /**
     * Gathers images for the StaticAnalysis Animation
     * @param animationIcons All animation icons
     */
    private void StaticAnimation(HashMap<String,ImageView> animationIcons)
    {
        ImageView[] images = {animationIcons.get("SAImage1"), 
            animationIcons.get("SAImage2"), 
            animationIcons.get("SAImage3"), 
            animationIcons.get("SAImagePassed"), 
            animationIcons.get("SAImageFailed")//,
            //animationIcons.get("SAArrow1"),
            //animationIcons.get("SAArrow2"),
            //animationIcons.get("SAArrow3"),
            //animationIcons.get("SAArrow4")
        };
        this.images = images;
        this.moveToStart(images);
    }
    /**
     * Gathers images for the UnitTest Animation
     * @param animationIcons All animation icons
     */
    private void UnitAnimation(HashMap<String,ImageView> animationIcons)
    {
        ImageView[] images = {animationIcons.get("UnitImage1"), 
            animationIcons.get("UnitImage2"), 
            animationIcons.get("UnitImage3"), 
            animationIcons.get("UnitImagePassed"), 
            animationIcons.get("UnitImageFailed")//,
            //animationIcons.get("UnitArrow1"),
            //animationIcons.get("UnitArrow2"),
            //animationIcons.get("UnitArrow3"),
            //animationIcons.get("UnitArrow4")
        };
        this.images = images;
        this.moveToStart(images);
    }
    /**
     * Move Icons to the proper stage position
     * @param images All images necessary for the animation;
     */
    private void moveToStart(ImageView[] images)
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
            if ( 
                    i == animationIcons.get("UnitImagePassed") ||
                    i == animationIcons.get("SAImagePassed") ||
                    i == animationIcons.get("DeployImagePassed") ||
                    i == animationIcons.get("IntegrationImagePassed") ||
                    i == animationIcons.get("chkoutImagePassed")
                    )
            {
                i.setVisible(passed);
            }
            else if (
                    i == animationIcons.get("UnitImageFailed") ||
                    i == animationIcons.get("SAImageFailed") ||
                    i == animationIcons.get("DeployImageFailed") ||
                    i == animationIcons.get("IntegrationImageFailed") ||
                    i == animationIcons.get("chkoutImageFailed")
                    )
            {
                i.setVisible(!passed);
            }
            else{
                i.setVisible(true);
            }
            moveToStart.play();
        }
    }
    /**
     * Move Icons back to their original positions
     */
    public void moveToEnd()
    {
        TranslateTransition moveToEnd;

        moveToEnd = new TranslateTransition();
        moveToEnd.setNode(this.helpButton);
        moveToEnd.setToY(0);
        moveToEnd.setDuration(Duration.seconds(1));
        moveToEnd.play();
        this.helpButton.setVisible(false);

        this.backGround.setVisible(false);
        for (ImageView i : images){
            moveToEnd = new TranslateTransition();
            moveToEnd.setNode(i);
            moveToEnd.setToY(0);
            moveToEnd.setDuration(Duration.seconds(0.00001));
            moveToEnd.play();
            i.setVisible(false);
        }
    }
}