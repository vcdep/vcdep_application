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
class CheckoutAnimation extends StageAnimation{
    
    public CheckoutAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
        
        ImageView[] _images = {animationIcons.get("chkoutImage1"), 
            animationIcons.get("chkoutImage2"), 
            animationIcons.get("chkoutImage3"), 
            animationIcons.get("chkoutImagePassed"), 
            animationIcons.get("chkoutImageFailed")//,
            //animationIcons.get("chkoutArrow1"),
            //animationIcons.get("chkoutArrow1"),
            //animationIcons.get("chkoutArrow1")
        };
        super.images = _images;
        super.passImage = animationIcons.get("chkoutImagePassed");
        super.failImage = animationIcons.get("chkoutImageFailed");
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
