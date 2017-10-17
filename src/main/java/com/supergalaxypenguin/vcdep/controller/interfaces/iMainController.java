/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.controller.interfaces;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main Controller Interface
 * @author Howtoon
 */
public interface iMainController 
{
    /**
     * displays the DirectoryChooser
     * @return Returns the absolute path of the directory chosen
     */
    File displayDirectoryChooser();
    
    /**
     * displays the ConfigurationScene
     * @throws java.io.IOException
     */
    void displayConfigurationScene() throws IOException;
    
    /**
     * displays the PipelineScene
     * @throws IOException 
     */
    void displayPipelineScene() throws IOException;
    
    /**
     * Returns the String associated with the next step in the process
     * @return Returns a String containing the information about the next step
     */
    String stepForward();
    
    /**
     * Returns the String associated with the last step in the process
     * @return Returns a String containing the information about the last step
     */
    String stepBackward();
    
    /**
     * Store the Log File from the Jenkins Server
     * @param _logFile stores the log file the was received from the Jenkins Server
     */
    void setLogFile(String _logFile);
    
    /**
     * Gets the last Log File received from the Jenkins Server
     * @return Returns a String containing the file.
     */
    String getLogFile();
    
    /**
     * Sets the Java FX Stage
     * @param _javaFXStage the JavaFX stage for the application
     */
    void setJavaFXStage(Stage _stage);
    
    /**
     * Runs the initializes and runs the pipeline.
     * 
     */
    void runPipeline();
    
    /**
     * Returns the Jenkins URL
     * @return jenkinsURL the URL of the Jenkins server
     */
    String getJenkinsURL();

    /**
     * Sets the Jenkins URL address to the correct instance field for later use
     * @param jenkinsURL the URL of the Jenkins server
     */
    void setJenkinsURL(String jenkinsURL);

    /**
     * Returns the GitHub URL
     * @return gitHubURL the URL of the specific remote repository
     */
    String getGitHubURL();

    /**
     * Sets the GitHub URL address to the correct instance field for later use
     * @param gitHubURL the URL of the specific remote repository
     */
    void setGitHubURL(String gitHubURL);

    /**
     * Returns the branch name
     * @return branchName the specific branch of the remote repository to access
     */
    String getBranchName();

    /**
     * Sets the name to the correct instance field for later use with specifying which branch to access
     * @param branch the specific branch of the remote repository to access
     */
    void setBranchName(String branch);

    /**
     * Returns the programming language of the remote repository application
     * @return language the programming language that the remote repository application is written in
     */
    String getLanguage();

    /**
     * Sets the programming language to the correct instance field for determining the type of pipeline to create
     * @param language the programming language that the remote repository application is written in
     */
    void setLanguage(String language);

    /**
     * Returns the order of the pipeline stages in an array of Strings
     * @return stages an array containing the order of the stages to be run in the pipeline
     */
    String[] getStages();

    /**
     * Sets the order of stages for the pipeline to run
     * @param stages an array of Strings that holds the order of the stages to run
     */
    void setStages(String[] stages);

    /**
     * Returns the path to the local git repository
     * @return localGitRepo the path to the local git repository
     */
    String getLocalRepo();

    /**
     * Sets the path of the local git repository to the correct instance field for later use
     * @param localGitRepo the path to the local git repository
     */
    void setLocalRepo(String localGitRepo);
}
