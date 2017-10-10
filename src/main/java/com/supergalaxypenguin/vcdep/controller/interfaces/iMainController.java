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
    
    Model getModel();
    
    void setModel(Model _model);
    
    String getJenkinsURL();

    void setJenkinsURL(String _jenkinsURL);

    String getGitHubURL();

    void setGitHubURL(String _gitHubURL);

    String getBranch();

    void setBranch(String _branch);

    String getLanguage();

    void setLanguage(String _language);

    String[] getStages();

    void setStages(String[] _stages);

    String getLocalRepo();

    void setLocalRepo(String _localRepo);
}
