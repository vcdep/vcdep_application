/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.controller.implementations;

import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.model.implementations.Model;
import com.supergalaxypenguin.vcdep.view.implementations.ConfigurationViewController;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
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
    private String localGitRepo;
    private final ConfigurationViewController configurationViewController;
    private final Model model;
    private Stage stage;
    private static MainController instance;
    
    /**
     * creates the MainController, Model, and ConfigurationViewController
     */
    public MainController()
    {
        this.instance = this;
        configurationViewController = new ConfigurationViewController();
        model = new Model((iMainController) this);
    }
    
    /**
     * displays the DirectoryChooser
     * @return Returns the absolute path of the directory chosen
     */
    public File displayDirectoryChooser()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(stage);
                
    }
    
    /**
     * displays the ConfigurationScene
     * @param _stage the window created by JavaFX
     * @throws java.io.IOException
     */
    public void displayConfigurationScene(Stage _stage) throws IOException
    {
        stage = _stage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ConfigurationScene.fxml"));
        this.configurationViewController.setMainControllerInterface((iMainController) this);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * 
     */
    public void runPipeline()
    {
        model.setBuildInput(jenkinsURL, branchName);
    }
    
    /**
     * Returns the Jenkins URL
     * @return jenkinsURL the URL of the Jenkins server
     */
    public String getJenkinsURL() 
    {
        return jenkinsURL;
    }

    /**
     * Sets the Jenkins URL address to the correct instance field for later use
     * @param jenkinsURL the URL of the Jenkins server
     */
    public void setJenkinsURL(String jenkinsURL) 
    {
        this.jenkinsURL = jenkinsURL;
    }

    /**
     * Returns the GitHub URL
     * @return gitHubURL the URL of the specific remote repository
     */
    public String getGitHubURL() 
    {
        return gitHubURL;
    }

    /**
     * Sets the GitHub URL address to the correct instance field for later use
     * @param gitHubURL the URL of the specific remote repository
     */
    public void setGitHubURL(String gitHubURL) 
    {
        this.gitHubURL = gitHubURL;
    }

    /**
     * Returns the branch name
     * @return branchName the specific branch of the remote repository to access
     */
    public String getBranchName() 
    {
        return branchName;
    }

    /**
     * Sets the name to the correct instance field for later use with specifying which branch to access
     * @param branch the specific branch of the remote repository to access
     */
    public void setBranchName(String branch) 
    {
        this.branchName = branch;
    }

    /**
     * Returns the programming language of the remote repository application
     * @return language the programming language that the remote repository application is written in
     */
    public String getLanguage() 
    {
        return language;
    }

    /**
     * Sets the programming language to the correct instance field for determining the type of pipeline to create
     * @param language the programming language that the remote repository application is written in
     */
    public void setLanguage(String language) 
    {
        this.language = language;
    }

    /**
     * Returns the order of the pipeline stages in an array of Strings
     * @return stages an array containing the order of the stages to be run in the pipeline
     */
    public String[] getStages() 
    {
        return stages;
    }

    /**
     * Sets the order of stages for the pipeline to run
     * @param stages an array of Strings that holds the order of the stages to run
     */
    public void setStages(String[] stages) 
    {
        this.stages = stages;
    }

    /**
     * Returns the path to the local git repository
     * @return localGitRepo the path to the local git repository
     */
    public String getLocalRepo() 
    {
        return localGitRepo;
    }

    /**
     * Sets the path of the local git repository to the correct instance field for later use
     * @param localGitRepo the path to the local git repository
     */
    public void setLocalRepo(String localGitRepo) 
    {
        this.localGitRepo = localGitRepo;
    }
}
