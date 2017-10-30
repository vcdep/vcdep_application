/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.model.implementations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.supergalaxypenguin.vcdep.controller.implementations.MainController;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * @author nathan
 */
public class Model extends Thread implements Runnable
{
    
    // All instance variables for defining a Jenkins Pipeline
    private String jenkinsURL;
    private String gitHubURL;
    private String branchName;
    private String language;
    private String localGitRepo;
    private String buildMessage;
    private String configInput;
    private String jenkinsResponse;
    public static Model instance;
    private static iMainController controller;
    private boolean isDone = false;
    private String[] stages;

    @Override
    public void run()
    {
        try
        {
            while(!isDone)
            {
                if (this.isInterrupted())
                {
                    throw new InterruptedException();
                }
                else
                {
                    boolean isDone = this.sendBuildMessage();
                }
            }
            
            String log = this.requestLogFile();
            controller.setLogFile(log);
            controller.updateStatusToView(log);
        }
        catch(InterruptedException e)
        {
            
        }
    }
    
    /**
     * (Only for testing) Creates the Model
     * 
     */
    private Model()
    {
        
    }
    
    /**
     * Sets the Controller interface
     * @param _controller iMainController interface
     */
    public void setController(iMainController _controller){
        controller = _controller;
    }
    
    /**
     * Creates the Model
     * @return returns instance of Model
     */
    public static Model getInstance()
    {
        
        if (instance == null)
            instance = new Model();

        return instance;
        
    }

    public void setIsDone(boolean isDone)
    {
    
        this.isDone = isDone;
    
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
    public void setConfigInput(String gitHubURL, String language, String localGitRepo, String[] stages)
    {
      this.setGitHubURL(gitHubURL);      
      this.setLanguage(language);
      this.setLocalGitRepo(localGitRepo);
      this.stages = stages;
    }
     /**
     * Creates a String formatted to input to the Jenkins server for the build
     * 
     */
    public String makeBuildMessage()
    {
       return this.buildMessage = String.format("http://%s/job/jenkins_pipline/%s/api/json?tree=result,timestamp,estimatedDuration", this.jenkinsURL, this.branchName);
    }
     /**
     * Creates a String formatted to set configuration file for the github repo
     * 
     */
    public String makeConfigInput()
    {
        ArrayList<String> stages = new ArrayList<String>();
        stages.add("static");
        stages.add("unit");
        stages.add("integration");
        stages.add("staging");
        Pipeline pipeline = new Pipeline(this.getLanguage(), stages);
        return this.createJson(pipeline);
//       this.configInput = String.format();
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
    
    /**
     * Returns the response from the Jenkins server
     * @return the response from the Jenkins server
     */
    public String getJenkinsResponse()
    {
        
        return this.jenkinsResponse;
        
    }
 
    /**
     * 
     * @param pipeline
     * @return 
     */
    public String createJson(Pipeline pipeline)
    {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.serializeNulls().create();

        try
        {

            PrintWriter writer = new PrintWriter(this.getLocalGitRepo() + "/config.json", "UTF-8");
            String json = gson.toJson(pipeline);
            writer.println(json);
            writer.close();
            
            return json;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return null;

    }
    
    public boolean sendBuildMessage()
    {
        
        try
        {
            URL url = new URL(this.buildMessage);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                
                StringBuffer res = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
                
                    res.append(inputLine);
                
                }
                
                in.close();
                this.jenkinsResponse = res.toString();
                System.out.println(this.parseResult(this.jenkinsResponse));
                System.out.println(res.toString());
                return true;

            }
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            return false;
            
        }
        
        return false;
        
    }
    
    public String requestLogFile()
    {
        
        try
        {
            
            String request = String.format("http://%s/job/jenkins_pipline/%s/consoleText", this.jenkinsURL, this.branchName);
            
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                
                StringBuffer res = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
                
                    res.append(inputLine + "\n");
                
                }
                
                in.close();
                this.jenkinsResponse = res.toString();
                MainController.getInstance().setLogFile(this.jenkinsResponse);
                System.out.println(this.jenkinsResponse);
                return res.toString();

            }
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            return null;
            
        }
        
        return null;
        
    }
    
    private String parseResult(String build)
    {
    
        JsonParser parser = new JsonParser();
        String json = parser.parse(build).toString();
        
        return json;
    
    }
    
    public String[] getStages()
    {
        return this.stages;
    }
    
}
