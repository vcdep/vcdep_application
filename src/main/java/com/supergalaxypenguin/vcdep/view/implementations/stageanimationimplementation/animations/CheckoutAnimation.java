/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.animations;

import com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.StageAnimation;
import com.supergalaxypenguin.vcdep.domain.StageInfo;
import com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.StageAnimation;
import java.util.HashMap;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Howtoon
 */
public class CheckoutAnimation extends StageAnimation{    
    TranslateTransition arrow1 = new TranslateTransition();
    SequentialTransition sequence;
    int arrowDist = 120;
    
    
    public CheckoutAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("chkoutImage1"), 
            animationIcons.get("chkoutImage2"), 
            animationIcons.get("chkoutImagePassed"), 
            animationIcons.get("chkoutImageFailed"),
            animationIcons.get("chkoutArrow1")
        };
        super.images = _images;
        super.passImage = animationIcons.get("chkoutImagePassed");
        super.failImage = animationIcons.get("chkoutImageFailed");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Checkout animation");
        
        this.arrow1 = StageAnimation.getArrowAnimation(super.images[4], arrowDist);
        sequence = new SequentialTransition(this.arrow1);
        sequence.play();
    }

    @Override
    public void stop() {
        System.out.println("Stop Checkout animation");
        
        sequence.stop();
        StageAnimation.resetArrow(super.images[4]);
    }
}
