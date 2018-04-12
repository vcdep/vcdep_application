/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.controller.implementations;

import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.domain.StageInfo;
import com.supergalaxypenguin.vcdep.domain.StageType;
import com.supergalaxypenguin.vcdep.model.implementations.Model;
import com.supergalaxypenguin.vcdep.view.implementations.ConfigurationViewController;
import com.supergalaxypenguin.vcdep.view.implementations.PipelineSceneController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * The Main Controller
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
    private PipelineSceneController pipelineSceneController;
    private final Model model;
    private Stage javaFXStage;
    private static MainController instance = null;
    private String logFile = "Waiting for Build to Finish";
    private String[] logLines;
    private int currentStage = 0;
    private String status = "Waiting for status update...";
    
    /**
     * creates the MainController, Model, and ConfigurationViewController
     */
    private MainController()
    {
        configurationViewController = new ConfigurationViewController();
        model = Model.getInstance();
        model.setController((iMainController) this);
    }
    
    /**
     * Checks for an instance, if one is not available, it creates one
     * @return instance of MainController
     */
    public static MainController getInstance()
    {
    
        if (instance == null)
        {
            instance = new MainController();
        }
        return instance;
        
    }
    
    /**
     * Sets the Java FX Stage
     * @param _javaFXStage the JavaFX stage for the application
     */
    @Override
    public void setJavaFXStage(Stage _javaFXStage)
    {
        this.javaFXStage = _javaFXStage;
    }
    
    /**
     * displays the DirectoryChooser
     * @return Returns the absolute path of the directory chosen
     */
    @Override
    public File displayDirectoryChooser()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(javaFXStage);     
    }
    
    /**
     * displays the ConfigurationScene
     * @throws java.io.IOException
     */
    @Override
    public void displayConfigurationScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ConfigurationScene.fxml"));
        this.configurationViewController.setMainControllerInterface((iMainController) this);
        Scene scene = new Scene(root);
        javaFXStage.setTitle("Configuration Viewer");
        javaFXStage.setScene(scene);
        javaFXStage.show();
    }
    
    /**
     * displays the WaitScene
     * @throws IOException 
     */
    @Override
    public void displayWaitScene() throws IOException
    {
        this.pipelineSceneController = PipelineSceneController.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WaitScene.fxml"));
        Scene scene = new Scene(root);
        javaFXStage.setTitle("Waiting");
        javaFXStage.setScene(scene);
        javaFXStage.show();
    }
    
    /**
     * displays the PipelineScene
     * @throws IOException 
     */
    @Override
    public void displayPipelineScene() throws IOException
    {
        this.pipelineSceneController = PipelineSceneController.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PipelineScene.fxml"));
        this.pipelineSceneController = PipelineSceneController.getInstance();
        this.pipelineSceneController.setMainControllerInterface((iMainController) this);
        this.pipelineSceneController.setLanguage(language);
        this.pipelineSceneController.setStageInfos(this.getStageInfos());
        Scene scene = new Scene(root);
        javaFXStage.setTitle("Pipeline Viewer");
        javaFXStage.setScene(scene);
        javaFXStage.show();
    }
    
    /**
     * Returns the String associated with the next step in the process
     * @return Returns a String containing the information about the next step
     */
    @Override
    public String stepForward()
    {
        if (currentStage >= stages.length - 1 || stages == null)
        {
            return null;
        }
        else
        {
            currentStage++;
            return stages[currentStage];
        }
    }
    
    /**
     * Returns the String associated with the last step in the process
     * @return Returns a String containing the information about the last step
     */
    @Override
    public String stepBackward()
    {
        if (currentStage <= 0 || stages == null)
        {
            return null;
        }
        else
        {
            currentStage--;
            return stages[currentStage];
        }
    }
    
    /**
     * Store the Log File from the Jenkins Server
     * @param _logFile stores the log file the was received from the Jenkins Server
     */
    @Override
    public void setLogFile(String _logFile)
    {
        this.logFile = _logFile;
    }
    
    /**
     * Store the Log File from the Jenkins Server
     * @param _logFile stores the log file the was received from the Jenkins Server
     */
    public void setLogLines(String[] _logLines)
    {
        this.logLines = _logLines;
    }
    
    /**
     * Gets the last Log File received from the Jenkins Server
     * @return Returns a String containing the file.
     */
    @Override
    public String getLogFile()
    {
        return this.logFile;
    }
    
    /**
     * Gets the last Log File received from the Jenkins Server
     * @return Returns a String containing the file.
     */
    public String[] getLogLines()
    {
        return this.logLines;
    }
    
    /**
     * Runs the initializes and runs the pipeline.
     * @param gitHubURL     what is the gitHubURL?
     * @param language      in what language is the project?
     * @param localGitRepo  where is the local git repo?
     * @param jenkinsURL    what is the ip/url address of the jenkins server?
     * @param branchName    on what branch are you working?
     * @param stages        in what order are the stages?
     */
    @Override
    public void runPipeline(String gitHubURL, String language, String localGitRepo, String jenkinsURL, String branchName, String[] stages)
    {
        
        this.setGitHubURL(gitHubURL);
        this.setLanguage(language);
        this.setLocalRepo(localGitRepo);
        this.setJenkinsURL(jenkinsURL);
        this.setBranchName(branchName);
        this.setStages(stages);
        model.setConfigInput(gitHubURL, language, localGitRepo, stages);
        model.setBuildName(gitHubURL + ": " + branchName);
        model.makeConfigInput();
        model.setBuildInput(jenkinsURL, branchName);
        model.makeBuildMessage();
        
        model.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent> () {
            @Override
            public void handle(WorkerStateEvent event) {
                String result = model.getValue();
             
                try {
                    if (result != null) {
                        MainController.getInstance().setLogFile(result);
                        MainController.getInstance().setLogLines(result.split("\n"));
                        MainController.getInstance().displayPipelineScene();
                    } else {
                        MainController.getInstance().displayErrorScene();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Thread backgroundModel = new Thread(model);
        backgroundModel.setDaemon(true);
        backgroundModel.start();

    }
    
    /**
     * Returns the Jenkins URL
     * @return jenkinsURL the URL of the Jenkins server
     */
    @Override
    public String getJenkinsURL() 
    {
        return jenkinsURL;
    }

    /**
     * Sets the Jenkins URL address to the correct instance field for later use
     * @param jenkinsURL the URL of the Jenkins server
     */
    @Override
    public void setJenkinsURL(String jenkinsURL) 
    {
        this.jenkinsURL = jenkinsURL;
    }

    /**
     * Returns the GitHub URL
     * @return gitHubURL the URL of the specific remote repository
     */
    @Override
    public String getGitHubURL() 
    {
        return gitHubURL;
    }

    /**
     * Sets the GitHub URL address to the correct instance field for later use
     * @param gitHubURL the URL of the specific remote repository
     */
    @Override
    public void setGitHubURL(String gitHubURL) 
    {
        this.gitHubURL = gitHubURL;
    }

    /**
     * Returns the branch name
     * @return branchName the specific branch of the remote repository to access
     */
    @Override
    public String getBranchName() 
    {
        return branchName;
    }

    /**
     * Sets the name to the correct instance field for later use with specifying which branch to access
     * @param branch the specific branch of the remote repository to access
     */
    @Override
    public void setBranchName(String branch) 
    {
        this.branchName = branch;
    }

    /**
     * Returns the programming language of the remote repository application
     * @return language the programming language that the remote repository application is written in
     */
    @Override
    public String getLanguage() 
    {
        return language;
    }

    /**
     * Sets the programming language to the correct instance field for determining the type of pipeline to create
     * @param language the programming language that the remote repository application is written in
     */
    @Override
    public void setLanguage(String language) 
    {
        this.language = language;
    }

    /**
     * Returns the order of the pipeline stages in an array of Strings
     * @return stages an array containing the order of the stages to be run in the pipeline
     */
    @Override
    public String[] getStages() 
    {
        return stages;
    }

    /**
     * Sets the order of stages for the pipeline to run
     * @param stages an array of Strings that holds the order of the stages to run
     */
    @Override
    public void setStages(String[] stages) 
    {
        this.stages = stages;
        this.currentStage = 0;
    }

    /**
     * Returns the path to the local git repository
     * @return localGitRepo the path to the local git repository
     */
    @Override
    public String getLocalRepo() 
    {
        return localGitRepo;
    }

    /**
     * Sets the path of the local git repository to the correct instance field for later use
     * @param localGitRepo the path to the local git repository
     */
    @Override
    public void setLocalRepo(String localGitRepo) 
    {
        this.localGitRepo = localGitRepo;
    }
    
    /**
     * Returns the Model object for communication testing
     * @return Model
     */
    @Override
    public Model getModel()
    {
        return model;
    }
    
    /**
     * Returns the javaFXStage object for testing
     * @return Stage
     */
    @Override
    public Stage getJavaFXStage()
    {
        return javaFXStage;
    }
    
    /**
     * Returns the PipelineSceneController Object
     * @return PipelineSceneController
     */
    public PipelineSceneController getPipelineSceneController()
    {
        return this.pipelineSceneController;
    }
    
    /**
     * Sets the PipelineScene
     * @param scene 
     */
    public void setPipelineSceneController(PipelineSceneController scene)
    {
        this.pipelineSceneController = scene;
    }
    
    /**
     * Updates the status to the view
     * @param status A String that is a message to the user
     */
    @Override
    public void updateStatusToView(String status)
    {
        this.status = status;
        try{
            this.pipelineSceneController.updateScrollPane(status);
        }
        catch(Exception e)
        {
            
        }
    }
    
    /**
     * Returns the current status message
     * @return 
     */
    public String getStatusMessage()
    {
        return status;
    }
    
    /**
     * Helper function to search through log files and split the string
     * @param stageInfo
     * @return integer representing the index
     */
    public int search(String stageInfo)
    {
       for(int i=0; i<this.logLines.length; i++)
       {
          if(this.logLines[i].equals(stageInfo))
          {
             return i;
          }
       }
       return -1;
    }
    /**
     * Function to get the checkout status from the log file
     * @return String representing the checkout status
     */
    public String getCheckoutStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (Checkout)");
       while(!this.logLines[++index].contains("[Pipeline] { ("))
       {
          output = output + this.logLines[index] + "\n";
          if(index == this.logLines.length-1)
          {
             break;
          }
       }
       
       System.out.println("Checkout stage: " + output);
       return output;
    }
    /**
     * Function to get the build status from the log file
     * Function only runs on Java projects
     * @return String representing the build status
     */
    public String getBuildStatus()              
    {
       String output = "";
       int index = search("[Pipeline] { (Build)");
       String [] checkoutArray = this.logFile.split("\n");
       while(!this.logLines[++index].contains("BUILD"))
       {
          continue; //LOL
       }
       String buildArray;
       index = index -1;
       while(!this.logLines[++index].contains("[Pipeline] { ("))
       {
          output = output + checkoutArray[index] + "\n";
          if(index == checkoutArray.length-1)
          {
             break;
          }
       }
       return output;
    }
    /**
     * Function to get the status of static analysis from the log file
     * @return String representing the status of the SA stage
     */
    public String getStaticAnalysisStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (static)");
       while(!this.logLines[++index].contains("[Pipeline] { ("))
       {
          output = output + this.logLines[index] + "\n";
          if(index == this.logLines.length-1)
          {
             break;
          }
       }
       return output;
    }

    /**
     * Function to get the status of unit testing from the log file
     * @return String representing the unit testing status
     */
    public String getUnitTestStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (unit)");
       while(!this.logLines[++index].contains("[Pipeline] { ("))
       {
          output = output + this.logLines[index] + "\n";
          if(index == this.logLines.length-1)
          {
             break;
          }
       }
       return output;
    }
    /**
     * Function to get the integration status from the log file
     * @return String representing the integration stage 
     */
    public String getIntegrationStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (integration)");
       while(!this.logLines[++index].contains("[Pipeline] { ("))
       {
          output = output + this.logLines[index] + "\n";
          if(index == this.logLines.length-1)
          {
             break;
          }
       }
       return output;
    }
    
    /**
     * Function to get the deployment status from the log file
     * @return String representing the deployment status
     */
    public String getDeploymentStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (staging)");
       while(!this.logLines[++index].contains("[Pipeline] { ("))
       {
          output = output + this.logLines[index] + "\n";
          if(index == this.logLines.length-1)
          {
             break;
          }
       }
       return output;
    }
    
    /**
     * Function to parse the Checkout chunk from the log file
     * @param stageInfo identifies the stage
     * @return the updated stageInfo
     */
    public StageInfo parseCheckout(StageInfo stageInfo)
    {
       String output = "Welcome to the checkout stage.\n\n\tCurrently, the Jenkins pipeline is locating your Github repository "
               + "located at " + this.getGitHubURL() + " and grabbing the branch labelled " + this.getBranchName() + " and "
               + "configuring the pipeline to analyze your project.  " + "Jenkins is initializing using the " + this.getLanguage() 
               + " language, with the following stages enabled:\n";
       for(int i=0; i<this.stages.length; i++)
       {
          output = output + this.stages[i] + "\n";
       }
       output = output + "Additionally, Jenkins is creating a configuration file and saving it to your local git repository "
               + "located at " + this.getLocalRepo() + "\n";
       //events
       if(this.getCheckoutStatus().contains("ERROR:"))
       {
           stageInfo.setPassed(false);
           output = output + "\nBuild failed.  Github repository not found. Aborting.\n";
       }
       else if (this.getCheckoutStatus().contains("Skipping due to failure"))
       {
           stageInfo.setPassed(true);
           output = output + "\nCheckout not ran due to previously failed stage.";
           
       }
       else
       {
          stageInfo.setPassed(true);
          output = output + "\nBuild successful.  Continue to next stage.\n";
       }
       output =  output + "\nIf you would like to see details, click the log file button below.\n";
       stageInfo.setScript(output);
       
       return stageInfo;

    }
    /**
     * Function to parse the build stage from the log file
     * @param stageInfo
     * @return the updated stageInfo
     */
    public StageInfo parseBuild(StageInfo stageInfo)           
    { 
      String output = "Welcome to the build stage.\n\n\tCurrently, the Jenkins pipeline is building your project and compiling "
                 + "the required files.  ";
      //events
      if(this.getBuildStatus().contains("FAILURE"))
      {
          stageInfo.setPassed(false);
         output = output + "\nBuild failed.  Unable to compile project. Aborting.\n";
      }
      else if (this.getBuildStatus().contains("Skipping due to failure"))
       {
           stageInfo.setPassed(true);
           output = output + "\nBuild not ran due to previously failed stage.";
           
       }
      else
      {
            stageInfo.setPassed(true);
         output = output + "\nBuild successful.  Continue to next stage.\n";
      }
      output =  output + "\nIf you would like to see details, click the log file button below.\n";

      stageInfo.setScript(output);
      return stageInfo;
    }
    /**
     * Function to find the errors in the static analysis stage
     * @return integer representing the number of errors
     */
    public int findErrorCount()        //used to find staticAnalysis errors
    {
       int errorsCount=0;
       if(this.getLanguage().equals("php"))
       {
         int filesCount = this.getStaticAnalysisStatus().length() - this.getStaticAnalysisStatus().replace("FILE:", "").length();
         filesCount = filesCount / 5;
         errorsCount = this.getStaticAnalysisStatus().length() - this.getStaticAnalysisStatus().replace("ERROR", "").length();
         errorsCount = errorsCount / 5;
         return(errorsCount - filesCount);
       }
       else
       {
          String [] staticArray = this.getStaticAnalysisStatus().split("\n");
          int i;
          for(i=0; i<staticArray.length; i++)
          {
             if(staticArray[i].contains("/cdep/src/"))
             {
                errorsCount++;
             }
          }
          return errorsCount;
       }
    }
    /**
     * Function to parse the static analysis chunk from the log file
     * @param stageInfo
     * @return the updated stageInfo
     */
    public StageInfo parseStaticAnalysis(StageInfo stageInfo)
    {
      String output = "Welcome to the Static Analysis stage.\n\n\tCurrently, the Jenkins pipeline is comparing your code with coding "
              + "standards, looking for ways to improve your coding structure.\n This pipelline uses PHP Code_Sniffer for performing"
              + " Static Analysis. It is currently using the default coding standard, which using the PEAR coding standard for PHP, to compare against your code.";
       //events
       int numErrors = this.findErrorCount();
       if(numErrors > 0)
       {
          output = output + "\nStatic Analysis found " + numErrors + " problems with your code.\n";
       }
       else if (this.getStaticAnalysisStatus().contains("Skipping due to failure"))
       {
           stageInfo.setPassed(true);
           output = output + "\nStatic Analysis not ran due to previously failed stage.";
           
       }
       else
       {
          output = output + "\nStatic Analysis found no issues with your code.  Continue to next stage.\n";
       }
       output =  output + "\nIf you would like to see details, click the log file button below.\n";
       
       stageInfo.setPassed(true);
       stageInfo.setScript(output);
       return stageInfo;
    }
    /**
     * Function to parse the unit tests chunk from the log file
     * @param stageInfo
     * @return the updated stageInfo
     */
    public StageInfo parseUnitTests(StageInfo stageInfo)
    {
      String output = "Welcome to unit testing.\n\n\tCurrently, the Jenkins pipeline is running your code’s unit tests to ensure "
              + "that your changes still pass their respective unit tests.\nThe unit tests are written using PHPUnit framework.";
       //events
       if(this.getUnitTestStatus().contains("FAILURES!"))
       {
           stageInfo.setPassed(false);
          output = output + "\nUnit testing found problems with your code. Aborting.\n";
       }
       else if (this.getUnitTestStatus().contains("Skipping due to failure"))
       {
           stageInfo.setPassed(true);
           output = output + "\nUnit testing not ran due to previously failed stage.";
           
       }
       else
       {
           stageInfo.setPassed(true);
          output = output + "\nAll the unit tests passed.  Continue to next stage.\n";
       }
       output =  output + "\nIf you would like to see details, click the log file button below.\n";
       stageInfo.setScript(output);
       
       return stageInfo;
    }
    /**
     * Function to parse the integration chunk from the log file
     * @param stageInfo
     * @return the updated stageInfo
     */
    public StageInfo parseIntegration(StageInfo stageInfo)
    {
      String output = "Welcome to integration.\n\n\tCurrently the Jenkins pipeline is integrating the modules within your project,"
              + " ensuring that the changes have not caused any integration issues.\nThe integration tests are written using PHPUnit framework.";
       //events
       if(this.getIntegrationStatus().contains("FAILURES!"))
       {
           stageInfo.setPassed(false);
          output = output + "\nIntegration testing found problems with your code. Aborting.\n";
       }
       else if (this.getIntegrationStatus().contains("Skipping due to failure"))
       {
           stageInfo.setPassed(true);
           output = output + "\nIntegration testing not ran due to previously failed stage.";
           
       }
       else
       {
          stageInfo.setPassed(true);
          output = output + "\nAll the integration tests passed.  Continue to next stage.\n";
       }
       output =  output + "\nIf you would like to see details, click the log file button below.\n";
       stageInfo.setScript(output);
       return stageInfo;
    }
    /**
     * Function to parse the deployment chunk from the log file
     * @param stageInfo
     * @return the updated stageInfo
     */
    public StageInfo parseDeployment(StageInfo stageInfo)
    {
      String output = "Welcome to deployment.\n\n\tCurrently, the Jenkins pipeline is deploying your new solution to production "
              + "machines and checking that the new code is compatible with your production hardware and software.\n"
              + "Your website is being hosted using apache which is a HTTP server.";
       //events
       if (this.getDeploymentStatus().contains("Skipping due to failure"))
       {
           
           output = output + "\nDeployment stage not ran due to previously failed stage.";
           
       }
       else
       {
            output = output + "\nYour project passed the deployment stage.  Now your code is ready to be released.\n";
       }
       output =  output + "\nIf you would like to see details, click the log file button below.\n";
       
       stageInfo.setPassed(true);
       return stageInfo;
    }
    /**
     * Function to get all the stageInfo objects
     * @return ArrayList of stageInfo objects
     */
    public ArrayList<StageInfo> getStageInfos()
    {
        
        ArrayList<StageInfo> stageInfos = new ArrayList<>();
        
        for (String stage : this.stages)
        {
            
            StageInfo stageInfo = new StageInfo();
            
            if (stage.equalsIgnoreCase("Checkout"))
            {
                
                stageInfo.setLogChunk(this.getCheckoutStatus());
                stageInfo = this.parseCheckout(stageInfo);
                stageInfo.setType(StageType.CHECKOUT);
                
            }
            else if (stage.equalsIgnoreCase("Static"))
            {
                
                stageInfo.setLogChunk(this.getStaticAnalysisStatus());
                stageInfo = this.parseStaticAnalysis(stageInfo);
                stageInfo.setType(StageType.STATIC);
            }
            else if (stage.equalsIgnoreCase("Unit"))
            {
                
                stageInfo.setLogChunk(this.getUnitTestStatus());
                stageInfo = this.parseUnitTests(stageInfo);
                stageInfo.setType(StageType.UNIT);
            }
            else if (stage.equalsIgnoreCase("Integration"))
            {
                
                stageInfo.setLogChunk(this.getIntegrationStatus());
                stageInfo = this.parseIntegration(stageInfo);
                stageInfo.setType(StageType.INTEGRATION);
            }
            else if (stage.equalsIgnoreCase("Deploy"))
            {
                
                stageInfo.setLogChunk(this.getDeploymentStatus());
                stageInfo = this.parseDeployment(stageInfo);
                stageInfo.setType(StageType.DEPLOY);
            }
            else if (stage.equalsIgnoreCase("Build"))
            {
                
                stageInfo.setLogChunk(this.getBuildStatus());
                stageInfo = this.parseBuild(stageInfo);
                stageInfo.setType(StageType.BUILD);
            }
            
            stageInfos.add(stageInfo);
            if (!stageInfo.isPassed())
            {
                break;
            }
        }
        return stageInfos;
    }

    private void displayErrorScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ErrorScene.fxml"));
        Scene scene = new Scene(root);
        javaFXStage.setTitle("Error");
        javaFXStage.setScene(scene);
        javaFXStage.show();
    }
    
}