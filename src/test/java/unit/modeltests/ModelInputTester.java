/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.modeltests;

import com.supergalaxypenguin.vcdep.model.implementations.Model;
import com.supergalaxypenguin.vcdep.model.implementations.Pipeline;
import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
/********************
 * @author natha
 * @author agl11
 *******************/
public class ModelInputTester
{

    String testIP = "52.91.106.201";
//    /*************************
//    * Unit test
//    *************************/
//    @Test
//    public void TestModelIsAbleToSetBuildInputVariablesToInstanceVariables()
//    {
//        
//        // Arrange
//        Model model = Model.getInstance();
//        String jenkinsURL = "jenkinsURL";
//        String branchName = "branchName";
//        
//        // Act
//        model.setBuildInput(jenkinsURL, branchName);
//        
//        // Assert
//        assertEquals(jenkinsURL, model.getJenkinsURL());
//        assertEquals(branchName, model.getBranchName());
//        
//    }
//    /*************************
//    * Unit test
//    *************************/
//    @Test
//    public void TestModelIsAbleToSetConfigInputVariablesToInstanceVariables()
//    {
//       
//       //Arrange
//       Model model = Model.getInstance();
//       String gitHubURL = "gitHubURL";
//       String language = "language";
//       String localGitRepo = "localGitRepo";
//       String[] stages = new String[] {"nathan", "is", "a", "douche"};
//       
//       //Act
//       model.setConfigInput(gitHubURL, language, localGitRepo, stages);
//       
//       //Assert
//       assertEquals(gitHubURL, model.getGitHubURL());
//       assertEquals(language, model.getLanguage());
//       assertEquals(localGitRepo, model.getLocalGitRepo());
//    }
//    /*************************
//    * Unit test
//    *************************/
//    @Test
//    public void TestMakeBuildMessageReturnsCorrectString()
//    {
//       
//       //Arrange
//       Model model = Model.getInstance();
//       String buildMessage = "http://jenkinsURL/job/jenkins_pipeline/1/api/json?tree=result,timestamp,estimatedDuration";
//       String jenkinsURL = "jenkinsURL";
//       String branchName = "1";
//       model.setBuildInput(jenkinsURL, branchName);
//       
//      //Act
//       String returnedBuildMessage = model.makeBuildMessage();
//       
//       //Assert
//       assertEquals(buildMessage, returnedBuildMessage);
//    }
//    
//    
//    @Test
//    public void TestMakeConfigInputReturnsCorrectString()
//    {
//       
//       //Arrange
//       Model model = Model.getInstance();
//       String language = "php";
//       String localGitRepo = "./src/main/resources/";
//       model.setLanguage(language);
//       model.setLocalGitRepo(localGitRepo);
//        
//       String actual = "{\n" +
//"  \"language\": \"php\",\n" +
//"  \"stages\": [\n" +
//"    \"static\",\n" +
//"    \"unit\",\n" +
//"    \"integration\",\n" +
//"    \"staging\"\n" +
//"  ]\n" +
//"}";
//        
//        // Act
//        String result = model.makeConfigInput();
//        
//        // Assert
//        assertEquals(actual, result);
//    }
//    /*************************
//    * Unit test
//    *************************/
//    @Test
//    public void TestCorrectPipelineCreated()
//    {
//        
//        // Arrange
//        Model model = Model.getInstance();
//        String language = "php";
//        String localGitRepo = "./src/main/resources/";
//        model.setLanguage(language);
//        model.setLocalGitRepo(localGitRepo);
//        
//        ArrayList<String> stages = new ArrayList<String>();
//        stages.add("static");
//        stages.add("unit");
//        stages.add("integration");
//        stages.add("staging");
//        Pipeline pipeline = new Pipeline(language, stages);
//        
//        model.createJson(pipeline);
//        
//        File config = new File(localGitRepo);
//        
//        // Act
//        boolean result = config.exists();
//        
//        
//        // Assert
//        assertEquals(true, result);
//        
//    }
//    /*************************
//    * Unit test
//    *************************/
//    @Test
//    public void TestCreateJSONIsCorrect()
//    {
//    
//        // Arrange
//        Model model = Model.getInstance();
//        String language = "php";
//        String localGitRepo = "./src/main/resources/";
//        model.setLanguage(language);
//        model.setLocalGitRepo(localGitRepo);
//        
//        ArrayList<String> stages = new ArrayList<String>();
//        stages.add("static");
//        stages.add("unit");
//        stages.add("integration");
//        stages.add("staging");
//        Pipeline pipeline = new Pipeline(language, stages);
//        
//        String actual = "{\n" +
//        "  \"language\": \"php\",\n" +
//        "  \"stages\": [\n" +
//        "    \"static\",\n" +
//        "    \"unit\",\n" +
//        "    \"integration\",\n" +
//        "    \"staging\"\n" +
//        "  ]\n" +
//        "}";
//        
//        // Act
//        String result = model.createJson(pipeline);
//        
//        // Assert
//        assertEquals(actual, result);
//    
//    }
//    /*************************
//    * Integration test
//    * Requires Jenkins server up and running to pass
//    *************************/
//    @Test
//    public void TestModelSendsBuildMessageToJenkins()
//    {
//        
//        //Arrange
//       Model model = Model.getInstance();
//       String branchName = "1";
//       model.setBuildInput(testIP, branchName);
//       model.makeBuildMessage();
//       
//      //Act
//      boolean result = model.sendBuildMessage();
//      
//      // Assert
//      if(!model.parseResult(model.getJenkinsResponse()).equals("null"))
//      {
//         assertEquals(true, result);
//      }
//      else
//      {
//         assertEquals(false, result);
//      }      
//    }
//    /*************************
//    * Integration test
//    * Requires Jenkins server up and running to pass
//    *************************/
//    @Test
//    public void TestResponseFromJenkinsIsReceivedByModel()
//    {
//    
//        // Arrange
//       Model model = Model.getInstance();
//       String branchName = "1";
//       model.setBuildInput(testIP, branchName);
//       model.makeBuildMessage();
//       String expectedMessage = "{\"_class\":\"org.jenkinsci.plugins.workflow.job.WorkflowRun\",\"estimatedDuration\":1486,\"result\":\"FAILURE\",\"timestamp\":1508262809866}";
//        
//        // Act
//        model.sendBuildMessage();
//
//        // Assert
//        assertEquals(expectedMessage, model.getJenkinsResponse());
//    
//    }
//    
//    // Integration Test
//    /*************************
//    * Integration test
//    * Requires Jenkins server up and running to pass
//    *************************/
//    @Test
//    public void TestRequestAndReceiveLogFileFromJenkins()
//    {
//    
//        Model model = Model.getInstance();
//        String branchName = "1";
//        model.setBuildInput(testIP, branchName);
//        String logTest = "Started by user anonymous\n" +
//"Checking out git https://github.com/ncoop57/jenkins_files.git to read Jenkinsfile\n" +
//"Cloning the remote Git repository\n" +
//"Cloning repository https://github.com/ncoop57/jenkins_files.git\n" +
//" > git init /var/jenkins_home/jobs/jenkins_pipline/workspace@script # timeout=10\n" +
//"Fetching upstream changes from https://github.com/ncoop57/jenkins_files.git\n" +
//" > git --version # timeout=10\n" +
//" > git fetch --tags --progress https://github.com/ncoop57/jenkins_files.git +refs/heads/*:refs/remotes/origin/*\n" +
//" > git config remote.origin.url https://github.com/ncoop57/jenkins_files.git # timeout=10\n" +
//" > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10\n" +
//" > git config remote.origin.url https://github.com/ncoop57/jenkins_files.git # timeout=10\n" +
//"Fetching upstream changes from https://github.com/ncoop57/jenkins_files.git\n" +
//" > git fetch --tags --progress https://github.com/ncoop57/jenkins_files.git +refs/heads/*:refs/remotes/origin/*\n" +
//" > git rev-parse refs/remotes/origin/shared_library^{commit} # timeout=10\n" +
//" > git rev-parse refs/remotes/origin/origin/shared_library^{commit} # timeout=10\n" +
//"Checking out Revision 0c891c963d57760521843a581c5619f6ef5b52c1 (refs/remotes/origin/shared_library)\n" +
//" > git config core.sparsecheckout # timeout=10\n" +
//" > git checkout -f 0c891c963d57760521843a581c5619f6ef5b52c1\n" +
//"First time build. Skipping changelog.\n" +
//"ERROR: Could not find any definition of libraries [shared_libraries]\n" +
//"org.codehaus.groovy.control.MultipleCompilationErrorsException: startup failed:\n" +
//"WorkflowScript: Loading libraries failed\n" +
//"\n" +
//"1 error\n" +
//"\n" +
//"	at org.codehaus.groovy.control.ErrorCollector.failIfErrors(ErrorCollector.java:310)\n" +
//"	at org.codehaus.groovy.control.CompilationUnit.applyToPrimaryClassNodes(CompilationUnit.java:1085)\n" +
//"	at org.codehaus.groovy.control.CompilationUnit.doPhaseOperation(CompilationUnit.java:603)\n" +
//"	at org.codehaus.groovy.control.CompilationUnit.processPhaseOperations(CompilationUnit.java:581)\n" +
//"	at org.codehaus.groovy.control.CompilationUnit.compile(CompilationUnit.java:558)\n" +
//"	at groovy.lang.GroovyClassLoader.doParseClass(GroovyClassLoader.java:298)\n" +
//"	at groovy.lang.GroovyClassLoader.parseClass(GroovyClassLoader.java:268)\n" +
//"	at groovy.lang.GroovyShell.parseClass(GroovyShell.java:688)\n" +
//"	at groovy.lang.GroovyShell.parse(GroovyShell.java:700)\n" +
//"	at org.jenkinsci.plugins.workflow.cps.CpsGroovyShell.reparse(CpsGroovyShell.java:67)\n" +
//"	at org.jenkinsci.plugins.workflow.cps.CpsFlowExecution.parseScript(CpsFlowExecution.java:430)\n" +
//"	at org.jenkinsci.plugins.workflow.cps.CpsFlowExecution.start(CpsFlowExecution.java:393)\n" +
//"	at org.jenkinsci.plugins.workflow.job.WorkflowRun.run(WorkflowRun.java:238)\n" +
//"	at hudson.model.ResourceController.execute(ResourceController.java:97)\n" +
//"	at hudson.model.Executor.run(Executor.java:421)\n" +
//"Finished: FAILURE\n";
//        
//        
//        // Act
//        String result = model.requestLogFile();
//        
//         // Assert
//         assertEquals(logTest, result);
//                
//    }
//    /******************
//     * Unit test 
//     *****************/
//    @Test
//    public void TestModelThreadIsRunning()
//    {
//    
//        // Arrange
//        Model model = Model.getInstance();
//        String branchName = "branchName";
//        model.setBuildInput(testIP, branchName);
//        model.makeBuildMessage();
//        
//        // Act
//        System.out.println("About to run...");
//        model.start();
//        System.out.println("Running...");
//        boolean result = model.isAlive();
//        
//        //Clean up
//        model.interrupt();
//        //model.setIsDone(true);
//        
//        // Assert
//        assertEquals(true, result);
//         
//    }
    
    /*************************
     * Integration test
     * Requires Jenkins server up and running to pass
     *************************/
    @Test
    public void TestBuildExistsFailsWithBuildNameExistingInDatabase()
    {

        // Arrange
        Model model = Model.getInstance();
        String buildName = "Does Not Exist";
        model.setJenkinsURL(testIP);

        // Act
        int result = model.buildExists(buildName);

        // Assert
        assertEquals(-1, result);

    }

    /*************************
     * Integration test
     * Requires Jenkins server up and running to pass
     *************************/
    @Test
    public void TestBuildExistsPassesWithBuildNameExistingInDatabase()
    {

        // Arrange
        Model model = Model.getInstance();
        String buildName = "se2_devops_mw_1: feature";
        model.setJenkinsURL(testIP);

        // Act
        int result = model.buildExists(buildName);

        // Assert
        assertNotEquals(-1, result);

    }


}
