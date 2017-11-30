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
public class BuildAnimation extends StageAnimation{
    
    TranslateTransition arrow1 = new TranslateTransition();
    SequentialTransition sequence;
    int arrowDist = 122;
    
    public BuildAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage2"), 
            animationIcons.get("BuildImagePassed"), 
            animationIcons.get("BuildImageFailed"), 
            animationIcons.get("BuildArrow1")
        };
        super.images = _images;
        super.passImage = animationIcons.get("");
        super.failImage = animationIcons.get("");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Build animation");
        
        this.arrow1 = StageAnimation.getArrowAnimation(super.images[4], arrowDist);
        sequence = new SequentialTransition(this.arrow1);
        sequence.play();
    }

    @Override
    public void stop() {
        System.out.println("Stop Build animation");
        
        sequence.stop();
        StageAnimation.resetArrow(super.images[4]);
    }
}
