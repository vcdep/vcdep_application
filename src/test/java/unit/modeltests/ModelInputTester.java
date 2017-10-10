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
    public void TestModelIsAbleToSetEachInputVariableToInstanceVariables()
    {
        
        // Arrange
        Model model = new Model();
        String jenkinsURL = "jenkinsURL";
        String gitHubURL = "gitHubURL";
        String branchName = "branchName";
        String language = "language";
        String localGitRepo = "localGitRepo";
        
        // Act
        model.setInput(jenkinsURL, gitHubURL, branchName, language, localGitRepo);
        
        // Assert
        assertEquals(jenkinsURL, model.getJenkinsURL());
        assertEquals(gitHubURL, model.getGitHubURL());
        assertEquals(branchName, model.getBranchName());
        assertEquals(language, model.getLanguage());
        assertEquals(localGitRepo, model.getLocalGitRepo());

        
    }
    
}
