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
class UnitAnimation extends StageAnimation{
    
    public UnitAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);
    
        ImageView[] _images = {animationIcons.get("UnitImage1"), 
            animationIcons.get("UnitImage2"), 
            animationIcons.get("UnitImage3"), 
            animationIcons.get("UnitImagePassed"), 
            animationIcons.get("UnitImageFailed")//,
            //animationIcons.get("UnitArrow1"),
            //animationIcons.get("UnitArrow2"),
            //animationIcons.get("UnitArrow3"),
            //animationIcons.get("UnitArrow4")
        };
        super.images = _images;
        super.passImage = animationIcons.get("UnitImagePassed");
        super.failImage = animationIcons.get("UnitImageFailed");
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
