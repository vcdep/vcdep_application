/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.domain;

import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

/**
 *  This object holds all the information about a stage
 * and is used to create the animations and hold the script and log info about each stage.
 * @author Howtoon
 */
public class StageInfo {
    private StageType type;
    private int orderNumber;
    private boolean passed;
    private Rectangle backGround;
    private Button helpButton;
    private String logChunk;
    private String script;
    
    /**
     *  Empty constructor
     */
    public StageInfo(){
        
    }
    
    /**
     * Param constructor
     * @param type
     * @param orderNumber
     * @param passed
     * @param backGround 
     * @param helpButtons 
     */
    public StageInfo(StageType type, int orderNumber, boolean passed, Rectangle backGround, HashMap<StageType, Button> helpButtons)
    {
        this.type = type;
        this.orderNumber = orderNumber;
        this.passed = passed;
        this.backGround = backGround;
        this.helpButton = helpButtons.get(type);
    }
    
    /**
     * returns the help button associated with this stage
     * @return
     */
    public Button getHelpButton() {
        return helpButton;
    }

    /**
     * sets the help button associated with this stage
     * @param helpButton
     */
    public void setHelpButton(Button helpButton) {
        this.helpButton = helpButton;
    }
    
    /**
     * Background of stage
     * @return Rectangle
     */
    public Rectangle getBackGround() {
        return backGround;
    }

    /**
     * Background of stage
     * @param backGround stage number should match orderNumber
     */
    public void setBackGround(Rectangle backGround) {
        this.backGround = backGround;
    }

    /**
     * Enum
     * StageType."stage_name"
     * 
     * @return 
     */
    public StageType getType() {
        return type;
    }

    /**
     * Enum
     * StageType."stage_name"
     *  
     * @param type
     */
    public void setType(StageType type) {
        this.type = type;
    }

    /**
     * What order the stage goes in
     * @return 
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * What order the stage goes in
     * 
     * @param orderNumber
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Whether or not the stage passed
     * @return 
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * Whether or not the stage passed
     * @param passed
     * @return 
     */
    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    /**
     * returns the portion of the log file associated with this stage
     * @return 
     */
    public String getLogChunk() {
        return logChunk;
    }

    /**
     * sets the portion of the log file associated with this stage
     * @param logChunk 
     */
    public void setLogChunk(String logChunk) {
        this.logChunk = logChunk;
    }

    /**
     * returns the script text associated with this stage
     * @return 
     */
    public String getScript() {
        return script;
    }

    /**
     * sets the script text associated with this stage
     * @param script 
     */
    public void setScript(String script) {
        this.script = script;
    }
}
