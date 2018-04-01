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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.concurrent.Task;


/********************
 * @author natha
 * @author agl11
 *******************/
public class Model extends Task<String>
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
    private String buildName;
    private String logFile;
    private int buildNumber = -1;

    /**
     *
     */
    public static Model instance;
    private static iMainController controller;
    private boolean isDone = false;
    private String[] stages;
    
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
      this.stages = stages;
    }
     /****************************
     * Function creates a String formatted to input to the Jenkins server for the build
     * @return String buildMessage
     */
    public String makeBuildMessage()
    {
       return this.buildMessage = String.format("http://%s/job/jenkins_pipeline/%s/api/json?tree=result,timestamp,estimatedDuration", this.jenkinsURL, this.branchName);
    }
     /*****************************
     * Function creates a String formatted to set configuration file for the github repo
     * @return String JSON message of pipeline configuration
     */
    public String makeConfigInput()
    {
        Pipeline pipeline = new Pipeline(this.getLanguage(), Arrays.asList(this.getStages()));
        return this.createJson(pipeline);
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
    /*******************************************
     * Function to set the buildName of the unique build
     * @param inputBuildName String representing unique identifier for the build
     */
    public void setBuildName(String inputBuildName)
    {
       
       this.buildName = inputBuildName;
       
    }
    /**************************
     * Function sets the build number from Jenkins
     * @param inputBuildNumber
     */
    public void setBuildNumber(int inputBuildNumber)
    {
       
       this.buildNumber = inputBuildNumber;
       
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
    /********************************************
     * function to get the buildName
     * @return String (the unique buildName based on the build number from jenkins)
     */
    public String getBuildName()
    {
       
       return this.buildName;
       
    }
    /****************************************
     * Function to get the buildNumber
     * @return int the unique buildNumber
     */
    public int getBuildNumber()
    {
       
       return this.buildNumber;
       
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
    /*****************
     * Function to parse the result of the run for the model thread
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
    /******************
     * Function to get the stages set on the object
     * @return String[] 
     */
    public String[] getStages()
    {
        return this.stages;
    }
    
    /*****************
     * Function to request the logFile from a build
     * @param buildName String representing the build from branchName input by user
     * @return String this.logFile
     */
   public String requestLogFile(String buildName) //FIXME: add unit test
   {
      String buildURL = String.format("http://%s/vcdep/get_build", this.jenkinsURL);
      try
      {
         System.out.println("URL: " + buildURL);
         HttpURLConnection conn = (HttpURLConnection)(new URL(buildURL)).openConnection();
         conn.setConnectTimeout(500000);
         conn.setDoOutput(true);
         conn.setDoInput(true);
         conn.setRequestProperty("Content-Type", "application/json");
         conn.setRequestProperty("Accept", "application/json");
         conn.setRequestMethod("POST");
         JsonObject json = new JsonObject();
         json.addProperty("buildName", buildName);
         OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
         writer.write(json.toString());
         writer.flush();
         int code = conn.getResponseCode();
         if (code == HttpURLConnection.HTTP_OK)
         {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;       
            StringBuffer res = new StringBuffer();

            while ((inputLine = in.readLine()) != null) 
            {
              res.append(inputLine);
            }
            in.close();
            
            
            System.out.println("Response: " + res.toString());
            JsonParser parser = new JsonParser();
            JsonObject response = (JsonObject) parser.parse(res.toString());
            String log = response.get("logFile").toString();
            log = log.replaceAll("newline", "\n");
            this.logFile = log;
            
         }
         else
         {
             
             System.out.println("Error occurred contacting server: " + code);
             
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

      return this.logFile;
      
    }
    @Override
    protected String call()
    {
        try
        {
            if (this.isCancelled())
            {
               throw new InterruptedException();
            }
            else
            {
               String log = this.requestLogFile(buildName);
               System.out.println("Log file: " + this.logFile);
            }
            
            System.out.println("Got the log file");
            
        }
        catch(InterruptedException e)
        {
            System.out.println("Something went wrong...");
            e.printStackTrace();
        }
        
        return this.logFile;
    }
    
}
