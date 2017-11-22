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
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Howtoon
 */
public class CheckoutAnimation extends StageAnimation{    
    TranslateTransition arrow1 = new TranslateTransition();
    TranslateTransition arrow2 = new TranslateTransition();
    TranslateTransition arrow3 = new TranslateTransition();
    int arrowDist = 58;
    
    
    public CheckoutAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("chkoutImage1"), 
            animationIcons.get("chkoutImage2"), 
            animationIcons.get("chkoutImage3"), 
            animationIcons.get("chkoutImagePassed"), 
            animationIcons.get("chkoutImageFailed"),
            animationIcons.get("chkoutArrow1"),
            animationIcons.get("chkoutArrow2"),
            animationIcons.get("chkoutArrow3")
        };
        super.images = _images;
        super.passImage = animationIcons.get("chkoutImagePassed");
        super.failImage = animationIcons.get("chkoutImageFailed");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Checkout animation");
        
        this.arrow1 = StageAnimation.getArrowAnimation(super.images[5], arrowDist);
        this.arrow2 = StageAnimation.getArrowAnimation(super.images[6], arrowDist);
        this.arrow3 = StageAnimation.getArrowAnimation(super.images[7], -arrowDist);
    }

    @Override
    public void stop() {
        System.out.println("Stop Checkout animation");
        
        this.arrow1.stop();
        StageAnimation.resetArrow(super.images[5]);
        
        this.arrow2.stop();
        StageAnimation.resetArrow(super.images[6]);

        this.arrow3.stop();
        StageAnimation.resetArrow(super.images[7]);  
    }
}
