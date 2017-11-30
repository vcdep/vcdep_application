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
public class DeployAnimation extends StageAnimation{
    TranslateTransition arrow1 = new TranslateTransition();
    TranslateTransition arrow2 = new TranslateTransition();
    int arrowDist = 110;
    
    public DeployAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("DeployImage1"), 
            animationIcons.get("DeployImage2"), 
            animationIcons.get("DeployImagePassed"), 
            animationIcons.get("DeployImageFailed"),
            animationIcons.get("DeployArrow1"),
            animationIcons.get("DeployArrow2")
        };
        super.images = _images;
        super.passImage = animationIcons.get("DeployImagePassed");
        super.failImage = animationIcons.get("DeployImageFailed");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Deploy animation");
        
        this.arrow1 = StageAnimation.getArrowAnimation(super.images[4], arrowDist);
        this.arrow2 = StageAnimation.getArrowAnimation(super.images[5], -arrowDist);
    }

    @Override
    public void stop() {
        System.out.println("Stop Deploy animation");
        
        this.arrow1.stop();
        StageAnimation.resetArrow(super.images[4]);
        
        this.arrow2.stop();
        StageAnimation.resetArrow(super.images[5]);
    }
}
