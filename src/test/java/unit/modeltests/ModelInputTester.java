/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.modeltests;

import com.supergalaxypenguin.vcdep.model.implementations.Model;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 *
 * @author natha
 */
public class ModelInputTester
{

    @Test
    public void TestModelIsAbleToSetBuildInputVariablesToInstanceVariables()
    {
        
        // Arrange
        Model model = new Model();
        String jenkinsURL = "jenkinsURL";
        String branchName = "branchName";
        
        // Act
        model.setBuildInput(jenkinsURL, branchName);
        
        // Assert
        assertEquals(jenkinsURL, model.getJenkinsURL());
        assertEquals(branchName, model.getBranchName());
        
    }
    @Test
    public void TestModelIsAbleToSetConfigInputVariablesToInstanceVariables()
    {
       
       //Arrange
       Model model = new Model();
       String gitHubURL = "gitHubURL";
       String language = "language";
       String localGitRepo = "localGitRepo";
       
       //Act
       model.setConfigInput(gitHubURL, language, localGitRepo);
       
       //Assert
       assertEquals(gitHubURL, model.getGitHubURL());
       assertEquals(language, model.getLanguage());
       assertEquals(localGitRepo, model.getLocalGitRepo());
    }
    @Test
    public void TestMakeBuildMessageReturnsCorrectString()
    {
       
       //Arrange
       Model model = new Model();
       String buildMessage = "http://jenkinsURL/jobs/jenkins_pipeline/branchName/api/json?tree=results,timestamp,estimatedDuration";
       String jenkinsURL = "jenkinsURL";
       String branchName = "branchName";
       model.setBuildInput(jenkinsURL, branchName);
       
      //Act
       String returnedBuildMessage = model.makeBuildMessage();
       
       //Assert
       assertEquals(buildMessage, returnedBuildMessage);
    }
    /*
    @Test
    public void TestMakeConfigInputReturnsCorrectString()
    {
       
       //Arrange
       Model model = new Model();
       String configInput = "";
       String gitHubURL = "gitHubURL";
       String language = "language";
       String localGitRepo = "localGitRepo";
       model.setConfigInput(gitHubURL, language, localGitRepo);
       //Act
       String returnedConfigInput = model.makeConfigInput(gitHubURL, language, localGitRepo)
       //Assert
       assertEquals(configInput, returnedConfigInput);
    }*/
    
}
