/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.animationtypes;

import com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.StageAnimation;
import com.supergalaxypenguin.vcdep.domain.StageInfo;
import java.util.HashMap;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Howtoon
 */
public class StaticAnimation extends StageAnimation{
    TranslateTransition arrow1 = new TranslateTransition();
    TranslateTransition arrow2 = new TranslateTransition();
    TranslateTransition arrow3 = new TranslateTransition();
    TranslateTransition arrow4 = new TranslateTransition();
    int arrowDist = 58;
    
    public StaticAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);

        ImageView[] _images = {animationIcons.get("SAImage1"), 
            animationIcons.get("SAImage2"), 
            animationIcons.get("SAImage3"), 
            animationIcons.get("SAImagePassed"), 
            animationIcons.get("SAImageFailed"),
            animationIcons.get("SAArrow1"),
            animationIcons.get("SAArrow2"),
            animationIcons.get("SAArrow3"),
            animationIcons.get("SAArrow4")
        };
        super.images = _images;
        super.passImage = animationIcons.get("SAImagePassed");
        super.failImage = animationIcons.get("SAImageFailed");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Static animation");
        
        this.arrow1 = StageAnimation.getArrowAnimation(super.images[5], arrowDist);
        this.arrow2 = StageAnimation.getArrowAnimation(super.images[6], arrowDist);
        this.arrow3 = StageAnimation.getArrowAnimation(super.images[7], -arrowDist);
        this.arrow4 = StageAnimation.getArrowAnimation(super.images[8], -arrowDist);
    }

    @Override
    public void stop() {
        System.out.println("Stop Static animation");
        
        this.arrow1.stop();
        StageAnimation.resetArrow(super.images[5]);
        
        this.arrow2.stop();
        StageAnimation.resetArrow(super.images[6]);

        this.arrow3.stop();
        StageAnimation.resetArrow(super.images[7]); 
        
        this.arrow4.stop();
        StageAnimation.resetArrow(super.images[8]);
    }
}
