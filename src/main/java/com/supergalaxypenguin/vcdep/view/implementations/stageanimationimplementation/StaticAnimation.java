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
 *
 * @author Howtoon
 */
public class StaticAnimation extends StageAnimation{
    TranslateTransition arrow1 = new TranslateTransition();
    TranslateTransition arrow2 = new TranslateTransition();
    SequentialTransition sequence;
    int numArrows = 2;
    int duration = 6;
    int arrowDist = 122;
    int returnDist = 136;
    
    public StaticAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);

        ImageView[] _images = {animationIcons.get("SAImage1"), 
            animationIcons.get("SAImage2"),
            animationIcons.get("SAImagePassed"), 
            animationIcons.get("SAImageFailed"),
            animationIcons.get("SAArrow1"),
            animationIcons.get("SAArrow2")
        };
        super.images = _images;
        super.passImage = animationIcons.get("SAImagePassed");
        super.failImage = animationIcons.get("SAImageFailed");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Static animation");
        
        this.arrow1 = StageAnimation.getArrowAnimation(super.images[4], arrowDist, duration/numArrows);
        this.arrow2 = StageAnimation.getArrowAnimation(super.images[5], -returnDist, duration/numArrows);
        sequence = new SequentialTransition(this.arrow1, this.arrow2);
        sequence.setCycleCount(2);
        sequence.play();
    }

    @Override
    public void stop() {
        System.out.println("Stop Static animation");
        
        sequence.stop();
        StageAnimation.resetArrow(super.images[4]);

        StageAnimation.resetArrow(super.images[5]);
    }
}
