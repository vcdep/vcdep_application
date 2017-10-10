/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.controller.implementations;

import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.model.implementations.Model;
import com.supergalaxypenguin.vcdep.view.implementations.ConfigurationViewController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Howtoon
 */
public class MainController implements iMainController
{
    private String jenkinsURL;
    private String gitHubURL;
    private String branchName;
    private String language;
    private String[] stages;
    private String localRepo;
    private ConfigurationViewController configurationViewController;
    private Model model;
    
    public void start(Stage stage) throws IOException
    {
        configurationViewController = new ConfigurationViewController((iMainController) this);
        model = new Model((iMainController) this);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ConfigurationScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    public void runPipeline()
    {
        model.setBuildInput(jenkinsURL, branchName);
    }
    
    public Model getModel()
    {
        return model;
    }
    
    public void setModel(Model _model)
    {
        this.model = _model;
    }
    
    public String getJenkinsURL() 
    {
        return jenkinsURL;
    }

    public void setJenkinsURL(String _jenkinsURL) 
    {
        this.jenkinsURL = _jenkinsURL;
    }

    public String getGitHubURL() 
    {
        return gitHubURL;
    }

    public void setGitHubURL(String _gitHubURL) 
    {
        this.gitHubURL = _gitHubURL;
    }

    public String getBranch() 
    {
        return branchName;
    }

    public void setBranch(String _branch) 
    {
        this.branchName = _branch;
    }

    public String getLanguage() 
    {
        return language;
    }

    public void setLanguage(String _language) 
    {
        this.language = _language;
    }

    public String[] getStages() 
    {
        return stages;
    }

    public void setStages(String[] _stages) 
    {
        this.stages = _stages;
    }

    public String getLocalRepo() 
    {
        return localRepo;
    }

    public void setLocalRepo(String _localRepo) 
    {
        this.localRepo = _localRepo;
    }
    
}
