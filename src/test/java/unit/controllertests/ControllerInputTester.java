/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.controllertests;

/**
 *
 * @author Howtoon
 */
import com.supergalaxypenguin.vcdep.controller.implementations.MainController;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.model.implementations.Model;
import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

public class ControllerInputTester
{

    @Test
    public void TestControllerIsSingleton()
    {
        //Arrange
        MainController controller1 = MainController.getInstance();
        
        //Act
        MainController controller2 = MainController.getInstance();
        
        //Assert
        assertEquals(controller1, controller2);
    }
    
    @Test
    public void TestControllerStoresConfigurationVariables()
    {
        //Arrange
        iMainController controller = (iMainController)MainController.getInstance();
        String jenkinsURL = "www.jenkinsurl.com";
        String gitHubURL = "www.githuburl.com";
        String branchName = "testBranch";
        String language = "testLanguage";
        String[] stages = new String[] {"stage1", "stage2"};
        String localGitRepo = "./localfile.txt";
        
        //Act
        controller.setJenkinsURL(jenkinsURL);
        controller.setGitHubURL(gitHubURL);
        controller.setBranchName(branchName);
        controller.setLanguage(language);
        controller.setStages(stages);
        controller.setLocalRepo(localGitRepo);
        
        //Assert
        assertEquals(jenkinsURL, controller.getJenkinsURL());
        assertEquals(gitHubURL, controller.getGitHubURL());
        assertEquals(branchName, controller.getBranchName());
        assertEquals(language, controller.getLanguage());
        Assert.assertArrayEquals(stages, controller.getStages());
        assertEquals(localGitRepo, controller.getLocalRepo());
    }
    
    @Test
    public void TestControllerCanReceiveLogFileFromModel()
    {
        Model model = Model.getInstance();
        iMainController controller = (iMainController)MainController.getInstance();
        String testIP = "34.207.251.223";
        String branchName = "1";
        model.setBuildInput(testIP, branchName);
        String logTest = "Started by user anonymous\n" +
        "Checking out git https://github.com/ncoop57/jenkins_files.git to read Jenkinsfile\n" +
        "Cloning the remote Git repository\n" +
        "Cloning repository https://github.com/ncoop57/jenkins_files.git\n" +
        " > git init /var/jenkins_home/jobs/jenkins_pipline/workspace@script # timeout=10\n" +
        "Fetching upstream changes from https://github.com/ncoop57/jenkins_files.git\n" +
        " > git --version # timeout=10\n" +
        " > git fetch --tags --progress https://github.com/ncoop57/jenkins_files.git +refs/heads/*:refs/remotes/origin/*\n" +
        " > git config remote.origin.url https://github.com/ncoop57/jenkins_files.git # timeout=10\n" +
        " > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10\n" +
        " > git config remote.origin.url https://github.com/ncoop57/jenkins_files.git # timeout=10\n" +
        "Fetching upstream changes from https://github.com/ncoop57/jenkins_files.git\n" +
        " > git fetch --tags --progress https://github.com/ncoop57/jenkins_files.git +refs/heads/*:refs/remotes/origin/*\n" +
        " > git rev-parse refs/remotes/origin/shared_library^{commit} # timeout=10\n" +
        " > git rev-parse refs/remotes/origin/origin/shared_library^{commit} # timeout=10\n" +
        "Checking out Revision 0c891c963d57760521843a581c5619f6ef5b52c1 (refs/remotes/origin/shared_library)\n" +
        " > git config core.sparsecheckout # timeout=10\n" +
        " > git checkout -f 0c891c963d57760521843a581c5619f6ef5b52c1\n" +
        "First time build. Skipping changelog.\n" +
        "ERROR: Could not find any definition of libraries [shared_libraries]\n" +
        "org.codehaus.groovy.control.MultipleCompilationErrorsException: startup failed:\n" +
        "WorkflowScript: Loading libraries failed\n" +
        "\n" +
        "1 error\n" +
        "\n" +
        "	at org.codehaus.groovy.control.ErrorCollector.failIfErrors(ErrorCollector.java:310)\n" +
        "	at org.codehaus.groovy.control.CompilationUnit.applyToPrimaryClassNodes(CompilationUnit.java:1085)\n" +
        "	at org.codehaus.groovy.control.CompilationUnit.doPhaseOperation(CompilationUnit.java:603)\n" +
        "	at org.codehaus.groovy.control.CompilationUnit.processPhaseOperations(CompilationUnit.java:581)\n" +
        "	at org.codehaus.groovy.control.CompilationUnit.compile(CompilationUnit.java:558)\n" +
        "	at groovy.lang.GroovyClassLoader.doParseClass(GroovyClassLoader.java:298)\n" +
        "	at groovy.lang.GroovyClassLoader.parseClass(GroovyClassLoader.java:268)\n" +
        "	at groovy.lang.GroovyShell.parseClass(GroovyShell.java:688)\n" +
        "	at groovy.lang.GroovyShell.parse(GroovyShell.java:700)\n" +
        "	at org.jenkinsci.plugins.workflow.cps.CpsGroovyShell.reparse(CpsGroovyShell.java:67)\n" +
        "	at org.jenkinsci.plugins.workflow.cps.CpsFlowExecution.parseScript(CpsFlowExecution.java:430)\n" +
        "	at org.jenkinsci.plugins.workflow.cps.CpsFlowExecution.start(CpsFlowExecution.java:393)\n" +
        "	at org.jenkinsci.plugins.workflow.job.WorkflowRun.run(WorkflowRun.java:238)\n" +
        "	at hudson.model.ResourceController.execute(ResourceController.java:97)\n" +
        "	at hudson.model.Executor.run(Executor.java:421)\n" +
        "Finished: FAILURE\n";
        
        
        // Act
        controller.getLogFile();
        
         // Assert
         //assertEquals(logTest, result);
    }
}
