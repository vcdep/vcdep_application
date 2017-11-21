/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations.StageAnimationImplementation.AnimationTypes;

import com.supergalaxypenguin.vcdep.view.implementations.StageAnimationImplementation.StageAnimation;
import com.supergalaxypenguin.vcdep.view.implementations.StageAnimationImplementation.StageInfo;
import java.util.HashMap;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Howtoon
 */
public class IntegrationAnimation extends StageAnimation{
    
    public IntegrationAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("IntegrationImage1"), 
            animationIcons.get("IntegrationImage2"), 
            animationIcons.get("IntegrationImagePassed"), 
            animationIcons.get("IntegrationImageFailed")//,
            //animationIcons.get("IntegrationArrow1"),
            //animationIcons.get("IntegrationArrow2")
        };
        super.images = _images;
        super.passImage = animationIcons.get("IntegrationImagePassed");
        super.failImage = animationIcons.get("IntegrationImageFailed");
        super.moveToStart();
    }

    @Override
    public void play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
