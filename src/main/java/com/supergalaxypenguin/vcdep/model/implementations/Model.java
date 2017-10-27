/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.model.implementations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

/********************
 * @author natha
 * @author agl11
 *******************/
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
    
    /*************************
     * Method implements run for thread
     */
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
    
    /*************************************
     * (Only for testing) Function creates the Model
     */
    private Model()
    {
        
    }
    
    /******************************
     * Function sets the Controller interface
     * @param _controller iMainController interface
     */
    public void setController(iMainController _controller){
        controller = _controller;
    }
    
    /********************************
     * Function creates the Model
     * @return the instance of Model
     */
    public static Model getInstance()
    {
        
        if (instance == null)
            instance = new Model();

        return instance;
        
    }
    /***********************************
     * Function to set the isDone variable
     * @param isDone boolean representing completion or not
     */
    public void setIsDone(boolean isDone)
    {
    
        this.isDone = isDone;
    
    }
    
    /**
     * Function sets the necessary input variables for a pipeline build
     * @param jenkinsURL String representing jenkinsURL the URL of the Jenkins server
     * @param branchName String representing branchName the specific branch of the remote repository to access
     */
    public void setBuildInput(String jenkinsURL, String branchName)
    {
       
        this.setJenkinsURL(jenkinsURL);
        this.setBranchName(branchName);

    }
     /************************************************************
     * Function sets the necessary input variables for a pipeline config file
     * @param gitHubURL String representing the URL of the specific remote repository
     * @param language String representing the programming language that the remote repository application is written in
     * @param localGitRepo String representing the path to the local git repository
     * @param stages String[] of stage names
     */
    public void setConfigInput(String gitHubURL, String language, String localGitRepo, String[] stages)
    {
      this.setGitHubURL(gitHubURL);      
      this.setLanguage(language);
      this.setLocalGitRepo(localGitRepo);
    }
     /****************************
     * Function creates a String formatted to input to the Jenkins server for the build
     * @return String buildMessage
     */
    public String makeBuildMessage()
    {
       return this.buildMessage = String.format("http://%s/job/jenkins_pipline/%s/api/json?tree=result,timestamp,estimatedDuration", this.jenkinsURL, this.branchName);
    }
     /*****************************
     * Function creates a String formatted to set configuration file for the github repo
     * @return String JSON message of pipeline configuration
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
    
    /******************************************
     * Function sets the Jenkins URL address to the correct instance field for later use
     * @param jenkinsURL String representing the URL of the Jenkins server
     */
    public void setJenkinsURL(String jenkinsURL)
    {
        this.jenkinsURL = jenkinsURL;   
    }
    
    /****************************************
     * Function sets the GitHub URL address to the correct instance field for later use
     * @param gitHubURL String representing the URL of the specific remote repository
     */
    public void setGitHubURL(String gitHubURL)
    {
        
        this.gitHubURL = gitHubURL;
        
    }
    
    /***************************************
     * Function sets the name to the correct instance field for later use with specifying which branch to access
     * @param branchName String representing the specific branch of the remote repository to access
     */
    public void setBranchName(String branchName)
    {
        
        this.branchName = branchName;
        
    }
    
    /************************************
     * Function sets the programming language to the correct instance field for determining the type of pipeline to create
     * @param language String representing the programming language that the remote repository application is written in
     */
    public void setLanguage(String language)
    {
        
        this.language = language;
        
    }
    
    /*******************************************
     * Function sets the path of the local git repository to the correct instance field for later use
     * @param localGitRepo String representing the path to the local git repository
     */
    public void setLocalGitRepo(String localGitRepo)
    {
        
        this.localGitRepo = localGitRepo;
        
    }
    
    /***************************
     * Function returns the Jenkins URL
     * @return String jenkinsURL (the URL of the Jenkins server)
     */
    public String getJenkinsURL()
    {
        
        return this.jenkinsURL;
        
    }
    
    /*************************
     * Function returns the GitHub URL
     * @return String gitHubURL (the URL of the specific remote repository)
     */
    public String getGitHubURL()
    {
        
        return this.gitHubURL;
        
    }
    
    /****************************
     * Function returns the branch name
     * @return String branchName (the specific branch of the remote repository to access)
     */
    public String getBranchName()
    {
        
        return this.branchName;
        
    }
    
    /*************************
     * Function returns the programming language of the remote repository application
     * @return String language (the programming language that the remote repository application is written in)
     */
    public String getLanguage()
    {
        
        return this.language;
        
    }
    
    /*****************************
     * Function to return the path to the local git repo
     * @return String localGitRepo (the path to the local git repository)
     */
    public String getLocalGitRepo()
    {
        
        return this.localGitRepo;
        
    }
    
    /*********************************************
     * function to get the Jenkins response
     * @return String (the response from the Jenkins server)
     */
    public String getJenkinsResponse()
    {
        
        return this.jenkinsResponse;
        
    }
 
    /****************************************
     * function to create a JSON message formate for communicating with Jenkins
     * @param pipeline (JSON Pipeline object)
     * @return String
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
    /******************************
     * function to create a connection and send the message to Jenkins
     * @return boolean
     */
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
                String result = parseResult(this.jenkinsResponse);
                System.out.println(this.parseResult(this.jenkinsResponse));
                System.out.println(res.toString());
                if(!result.equals("null"))
                {
                  return true;
                }
                else
                {
                  return false;  
                }
            }
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            return false;
            
        }
        
        return false;
    }
    /***************************
     * function to request the log file from Jenkins
     * @return String
     */
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
    /*****************
     * function to parse the result of the run for the model thread
     * @param build (String)
     * @return String result
     */
    public String parseResult(String build)
    {
       JsonElement jelement = new JsonParser().parse(build);
       JsonObject jobject = jelement.getAsJsonObject();
       String result = jobject.get("result").getAsString();
        
        return result;
    }
    
}
