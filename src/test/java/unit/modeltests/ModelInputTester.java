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
       String buildMessage = "http://jenkinsURL/job/jenkins_pipline/1/api/json?tree=results,timestamp,estimatedDuration";
       String jenkinsURL = "jenkinsURL";
       String branchName = "1";
       model.setBuildInput(jenkinsURL, branchName);
       
      //Act
       String returnedBuildMessage = model.makeBuildMessage();
       
       //Assert
       assertEquals(buildMessage, returnedBuildMessage);
    }
    
    
    @Test
    public void TestMakeConfigInputReturnsCorrectString()
    {
       
       //Arrange
       Model model = new Model();
       String language = "php";
       String localGitRepo = "./src/main/resources/";
       model.setLanguage(language);
       model.setLocalGitRepo(localGitRepo);
        
       String actual = "{\n" +
"  \"language\": \"php\",\n" +
"  \"stages\": [\n" +
"    \"static\",\n" +
"    \"unit\",\n" +
"    \"integration\",\n" +
"    \"staging\"\n" +
"  ]\n" +
"}";
        
        // Act
        String result = model.makeConfigInput();
        
        // Assert
        assertEquals(actual, result);
    }
    
    @Test
    public void TestCorrectPipelineCreated()
    {
        
        // Arrange
        Model model = new Model();
        String language = "php";
        String localGitRepo = "./src/main/resources/";
        model.setLanguage(language);
        model.setLocalGitRepo(localGitRepo);
        
        ArrayList<String> stages = new ArrayList<String>();
        stages.add("static");
        stages.add("unit");
        stages.add("integration");
        stages.add("staging");
        Pipeline pipeline = new Pipeline(language, stages);
        
        model.createJson(pipeline);
        
        File config = new File(localGitRepo);
        
        // Act
        boolean result = config.exists();
        
        
        // Assert
        assertEquals(true, result);
        
    }
    
    @Test
    public void TestCreateJSONIsCorrect()
    {
    
        // Arrange
        Model model = new Model();
        String language = "php";
        String localGitRepo = "./src/main/resources/";
        model.setLanguage(language);
        model.setLocalGitRepo(localGitRepo);
        
        ArrayList<String> stages = new ArrayList<String>();
        stages.add("static");
        stages.add("unit");
        stages.add("integration");
        stages.add("staging");
        Pipeline pipeline = new Pipeline(language, stages);
        
        String actual = "{\n" +
"  \"language\": \"php\",\n" +
"  \"stages\": [\n" +
"    \"static\",\n" +
"    \"unit\",\n" +
"    \"integration\",\n" +
"    \"staging\"\n" +
"  ]\n" +
"}";
        
        // Act
        String result = model.createJson(pipeline);
        
        // Assert
        assertEquals(actual, result);
    
    }
    
    @Test
    public void TestModelSendsBuildMessageToJenkins()
    {
        
        //Arrange
       Model model = new Model();
       String jenkinsURL = "34.228.23.117";
       String branchName = "1";
       model.setBuildInput(jenkinsURL, branchName);
       model.makeBuildMessage();
       
      //Act
      boolean result = model.sendBuildMessage();
      
      // Assert
      assertEquals(true, result);
      
    }
    
}
