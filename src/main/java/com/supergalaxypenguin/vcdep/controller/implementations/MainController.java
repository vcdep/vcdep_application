/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.controller.implementations;

import com.sun.xml.internal.ws.util.StringUtils;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.model.implementations.Model;
import com.supergalaxypenguin.vcdep.view.implementations.ConfigurationViewController;
import com.supergalaxypenguin.vcdep.view.implementations.PipelineSceneController;
import java.io.File;
import java.io.IOException;
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
    private String logFile;
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
     * 
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
    public void setJavaFXStage(Stage _javaFXStage)
    {
        this.javaFXStage = _javaFXStage;
    }
    
    /**
     * displays the DirectoryChooser
     * @return Returns the absolute path of the directory chosen
     */
    public File displayDirectoryChooser()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(javaFXStage);     
    }
    
    /**
     * displays the ConfigurationScene
     * @throws java.io.IOException
     */
    public void displayConfigurationScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ConfigurationScene.fxml"));
        this.configurationViewController.setMainControllerInterface((iMainController) this);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        javaFXStage.setTitle("Configuration Viewer");
        javaFXStage.setScene(scene);
        javaFXStage.show();
    }
    
    /**
     * displays the PipelineScene
     * @throws IOException 
     */
    public void displayPipelineScene() throws IOException
    {
        this.pipelineSceneController = PipelineSceneController.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PipelineScene.fxml"));
        this.pipelineSceneController = PipelineSceneController.getInstance();
        this.pipelineSceneController.setMainControllerInterface((iMainController) this);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        javaFXStage.setTitle("Pipeline Viewer");
        javaFXStage.setScene(scene);
        javaFXStage.show();
    }
    
    /**
     * Returns the String associated with the next step in the process
     * @return Returns a String containing the information about the next step
     */
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
    public void setLogFile(String _logFile)
    {
        this.logFile = _logFile;
    }
    
    /**
     * Gets the last Log File received from the Jenkins Server
     * @return Returns a String containing the file.
     */
    public String getLogFile()
    {
        logFile = model.requestLogFile();
        return logFile;
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
    public void runPipeline(String gitHubURL, String language, String localGitRepo, String jenkinsURL, String branchName, String[] stages)
    {
        this.setGitHubURL(gitHubURL);
        this.setLanguage(language);
        this.setLocalRepo(localGitRepo);
        this.setJenkinsURL(jenkinsURL);
        this.setBranchName(branchName);
        this.setStages(stages);
        model.setConfigInput(gitHubURL, language, localGitRepo, stages);
        model.makeConfigInput();
        model.setBuildInput(jenkinsURL, branchName);
        model.makeBuildMessage();
        
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
        this.currentStage = 0;
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
    
    /**
     * Returns the Model object for communication testing
     * @return Model
     */
    public Model getModel()
    {
        return model;
    }
    
    /**
     * Returns the javaFXStage object for testing
     * @return Stage
     */
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
    public int search(String stageInfo)
    {
       String [] stageArray = this.logFile.split("\n");
       for(int i=0; i<stageArray.length; i++)
       {
          if(stageArray[i].equals(stageInfo))
          {
             return i;
          }
       }
       return -1;
    }
    public String getCheckoutStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (Checkout)");
       String [] checkoutArray = this.logFile.split("\n");
       while(!checkoutArray[++index].contains("[Pipeline] { ("))
       {
          output = output + checkoutArray[index] + "\n";
          if(index == checkoutArray.length-1)
          {
             break;
          }
       }
       return output;
    }
    public String getBuildStatus()              //only run if it is a Java project
    {
       String output = "";
       int index = search("[Pipeline] { (Build)");
       String [] checkoutArray = this.logFile.split("\n");
       while(!checkoutArray[++index].contains("[Pipeline] { ("))
       {
          output = output + checkoutArray[index] + "\n";
          if(index == checkoutArray.length-1)
          {
             break;
          }
       }
       return output;
    }
    public String getStaticAnalysisStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (Static Analysis)");
       String [] checkoutArray = this.logFile.split("\n");
       while(!checkoutArray[++index].contains("[Pipeline] { ("))
       {
          output = output + checkoutArray[index] + "\n";
          if(index == checkoutArray.length-1)
          {
             break;
          }
       }
       return output;
    }
    public String getUnitTestStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (Unit Testing)");
       String [] checkoutArray = this.logFile.split("\n");
       while(!checkoutArray[++index].contains("[Pipeline] { ("))
       {
          output = output + checkoutArray[index] + "\n";
          if(index == checkoutArray.length-1)
          {
             break;
          }
       }
       return output;
    }
    public String getIntegrationStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (Integration Testing)");
       String [] checkoutArray = this.logFile.split("\n");
       while(!checkoutArray[++index].contains("[Pipeline] { ("))
       {
          output = output + checkoutArray[index] + "\n";
          if(index == checkoutArray.length-1)
          {
             break;
          }
       }
       return output;
    }
    public String getDeploymentStatus()
    {
       String output = "";
       int index = search("[Pipeline] { (Staging)");
       String [] checkoutArray = this.logFile.split("\n");
       while(!checkoutArray[++index].contains("[Pipeline] { ("))
       {
          output = output + checkoutArray[index] + "\n";
          if(index == checkoutArray.length-1)
          {
             break;
          }
       }
       return output;
    }
    public String parseCheckout()
    {
       String output = "Welcome to the checkout stage.  Currently, the Jenkins pipeline is locating your Github repository "
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
          output = output + "Build failed.  Github repository not found. Aborting.\n";
       }
       else
       {
          output = output + "Build successful.  Continue to next stage.\n";
       }
       output =  output + "If you would like to see details, click the log file button below.\n";
       System.out.println(output);
       return (output);
    }
    /* deal with later
    public String parseBuild()               //java only
    {
       String output = "Welcome to the build stage.  Currently, the Jenkins pipeline is building your project and compiling "
               + "the required files.";
       //events
       if(this.getBuildStatus().contains(""))
       {
          output = output + "Build failed.  Unable to compile project. Aborting.\n";
       }
       else
       {
          output = output + "Build successful.  Continue to next stage.\n";
       }
       output =  output + "If you would like to see details, click the log file button below.\n";
       return (output);
    }*/
    public int findErrorCount()        //used to find staticAnalysis errors
    {
       int filesCount = this.getStaticAnalysisStatus().length() - this.getStaticAnalysisStatus().replace("FILE:", "").length();
       filesCount = filesCount / 5;
       int errorsCount = this.getStaticAnalysisStatus().length() - this.getStaticAnalysisStatus().replace("ERROR", "").length();
       errorsCount = errorsCount / 5;
       return(errorsCount - filesCount);
    }
    public String parseStaticAnalysis()
    {
      String output = "Welcome to the Static Analysis stage.  Currently, the Jenkins pipeline is comparing your code with coding "
              + "standards, looking for ways to improve your coding structure.\n";
       //events
       int numErrors = this.findErrorCount();
       if(numErrors > 0)
       {
          output = output + "Static Analysis found " + numErrors + " problems with your code.\n";
       }
       else
       {
          output = output + "Static Analysis found no issues with your code.  Continue to next stage.\n";
       }
       output =  output + "If you would like to see details, click the log file button below.\n";
       System.out.println(output);
       return (output);
    }
    public String parseUnitTests()
    {
      String output = "Welcome to unit testing.  Currently, the Jenkins pipeline is running your code’s unit tests to ensure "
              + "that your changes still pass their respective unit tests.\n";
       //events
       if(this.getUnitTestStatus().contains("FAILURES!"))
       {
          output = output + "Unit testing found problems with your code. Aborting.\n";
       }
       else
       {
          output = output + "All the unit tests passed.  Continue to next stage.\n";
       }
       output =  output + "If you would like to see details, click the log file button below.\n";
       System.out.println(output);
       return (output);
    }
    public String parseIntegration()
    {
      String output = "Welcome to integration.  Currently the Jenkins pipeline is integrating the modules within your project,"
              + " ensuring that the changes have not caused any integration issues.\n";
       //events
       if(this.getIntegrationStatus().contains("FAILURES!"))
       {
          output = output + "Integration testing found problems with your code. Aborting.\n";
       }
       else
       {
          output = output + "All the integration tests passed.  Continue to next stage.\n";
       }
       output =  output + "If you would like to see details, click the log file button below.\n";
       System.out.println(output);
       return (output);
    }
    public String parseDeployment()
    {
      String output = "Welcome to deployment.  Currently, the Jenkins pipeline is deploying your new solution to production "
              + "machines and checking that the new code is compatible with your production hardware and software.\n";
       //events
       output = output + "Your project passed the deployment stage.  Now your code is ready to be released.\n";
       output =  output + "If you would like to see details, click the log file button below.\n";
       System.out.println(output);
       return (output);
    }
}