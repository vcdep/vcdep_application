/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.model.implementations;

import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;

/**
 *
 * @author nathan
 */
public class Model
{
    
    // All instance variables for defining a Jenkins Pipeline
    private String jenkinsURL;
    private String gitHubURL;
    private String branchName;
    private String language;
    private String localGitRepo;
    private String buildMessage;
    private String configInput;
    public static Model instance;
    private iMainController controller;

    /**
     * (Only for testing) Creates the Model
     * 
     */
    public Model()
    {
        
    }
    
    /**
     * Creates the Model
     * @param _controller interface of the MainController
     */
    public Model(iMainController _controller)
    {
        this.controller = _controller;
        instance = this;
    }

    /**
     * Sets the necessary input variables for a pipeline build
     * @param jenkinsURL jenkinsURL the URL of the Jenkins server
     * @param branchName branchName the specific branch of the remote repository to access
     */
    public void setBuildInput(String jenkinsURL, String branchName)
    {
       
        this.setJenkinsURL(jenkinsURL);
        this.setBranchName(branchName);

    }
        /**
     * Sets the necessary input variables for a pipeline config file
     * @param gitHubURL the URL of the specific remote repository
     * @param language the programming language that the remote repository application is written in
     * @param localGitRepo the path to the local git repository
     */
    public void setConfigInput(String gitHubURL, String language, String localGitRepo)
    {
      this.setGitHubURL(gitHubURL);      
      this.setLanguage(language);
      this.setLocalGitRepo(localGitRepo);
    }
     /**
     * Creates a String formatted to input to the Jenkins server for the build
     * 
     */
    public String makeBuildMessage()
    {
       return this.buildMessage = String.format("http://%s/jobs/jenkins_pipeline/%s/api/json?tree=results,timestamp,estimatedDuration", this.jenkinsURL, this.branchName);
    }
     /**
     * Creates a String formatted to set configuration file for the github repo
     * 
     *
    public String makeConfigInput()
    {
       this.configInput = String.format();
    }*/
    
    /**
     * Sets the Jenkins URL address to the correct instance field for later use
     * @param jenkinsURL the URL of the Jenkins server
     */
    public void setJenkinsURL(String jenkinsURL)
    {
        this.jenkinsURL = jenkinsURL;   
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
     * Sets the name to the correct instance field for later use with specifying which branch to access
     * @param branchName the specific branch of the remote repository to access
     */
    public void setBranchName(String branchName)
    {
        
        this.branchName = branchName;
        
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
     * Sets the path of the local git repository to the correct instance field for later use
     * @param localGitRepo the path to the local git repository
     */
    public void setLocalGitRepo(String localGitRepo)
    {
        
        this.localGitRepo = localGitRepo;
        
    }
    
    /**
     * Returns the Jenkins URL
     * @return jenkinsURL the URL of the Jenkins server
     */
    public String getJenkinsURL()
    {
        
        return this.jenkinsURL;
        
    }
    
    /**
     * Returns the GitHub URL
     * @return gitHubURL the URL of the specific remote repository
     */
    public String getGitHubURL()
    {
        
        return this.gitHubURL;
        
    }
    
    /**
     * Returns the branch name
     * @return branchName the specific branch of the remote repository to access
     */
    public String getBranchName()
    {
        
        return this.branchName;
        
    }
    
    /**
     * Returns the programming language of the remote repository application
     * @return language the programming language that the remote repository application is written in
     */
    public String getLanguage()
    {
        
        return this.language;
        
    }
    
    /**
     * Returns the path to the local git repository
     * @return localGitRepo the path to the local git repository
     */
    public String getLocalGitRepo()
    {
        
        return this.localGitRepo;
        
    }
    
}
