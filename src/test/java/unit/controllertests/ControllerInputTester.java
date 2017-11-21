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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
    @Test
    public void TestSearchMethod() throws FileNotFoundException
    {
       //Arrange
       MainController controller = MainController.getInstance();
       File logFile = new File("./src/main/resources/textFiles/TestconsoleText.txt");
       Scanner logScan = new Scanner(logFile);
       String logText = "";
       while(logScan.hasNextLine())
       {
          logText = logText + logScan.nextLine() + "\n";
       }
       controller.setLogFile(logText);
       //Act
       int result = controller.search("[Pipeline] { (Static Analysis)");
       //Assert
       assertNotEquals(result, -1);
    }
    @Test
    public void TestGetCheckoutStageInfoMethod() throws FileNotFoundException
    {
       //Arrange
       MainController controller = MainController.getInstance();
       File logFile = new File("./src/main/resources/textFiles/TestconsoleText.txt");
       Scanner logScan = new Scanner(logFile);
       String actual = 
"[Pipeline] sh\n" +
"[DevOps] Running shell script\n" +
"+ git clone git@github.com:DevOpsActivity/se2_devops_tr_15.git /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15\n" +
"Cloning into '/home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15'...\n" +
"[Pipeline] dir\n" +
"Running in /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[se2_devops_tr_15] Running shell script\n" +
"+ git checkout feature\n" +
"Switched to a new branch 'feature'\n" +
"Branch feature set up to track remote branch feature from origin.\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] echo\n" +
"Updated the tester repo\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] dir\n" +
"Running in /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[se2_devops_tr_15] Running shell script\n" +
"+ cat config.json\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] stage\n";
       String logText = "";
       while(logScan.hasNextLine())
       {
          logText = logText + logScan.nextLine() + "\n";
       }
       controller.setLogFile(logText);
       //Act
       String result = controller.getCheckoutStatus();
       //Assert
       assertEquals(result, actual);
    }
    /*@Test
    public void TestGetBuildStageInfoMethod()
    {
       //Arrange
       MainController controller = MainController.getInstance();
       File logFile = new File("./src/main/resources/textFiles/TestconsoleText.txt");
       Scanner logScan = new Scanner(logFile);
       String actual = ;
       String logText = "";
       while(logScan.hasNextLine())
       {
          logText = logText + logScan.nextLine() + "\n";
       }
       controller.setLogFile(logText);
       //Act
       String result = controller.getCheckoutStatus();
       System.out.println(result);
       //Assert
       assertEquals(result, actual);
    }  */
    @Test
    public void TestGetStaticAnalysisStageInfoMethod() throws FileNotFoundException
    {
       //Arrange
       MainController controller = MainController.getInstance();
       File logFile = new File("./src/main/resources/textFiles/TestconsoleText.txt");
       Scanner logScan = new Scanner(logFile);
       String actual = "[Pipeline] dir\n" +
"Running in /home/ec2-user/workspace/DevOps/tests/php/static\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[static] Running shell script\n" +
"+ docker build -t static .\n" +
"Sending build context to Docker daemon  7.68 kB\n" +
"\n" +
"Step 1 : FROM ubuntu:latest\n" +
" ---> 6a2f32de169d\n" +
"Step 2 : RUN apt-get update && apt-get -y install php-pear && pear install PHP_CodeSniffer\n" +
" ---> Using cache\n" +
" ---> 2cdaf11cca55\n" +
"Step 3 : EXPOSE 8080\n" +
" ---> Using cache\n" +
" ---> fa976668245a\n" +
"Successfully built fa976668245a\n" +
"[Pipeline] dockerFingerprintFrom\n" +
"[Pipeline] sh\n" +
"[static] Running shell script\n" +
"+ docker inspect -f . static\n" +
".\n" +
"[Pipeline] withDockerContainer\n" +
"$ docker run -t -d -u 500:500 -v /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15:/cdep -w /home/ec2-user/workspace/DevOps/tests/php/static -v /home/ec2-user/workspace/DevOps/tests/php/static:/home/ec2-user/workspace/DevOps/tests/php/static:rw -v /home/ec2-user/workspace/DevOps/tests/php/static@tmp:/home/ec2-user/workspace/DevOps/tests/php/static@tmp:rw -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** --entrypoint cat static\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[static] Running shell script\n" +
"+ bash build.sh\n" +
"\n" +
"FILE: /cdep/index/Gadget.php\n" +
"----------------------------------------------------------------------\n" +
"FOUND 283 ERRORS AND 1 WARNING AFFECTING 100 LINES\n" +
"----------------------------------------------------------------------\n" +
"   2 | ERROR   | [ ] Missing file doc comment\n" +
"   4 | ERROR   | [ ] Missing class doc comment\n" +
"   4 | ERROR   | [x] Opening brace of a class must be on the line\n" +
"     |         |     after the definition\n" +
"   6 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"   6 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"   6 | ERROR   | [ ] Private member variable \"item\" must be prefixed\n" +
"     |         |     with an underscore\n" +
"   7 | ERROR   | [ ] Private member variable \"name\" must be prefixed\n" +
"     |         |     with an underscore\n" +
"   8 | ERROR   | [ ] Private member variable \"model\" must be prefixed\n" +
"     |         |     with an underscore\n" +
"   9 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"   9 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"   9 | ERROR   | [ ] Private member variable \"cost\" must be prefixed\n" +
"     |         |     with an underscore\n" +
"  10 | ERROR   | [ ] Private member variable \"manufacturer\" must be\n" +
"     |         |     prefixed with an underscore\n" +
"  11 | ERROR   | [ ] Private member variable \"link\" must be prefixed\n" +
"     |         |     with an underscore\n" +
"  12 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  12 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  12 | ERROR   | [ ] Private member variable \"imgName\" must be\n" +
"     |         |     prefixed with an underscore\n" +
"  13 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  13 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  13 | ERROR   | [ ] Private member variable \"icon\" must be prefixed\n" +
"     |         |     with an underscore\n" +
"  15 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  15 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  15 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$item\" missing\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$name\" missing\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$model\" missing\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$cost\" missing\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$manufacturer\"\n" +
"     |         |     missing\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$link\" missing\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$imgName\" missing\n" +
"  15 | ERROR   | [ ] Doc comment for parameter \"$icon\" missing\n" +
"  15 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  15 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  16 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  16 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  16 | WARNING | [ ] Line exceeds 85 characters; contains 96\n" +
"     |         |     characters\n" +
"  17 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  17 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  19 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  19 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  20 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  20 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  21 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  21 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  22 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  22 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  23 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  23 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  24 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  24 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  25 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  25 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  26 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  26 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  28 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  28 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  30 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  30 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  30 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  30 | ERROR   | [ ] Doc comment for parameter \"$item\" missing\n" +
"  30 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  30 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  30 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  31 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  31 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  32 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  32 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  34 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  34 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  36 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  36 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  38 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  38 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  38 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  38 | ERROR   | [ ] Doc comment for parameter \"$name\" missing\n" +
"  38 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  38 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  38 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  39 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  39 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  40 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  40 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  42 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  42 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
"  44 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  44 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  46 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  46 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  46 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  46 | ERROR   | [ ] Doc comment for parameter \"$model\" missing\n" +
"  46 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  46 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  46 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  48 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  48 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  48 | ERROR   | [x] Opening brace indented incorrectly; expected 4\n" +
"     |         |     spaces, found 1\n" +
"  50 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  52 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  52 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  52 | ERROR   | [x] Closing brace indented incorrectly; expected 4\n" +
"     |         |     spaces, found 1\n" +
"  54 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  54 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  54 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  54 | ERROR   | [ ] Doc comment for parameter \"$cost\" missing\n" +
"  54 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  54 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  54 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  55 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  55 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  56 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  56 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  58 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  60 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  60 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  62 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  62 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  62 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  62 | ERROR   | [ ] Doc comment for parameter \"$manufacturer\"\n" +
"     |         |     missing\n" +
"  62 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  62 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  62 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  63 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  63 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 2\n" +
"  64 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  64 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  64 | ERROR   | [x] Opening brace indented incorrectly; expected 2\n" +
"     |         |     spaces, found 1\n" +
"  66 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  68 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  68 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  68 | ERROR   | [x] Closing brace indented incorrectly; expected 2\n" +
"     |         |     spaces, found 1\n" +
"  70 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  70 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  70 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  70 | ERROR   | [ ] Doc comment for parameter \"$link\" missing\n" +
"  70 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  70 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  70 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  71 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  71 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  72 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  72 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  74 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  74 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 6\n" +
"  76 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  76 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  78 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  78 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  78 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  78 | ERROR   | [ ] Doc comment for parameter \"$imgName\" missing\n" +
"  78 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  78 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  78 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  79 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  79 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  80 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  80 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  82 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  82 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 6\n" +
"  84 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  84 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  86 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  86 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  86 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  86 | ERROR   | [ ] Doc comment for parameter \"$icon\" missing\n" +
"  86 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  86 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  86 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  87 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  87 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  88 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  88 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  90 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  90 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 6\n" +
"  92 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  92 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"  94 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  94 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  94 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
"  94 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
"  94 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
"  94 | ERROR   | [ ] Missing @return tag in function comment\n" +
"  96 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  96 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
"  96 | ERROR   | [x] Opening brace indented incorrectly; expected 4\n" +
"     |         |     spaces, found 1\n" +
"  98 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
"  98 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 3\n" +
" 100 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 100 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 100 | ERROR   | [x] Closing brace indented incorrectly; expected 4\n" +
"     |         |     spaces, found 1\n" +
" 102 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 102 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 102 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
" 102 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
" 102 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
" 102 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 103 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 104 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 104 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 104 | ERROR   | [x] Opening brace indented incorrectly; expected 4\n" +
"     |         |     spaces, found 1\n" +
" 106 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 106 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 6\n" +
" 108 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 108 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 108 | ERROR   | [x] Closing brace indented incorrectly; expected 4\n" +
"     |         |     spaces, found 1\n" +
" 110 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 110 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 110 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
" 110 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
" 110 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
" 112 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 112 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 112 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
" 112 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
" 112 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
" 114 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 114 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 114 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
" 114 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
" 114 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
" 114 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 115 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 115 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 116 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 116 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 118 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 118 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 6\n" +
" 120 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 120 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 122 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 122 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 122 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
" 122 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
" 122 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
" 122 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 123 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 123 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 124 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 124 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 126 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 128 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 128 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 130 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 130 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 130 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
" 130 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
" 130 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
" 130 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 131 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 131 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 132 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 132 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 134 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 136 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 136 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 138 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 138 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 138 | ERROR   | [x] The open comment tag must be the only content on\n" +
"     |         |     the line\n" +
" 138 | ERROR   | [x] Doc comment short description must be on the\n" +
"     |         |     first line\n" +
" 138 | ERROR   | [x] The close comment tag must be the only content\n" +
"     |         |     on the line\n" +
" 138 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 139 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 139 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 140 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 140 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 142 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 144 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 144 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 146 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 146 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 146 | ERROR   | [ ] Missing function doc comment\n" +
" 147 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 147 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 148 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 148 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
" 149 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 149 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 150 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 150 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
" 150 | ERROR   | [ ] Missing function doc comment\n" +
" 151 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 151 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"     |         |     spaces, found 1\n" +
" 152 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 152 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"     |         |     spaces, found 2\n" +
" 153 | ERROR   | [x] Spaces must be used to indent lines; tabs are\n" +
"     |         |     not allowed\n" +
" 153 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"     |         |     found 1\n" +
"----------------------------------------------------------------------\n" +
"PHPCBF CAN FIX THE 241 MARKED SNIFF VIOLATIONS AUTOMATICALLY\n" +
"----------------------------------------------------------------------\n" +
"\n" +
"\n" +
"FILE: /cdep/index/databaseConnection.php\n" +
"----------------------------------------------------------------------\n" +
"FOUND 142 ERRORS AND 1 WARNING AFFECTING 58 LINES\n" +
"----------------------------------------------------------------------\n" +
"  2 | ERROR   | [ ] Missing file doc comment\n" +
"  3 | ERROR   | [x] \"require_once\" is a statement not a function; no\n" +
"    |         |     parentheses are required\n" +
"  5 | ERROR   | [ ] Missing class doc comment\n" +
"  5 | ERROR   | [ ] Class name must begin with a capital letter\n" +
"  5 | ERROR   | [x] Opening brace of a class must be on the line\n" +
"    |         |     after the definition\n" +
"  7 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
"  7 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
"  8 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
"  8 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
"  9 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
"  9 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 10 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 10 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 11 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 11 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 13 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 13 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 13 | ERROR   | [x] The open comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 13 | ERROR   | [x] Doc comment short description must be on the\n" +
"    |         |     first line\n" +
" 13 | ERROR   | [x] The close comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 14 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 14 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 15 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 15 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 17 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 18 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 19 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 20 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 22 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 22 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 24 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 24 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 24 | ERROR   | [x] The open comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 24 | ERROR   | [x] Doc comment short description must be on the\n" +
"    |         |     first line\n" +
" 24 | ERROR   | [x] The close comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 24 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 25 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 25 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 26 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 26 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 28 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 28 | ERROR   | [x] Line indented incorrectly; expected 8 spaces,\n" +
"    |         |     found 5\n" +
" 29 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 29 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 31 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 31 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 31 | WARNING | [ ] Line exceeds 85 characters; contains 110\n" +
"    |         |     characters\n" +
" 33 | ERROR   | [x] Closing brace indented incorrectly; expected 5\n" +
"    |         |     spaces, found 8\n" +
" 34 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 34 | ERROR   | [x] Line indented incorrectly; expected 8 spaces,\n" +
"    |         |     found 2\n" +
" 35 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 35 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 37 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 37 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 3\n" +
" 39 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 39 | ERROR   | [x] Line indented incorrectly; expected 8 spaces,\n" +
"    |         |     found 2\n" +
" 41 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 41 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 43 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 43 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 43 | ERROR   | [x] The open comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 43 | ERROR   | [ ] Doc comment for parameter \"$id\" missing\n" +
" 43 | ERROR   | [x] Doc comment short description must be on the\n" +
"    |         |     first line\n" +
" 43 | ERROR   | [x] The close comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 43 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 44 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 44 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 45 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 45 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 47 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 47 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 48 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 48 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 49 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 49 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 50 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 50 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 51 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 51 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 53 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 53 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 55 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 55 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 55 | ERROR   | [x] The open comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 55 | ERROR   | [ ] Doc comment for parameter \"$id\" missing\n" +
" 55 | ERROR   | [x] Doc comment short description must be on the\n" +
"    |         |     first line\n" +
" 55 | ERROR   | [x] The close comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 55 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 56 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 56 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 57 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 57 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 59 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 59 | ERROR   | [x] Line indented incorrectly; expected 8 spaces,\n" +
"    |         |     found 2\n" +
" 59 | ERROR   | [ ] Expected \"foreach (...) {\\n\"; found\n" +
"    |         |     \"foreach(...)\\n\\t\\t{\\n\"\n" +
" 60 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 60 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 62 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 62 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 3\n" +
" 62 | ERROR   | [x] Opening parenthesis of a multi-line function call\n" +
"    |         |     must be the last content on the line\n" +
" 63 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 63 | ERROR   | [x] Multi-line function call not indented correctly;\n" +
"    |         |     expected 7 spaces but found 9\n" +
" 63 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 64 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 64 | ERROR   | [x] Multi-line function call not indented correctly;\n" +
"    |         |     expected 7 spaces but found 9\n" +
" 64 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 65 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 65 | ERROR   | [x] Multi-line function call not indented correctly;\n" +
"    |         |     expected 7 spaces but found 9\n" +
" 65 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 66 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 66 | ERROR   | [x] Multi-line function call not indented correctly;\n" +
"    |         |     expected 7 spaces but found 9\n" +
" 66 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 67 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 67 | ERROR   | [x] Multi-line function call not indented correctly;\n" +
"    |         |     expected 7 spaces but found 9\n" +
" 67 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 68 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 68 | ERROR   | [x] Multi-line function call not indented correctly;\n" +
"    |         |     expected 7 spaces but found 9\n" +
" 68 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 69 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 69 | ERROR   | [x] Multi-line function call not indented correctly;\n" +
"    |         |     expected 3 spaces but found 9\n" +
" 69 | ERROR   | [x] Line indented incorrectly; expected at least 12\n" +
"    |         |     spaces, found 9\n" +
" 69 | ERROR   | [x] Closing parenthesis of a multi-line function call\n" +
"    |         |     must be on a line by itself\n" +
" 71 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 71 | ERROR   | [x] Line indented incorrectly; expected 8 spaces,\n" +
"    |         |     found 2\n" +
" 73 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 73 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 75 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 75 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 77 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 77 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 77 | ERROR   | [x] The open comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 77 | ERROR   | [ ] Doc comment for parameter \"$ID\" missing\n" +
" 77 | ERROR   | [x] Doc comment short description must be on the\n" +
"    |         |     first line\n" +
" 77 | ERROR   | [x] The close comment tag must be the only content on\n" +
"    |         |     the line\n" +
" 77 | ERROR   | [ ] Missing @return tag in function comment\n" +
" 78 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 78 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
" 79 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 79 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 1\n" +
" 81 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 81 | ERROR   | [x] Line indented incorrectly; expected at least 8\n" +
"    |         |     spaces, found 2\n" +
" 83 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 83 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 1\n" +
"----------------------------------------------------------------------\n" +
"PHPCBF CAN FIX THE 131 MARKED SNIFF VIOLATIONS AUTOMATICALLY\n" +
"----------------------------------------------------------------------\n" +
"\n" +
"\n" +
"FILE: /cdep/index/index.php\n" +
"----------------------------------------------------------------------\n" +
"FOUND 33 ERRORS AND 5 WARNINGS AFFECTING 26 LINES\n" +
"----------------------------------------------------------------------\n" +
" 26 | ERROR   | [ ] Missing file doc comment\n" +
" 27 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 27 | ERROR   | [x] \"require_once\" is a statement not a function; no\n" +
"    |         |     parentheses are required\n" +
" 28 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 29 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 30 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 30 | WARNING | [x] Inline control structures are discouraged\n" +
" 30 | ERROR   | [x] TRUE, FALSE and NULL must be lowercase; expected\n" +
"    |         |     \"null\" but found \"NULL\"\n" +
" 31 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 32 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 32 | WARNING | [x] Inline control structures are discouraged\n" +
" 32 | ERROR   | [x] Line indented incorrectly; expected 0 spaces,\n" +
"    |         |     found 1\n" +
" 35 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 38 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 39 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 40 | WARNING | [ ] Line exceeds 85 characters; contains 103\n" +
"    |         |     characters\n" +
" 43 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 44 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 45 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 45 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 2\n" +
" 47 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 47 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 3\n" +
" 48 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 48 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 3\n" +
" 49 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 49 | ERROR   | [x] Line indented incorrectly; expected at least 4\n" +
"    |         |     spaces, found 3\n" +
" 51 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 51 | ERROR   | [x] Line indented incorrectly; expected 4 spaces,\n" +
"    |         |     found 2\n" +
" 52 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 54 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 55 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 56 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 56 | WARNING | [ ] Line exceeds 85 characters; contains 109\n" +
"    |         |     characters\n" +
" 57 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 57 | WARNING | [ ] Line exceeds 85 characters; contains 109\n" +
"    |         |     characters\n" +
" 58 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 59 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
" 61 | ERROR   | [x] Spaces must be used to indent lines; tabs are not\n" +
"    |         |     allowed\n" +
"----------------------------------------------------------------------\n" +
"PHPCBF CAN FIX THE 34 MARKED SNIFF VIOLATIONS AUTOMATICALLY\n" +
"----------------------------------------------------------------------\n" +
"\n" +
"Time: 25ms; Memory: 4Mb\n" +
"\n" +
"[Pipeline] }\n" +
"$ docker stop --time=1 10518dfdbbf9156bbe2743f1d4539708b05a17d7df00383fc765beb3eadfae78\n" +
"$ docker rm -f 10518dfdbbf9156bbe2743f1d4539708b05a17d7df00383fc765beb3eadfae78\n" +
"[Pipeline] // withDockerContainer\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n";
       String logText = "";
       while(logScan.hasNextLine())
       {
          logText = logText + logScan.nextLine() + "\n";
       }
       controller.setLogFile(logText);
       //Act
       String result = controller.getStaticAnalysisStatus();
       //System.out.println(result);
       //Assert
       assertEquals(result, actual);
       
    }
    @Test
    public void TestGetUnitTestingStageInfoMethod() throws FileNotFoundException
    {
       //Arrange
       MainController controller = MainController.getInstance();
       File logFile = new File("./src/main/resources/textFiles/TestconsoleText.txt");
       Scanner logScan = new Scanner(logFile);
       String actual = "[Pipeline] dir\n" +
"Running in /home/ec2-user/workspace/DevOps/tests/php/unit\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[unit] Running shell script\n" +
"+ docker build -t unit .\n" +
"Sending build context to Docker daemon 3.072 kB\n" +
"\n" +
"Step 1 : FROM ubuntu:latest\n" +
" ---> 6a2f32de169d\n" +
"Step 2 : RUN apt-get update && apt-get -y install phpunit\n" +
" ---> Using cache\n" +
" ---> 68c8a4870c90\n" +
"Step 3 : EXPOSE 8080\n" +
" ---> Using cache\n" +
" ---> a3ce385df138\n" +
"Successfully built a3ce385df138\n" +
"[Pipeline] dockerFingerprintFrom\n" +
"[Pipeline] sh\n" +
"[unit] Running shell script\n" +
"+ docker inspect -f . unit\n" +
".\n" +
"[Pipeline] withDockerContainer\n" +
"$ docker run -t -d -u 500:500 -v /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15:/cdep -w /home/ec2-user/workspace/DevOps/tests/php/unit -v /home/ec2-user/workspace/DevOps/tests/php/unit:/home/ec2-user/workspace/DevOps/tests/php/unit:rw -v /home/ec2-user/workspace/DevOps/tests/php/unit@tmp:/home/ec2-user/workspace/DevOps/tests/php/unit@tmp:rw -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** --entrypoint cat unit\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[unit] Running shell script\n" +
"+ bash build.sh\n" +
"PHPUnit 5.1.3 by Sebastian Bergmann and contributors.\n" +
"\n" +
"......                                                              6 / 6 (100%)\n" +
"\n" +
"Time: 22 ms, Memory: 4.00Mb\n" +
"\n" +
"OK (6 tests, 6 assertions)\n" +
"[Pipeline] }\n" +
"$ docker stop --time=1 9d9dc6f2fda14c4c709284d335b23311f9a03740860fbe4370ff53650b2ea552\n" +
"$ docker rm -f 9d9dc6f2fda14c4c709284d335b23311f9a03740860fbe4370ff53650b2ea552\n" +
"[Pipeline] // withDockerContainer\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n";
       String logText = "";
       while(logScan.hasNextLine())
       {
          logText = logText + logScan.nextLine() + "\n";
       }
       controller.setLogFile(logText);
       //Act
       String result = controller.getUnitTestStatus();
       //System.out.println(result);
       //Assert
       assertEquals(result, actual);
       
    }
    @Test
    public void TestGetIntegrationStageInfoMethod() throws FileNotFoundException
    {
       //Arrange
       MainController controller = MainController.getInstance();
       File logFile = new File("./src/main/resources/textFiles/TestconsoleText.txt");
       Scanner logScan = new Scanner(logFile);
       String actual = "[Pipeline] dir\n" +
"Running in /home/ec2-user/workspace/DevOps/tests/php/integration\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[integration] Running shell script\n" +
"+ docker build -t integration .\n" +
"Sending build context to Docker daemon 3.072 kB\n" +
"\n" +
"Step 1 : FROM ubuntu:latest\n" +
" ---> 6a2f32de169d\n" +
"Step 2 : RUN apt-get update && apt-get -y install php phpunit php-mysql\n" +
" ---> Using cache\n" +
" ---> 9fe49db15793\n" +
"Successfully built 9fe49db15793\n" +
"[Pipeline] dockerFingerprintFrom\n" +
"[Pipeline] sh\n" +
"[integration] Running shell script\n" +
"+ docker inspect -f . integration\n" +
".\n" +
"[Pipeline] withDockerContainer\n" +
"$ docker run -t -d -u 500:500 --link database:db -v /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15:/cdep -w /home/ec2-user/workspace/DevOps/tests/php/integration -v /home/ec2-user/workspace/DevOps/tests/php/integration:/home/ec2-user/workspace/DevOps/tests/php/integration:rw -v /home/ec2-user/workspace/DevOps/tests/php/integration@tmp:/home/ec2-user/workspace/DevOps/tests/php/integration@tmp:rw -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** --entrypoint cat integration\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[integration] Running shell script\n" +
"+ bash build.sh\n" +
"PHPUnit 5.1.3 by Sebastian Bergmann and contributors.\n" +
"\n" +
".........                                                           9 / 9 (100%)\n" +
"\n" +
"Time: 26 ms, Memory: 4.00Mb\n" +
"\n" +
"OK (9 tests, 8 assertions)\n" +
"[Pipeline] }\n" +
"$ docker stop --time=1 5a3c49a0d94dc8773b755b290d339a0505eb910bd799846d32c7ece3d77ecdd7\n" +
"$ docker rm -f 5a3c49a0d94dc8773b755b290d339a0505eb910bd799846d32c7ece3d77ecdd7\n" +
"[Pipeline] // withDockerContainer\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n";
       String logText = "";
       while(logScan.hasNextLine())
       {
          logText = logText + logScan.nextLine() + "\n";
       }
       controller.setLogFile(logText);
       //Act
       String result = controller.getIntegrationStatus();
       //System.out.println(result);
       //Assert
       assertEquals(result, actual);
       
    }
    @Test
    public void TestGetDeploymentStageInfoMethod() throws FileNotFoundException
    {
       //Arrange
       MainController controller = MainController.getInstance();
       File logFile = new File("./src/main/resources/textFiles/TestconsoleText.txt");
       Scanner logScan = new Scanner(logFile);
       String actual = "[Pipeline] sh\n" +
"[jenkins_pipeline] Running shell script\n" +
"+ bash /home/ec2-user/workspace/DevOps/tests/staging/stage/php_stage/bash/push.sh se2_devops_tr_15\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n";
       String logText = "";
       while(logScan.hasNextLine())
       {
          logText = logText + logScan.nextLine() + "\n";
       }
       controller.setLogFile(logText);
       //Act
       String result = controller.getDeploymentStatus();
       System.out.println(result);
       //Assert
       assertEquals(result, actual);
       
    }    
}
