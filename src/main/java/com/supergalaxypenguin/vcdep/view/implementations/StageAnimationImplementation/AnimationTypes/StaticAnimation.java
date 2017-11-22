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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Howtoon
 */
public class StaticAnimation extends StageAnimation{
    TranslateTransition arrow1 = new TranslateTransition();
    TranslateTransition arrow2 = new TranslateTransition();
    TranslateTransition arrow3 = new TranslateTransition();
    TranslateTransition arrow4 = new TranslateTransition();
    
    public StaticAnimation(StageInfo info, HashMap<String, ImageView> animationIcons, Rectangle backGround) {
        super(info, backGround);

        ImageView[] _images = {animationIcons.get("SAImage1"), 
            animationIcons.get("SAImage2"), 
            animationIcons.get("SAImage3"), 
            animationIcons.get("SAImagePassed"), 
            animationIcons.get("SAImageFailed"),
            animationIcons.get("SAArrow1"),
            animationIcons.get("SAArrow2"),
            animationIcons.get("SAArrow3"),
            animationIcons.get("SAArrow4")
        };
        super.images = _images;
        super.passImage = animationIcons.get("SAImagePassed");
        super.failImage = animationIcons.get("SAImageFailed");
        super.moveToStart();
    }

    @Override
    public void play() {
        System.out.println("Play Static animation");
        
        arrow1.setNode(super.images[5]);
        arrow1.setToX(58);
        arrow1.setDuration(Duration.seconds(1));
        arrow1.cycleCountProperty().setValue(3);
        arrow1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arrow1.setToX(0);
                arrow1.setDuration(Duration.seconds(0.0001));
                arrow1.play();
                event.consume();
            }
        });
        arrow1.play();
        
        arrow2.setNode(super.images[6]);
        arrow2.setToX(58);
        arrow2.setDuration(Duration.seconds(1));
        arrow2.cycleCountProperty().setValue(3);
        arrow2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arrow2.setToX(0);
                arrow2.setDuration(Duration.seconds(0.0001));
                arrow2.play();
                event.consume();
            }
        });
        arrow2.play();
        

        arrow3.setNode(super.images[7]);
        arrow3.setToX(-58);
        arrow3.setDuration(Duration.seconds(1));
        arrow3.cycleCountProperty().setValue(3);
        arrow3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arrow3.setToX(0);
                arrow3.setDuration(Duration.seconds(0.0001));
                arrow3.play();
                event.consume();
            }
        });
        arrow3.play();
        
        arrow4.setNode(super.images[8]);
        arrow4.setToX(-58);
        arrow4.setDuration(Duration.seconds(1));
        arrow4.cycleCountProperty().setValue(3);
        arrow4.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                arrow4.setToX(0);
                arrow4.setDuration(Duration.seconds(0.0001));
                arrow4.play();
                event.consume();
            }
        });
        arrow4.play();
    }

    @Override
    public void stop() {
        System.out.println("Stop Static animation");
        
        this.arrow1.stop();
        TranslateTransition arrow1;
        arrow1 = new TranslateTransition();
        arrow1.setNode(super.images[5]);
        arrow1.setToX(0);
        arrow1.setDuration(Duration.seconds(0.0001));
        arrow1.play();
        
        this.arrow2.stop();
        TranslateTransition arrow2;
        arrow2 = new TranslateTransition();
        arrow2.setNode(super.images[6]);
        arrow2.setToX(0);
        arrow2.setDuration(Duration.seconds(0.0001));
        arrow2.play();
        
        this.arrow3.stop();
        TranslateTransition arrow3;
        arrow3 = new TranslateTransition();
        arrow3.setNode(super.images[7]);
        arrow3.setToX(0);
        arrow3.setDuration(Duration.seconds(0.0001));
        arrow3.play();
        
        this.arrow4.stop();
        TranslateTransition arrow4;
        arrow4 = new TranslateTransition();
        arrow4.setNode(super.images[8]);
        arrow4.setToX(0);
        arrow4.setDuration(Duration.seconds(0.0001));
        arrow4.play();
    }
}
