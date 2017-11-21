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
class StaticAnimation extends StageAnimation{

    public StaticAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);

        ImageView[] _images = {animationIcons.get("SAImage1"), 
            animationIcons.get("SAImage2"), 
            animationIcons.get("SAImage3"), 
            animationIcons.get("SAImagePassed"), 
            animationIcons.get("SAImageFailed")//,
            //animationIcons.get("SAArrow1"),
            //animationIcons.get("SAArrow2"),
            //animationIcons.get("SAArrow3"),
            //animationIcons.get("SAArrow4")
        };
        super.images = _images;
        super.passImage = animationIcons.get("SAImagePassed");
        super.failImage = animationIcons.get("SAImageFailed");
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
