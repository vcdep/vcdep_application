/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation;

import com.supergalaxypenguin.vcdep.domain.StageInfo;
import java.util.HashMap;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * This animation is for the Deploy stage
 * @author Howtoon
 */
public class DeployAnimation extends StageAnimation{
    TranslateTransition arrow1 = new TranslateTransition();
    SequentialTransition sequence;
    int numArrows = 1;
    int duration = 6;
    int arrowDist = 122;
    
    /**
     * Param constructor for this animation
     * @param info the info associated with this stage
     * @param animationIcons the icons associated with this animation
     * @param backGround the background associated with this animation
     */
    public DeployAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("DeployImage1"), 
            animationIcons.get("DeployImage2"), 
            animationIcons.get("DeployImagePassed"), 
            animationIcons.get("DeployImageFailed"),
            animationIcons.get("DeployArrow1")
        };
        super.images = _images;
        super.passImage = animationIcons.get("DeployImagePassed");
        super.failImage = animationIcons.get("DeployImageFailed");
        super.moveToStart();
    }

    /**
     * Plays all the arrow Animations in order twice
     */
    @Override
    public void play() {
        System.out.println("Play Deploy animation");
        
        this.arrow1 = StageAnimation.getArrowAnimation(super.images[4], arrowDist, duration/numArrows);
        sequence = new SequentialTransition(this.arrow1);
        sequence.setCycleCount(2);
        sequence.play();
    }

    /**
     * Stops all the animations and resets the icons to their original positions
     */
    @Override
    public void stop() {
        System.out.println("Stop Deploy animation");
        
        sequence.stop();
        StageAnimation.resetArrow(super.images[4]);
    }
}
