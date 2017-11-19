/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.view.implementations;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Howtoon
 */
public class StageInfo {
    private StageType type;
    private int orderNumber;
    private boolean passed;
    private Rectangle backGround;

    public Rectangle getBackGround() {
        return backGround;
    }

    public void setBackGround(Rectangle backGround) {
        this.backGround = backGround;
    }
    
    public StageInfo(StageType type, int orderNumber, boolean passed, Rectangle backGround)
    {
        this.type = type;
        this.orderNumber = orderNumber;
        this.passed = passed;
        this.backGround = backGround;
    }

    public StageType getType() {
        return type;
    }

    public void setType(StageType type) {
        this.type = type;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
