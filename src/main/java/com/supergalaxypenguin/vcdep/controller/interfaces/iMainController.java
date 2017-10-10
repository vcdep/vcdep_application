/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.controller.interfaces;

import com.supergalaxypenguin.vcdep.model.implementations.Model;

/**
 *
 * @author Howtoon
 */
public interface iMainController 
{
    void runPipeline();
    
    String getJenkinsURL();

    void setJenkinsURL(String jenkinsURL);

    String getGitHubURL();

    void setGitHubURL(String gitHubURL);

    String getBranchName();

    void setBranchName(String branch);

    String getLanguage();

    void setLanguage(String language);

    String[] getStages();

    void setStages(String[] stages);

    String getLocalRepo();

    void setLocalRepo(String localRepo);
}
