/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.model.implementations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natha
 */
public class Pipeline
{

    private String language;
    private List<String> stages = new ArrayList<String>();

    public Pipeline(String language, List<String> stages)
    {

        this.language = language;
        this.stages = stages;

    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getLanguage()
    {
        return this.language;
    }

    public List<String> getStages()
    {
        return stages;
    }

    public void setStage(String stage)
    {
        this.stages.add(stage);
    }
    
}
