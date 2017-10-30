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
import com.supergalaxypenguin.vcdep.MainApp;
import com.supergalaxypenguin.vcdep.controller.implementations.MainController;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import com.supergalaxypenguin.vcdep.model.implementations.Model;
import com.supergalaxypenguin.vcdep.view.implementations.PipelineSceneController;
import java.io.IOException;
import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

public class ControllerInputTester
{
    String jenkinsURL = "www.jenkinsurl.com";
    String gitHubURL = "www.githuburl.com";
    String branchName = "testBranch";
    String language = "testLanguage";
    String[] stages = new String[] {"stage1", "stage2"};
    String localGitRepo = "./localfile.txt";
    String status = "Status";
    
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
    public void TestControllerCanReceiveandPassStatusUpdatesFromModelToView()
    {
        //Arrange
        MainController controller = MainController.getInstance();
        
        //Act
        controller.updateStatusToView(status);
        
        //Assert
        assertEquals(status, controller.getStatusMessage());
    }
    
    @Test
    public void TestControllerCanSendInputToTheModel()
    {
        //Arrange
        MainController controller = MainController.getInstance();
        
        //Act
        controller.getModel().setConfigInput(gitHubURL, language, localGitRepo, stages);
        controller.getModel().setBuildInput(jenkinsURL, branchName);
        
        //Assert
        assertEquals(jenkinsURL, controller.getModel().getJenkinsURL());
        assertEquals(gitHubURL, controller.getModel().getGitHubURL());
        assertEquals(branchName, controller.getModel().getBranchName());
        assertEquals(language, controller.getModel().getLanguage());
        Assert.assertArrayEquals(stages, controller.getModel().getStages());
        assertEquals(localGitRepo, controller.getModel().getLocalGitRepo());
    }
}
