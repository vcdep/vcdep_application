/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.animations;

import com.supergalaxypenguin.vcdep.domain.StageInfo;
import com.supergalaxypenguin.vcdep.view.implementations.stageanimationimplementation.StageAnimation;
import java.util.HashMap;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Howtoon
 */
public class BuildAnimation extends StageAnimation{
    
    public BuildAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1")
        };
        super.images = _images;
        super.passImage = animationIcons.get("");
        super.failImage = animationIcons.get("");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Build animation");
        
    }

    @Override
    public void stop() {
        System.out.println("Stop Build animation");
    }
}
