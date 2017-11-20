/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations.StageAnimations;

import java.util.HashMap;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Howtoon
 */
class BuildAnimation extends StageAnimation{
    
    public BuildAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1"), 
            animationIcons.get("BuildImage1")
        };
        super.images = _images;
        super.moveToStart();
    }
}
