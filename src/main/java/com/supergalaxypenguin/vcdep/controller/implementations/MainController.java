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
    private String logFile = "Started by remote host 192.30.252.41\n" +
"Checking out git https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools to read Jenkinsfile\n" +
" > git rev-parse --is-inside-work-tree # timeout=10\n" +
"Fetching changes from the remote Git repository\n" +
" > git config remote.origin.url https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools # timeout=10\n" +
"Fetching upstream changes from https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools\n" +
" > git --version # timeout=10\n" +
"using GIT_ASKPASS to set credentials \n" +
" > git fetch --tags --progress https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools +refs/heads/*:refs/remotes/origin/*\n" +
" > git rev-parse refs/remotes/origin/shared_library^{commit} # timeout=10\n" +
" > git rev-parse refs/remotes/origin/origin/shared_library^{commit} # timeout=10\n" +
"Checking out Revision 0c891c963d57760521843a581c5619f6ef5b52c1 (refs/remotes/origin/shared_library)\n" +
" > git config core.sparsecheckout # timeout=10\n" +
" > git checkout -f 0c891c963d57760521843a581c5619f6ef5b52c1\n" +
" > git rev-list 0c891c963d57760521843a581c5619f6ef5b52c1 # timeout=10\n" +
"Loading library shared_libraries@shared_library\n" +
" > git rev-parse --is-inside-work-tree # timeout=10\n" +
"Setting origin to https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools.git\n" +
" > git config remote.origin.url https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools.git # timeout=10\n" +
"Fetching origin...\n" +
"Fetching upstream changes from origin\n" +
" > git --version # timeout=10\n" +
"using GIT_ASKPASS to set credentials \n" +
" > git fetch --tags --progress origin +refs/heads/*:refs/remotes/origin/*\n" +
" > git rev-parse shared_library^{commit} # timeout=10\n" +
" > git rev-parse origin/shared_library^{commit} # timeout=10\n" +
" > git rev-parse --is-inside-work-tree # timeout=10\n" +
"Fetching changes from the remote Git repository\n" +
" > git config remote.origin.url https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools.git # timeout=10\n" +
"Fetching upstream changes from https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools.git\n" +
" > git --version # timeout=10\n" +
"using GIT_ASKPASS to set credentials \n" +
" > git fetch --tags --progress https://github.com/UWF-HMCSE-CS/DevOps_ConfigurationTools.git +refs/heads/*:refs/remotes/origin/*\n" +
"Checking out Revision 0c891c963d57760521843a581c5619f6ef5b52c1 (shared_library)\n" +
" > git config core.sparsecheckout # timeout=10\n" +
" > git checkout -f 0c891c963d57760521843a581c5619f6ef5b52c1\n" +
" > git rev-list 0c891c963d57760521843a581c5619f6ef5b52c1 # timeout=10\n" +
"[Pipeline] node\n" +
"Running on docker_box in /home/ec2-user/workspace/jenkins_pipeline\n" +
"[Pipeline] {\n" +
"[Pipeline] dir\n" +
"Running in /home/ec2-user/workspace/DevOps\n" +
"[Pipeline] {\n" +
"[Pipeline] sh\n" +
"[DevOps] Running shell script\n" +
"+ git pull\n" +
"Already up-to-date.\n" +
"[Pipeline] stage\n" +
"[Pipeline] { (Checkout)\n" +
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
"[Pipeline] stage\n" +
"[Pipeline] { (Static Analysis)\n" +
"[Pipeline] dir\n" +
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
"FOUND 31 ERRORS AND 6 WARNINGS AFFECTING 25 LINES\n" +
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
" 38 | WARNING | [ ] Line exceeds 85 characters; contains 97\n" +
"    |         |     characters\n" +
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
"PHPCBF CAN FIX THE 32 MARKED SNIFF VIOLATIONS AUTOMATICALLY\n" +
"----------------------------------------------------------------------\n" +
"\n" +
"Time: 25ms; Memory: 4Mb\n" +
"\n" +
"[Pipeline] }\n" +
"$ docker stop --time=1 48eea2bbbf67954e3b32e286c7a91a64cda334dff9e46a4975650e88422eeb14\n" +
"$ docker rm -f 48eea2bbbf67954e3b32e286c7a91a64cda334dff9e46a4975650e88422eeb14\n" +
"[Pipeline] // withDockerContainer\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n" +
"[Pipeline] { (Unit Testing)\n" +
"[Pipeline] dir\n" +
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
"Time: 19 ms, Memory: 4.00Mb\n" +
"\n" +
"OK (6 tests, 6 assertions)\n" +
"[Pipeline] }\n" +
"$ docker stop --time=1 b62bf9a23e65914322806b533ea9df531f2fc1c055737668a535067c84f4137c\n" +
"$ docker rm -f b62bf9a23e65914322806b533ea9df531f2fc1c055737668a535067c84f4137c\n" +
"[Pipeline] // withDockerContainer\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n" +
"[Pipeline] { (Integration Testing)\n" +
"[Pipeline] dir\n" +
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
"........F                                                           9 / 9 (100%)\n" +
"\n" +
"Time: 27 ms, Memory: 4.00Mb\n" +
"\n" +
"There was 1 failure:\n" +
"\n" +
"1) Tester::testManufacturer\n" +
"Failed asserting that two strings are equal.\n" +
"--- Expected\n" +
"+++ Actual\n" +
"@@ @@\n" +
"-'Alienware'\n" +
"+'AlienWare'\n" +
"\n" +
"/cdep/tests/Tester.php:85\n" +
"\n" +
"FAILURES!\n" +
"Tests: 9, Assertions: 8, Failures: 1.\n" +
"[Pipeline] }\n" +
"$ docker stop --time=1 9e2c4ffbefe5cd76174baeceb02ca7626e6ca554ff6ad77b81b70dd887e62406\n" +
"$ docker rm -f 9e2c4ffbefe5cd76174baeceb02ca7626e6ca554ff6ad77b81b70dd887e62406\n" +
"[Pipeline] // withDockerContainer\n" +
"[Pipeline] }\n" +
"[Pipeline] // dir\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n" +
"[Pipeline] { (Staging)\n" +
"[Pipeline] echo\n" +
"Skipping due to failure\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n" +
"[Pipeline] { (Merging)\n" +
"[Pipeline] echo\n" +
"Skipping due to failure\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] stage\n" +
"[Pipeline] { (Cleanup)\n" +
"[Pipeline] sh\n" +
"[jenkins_pipeline] Running shell script\n" +
"+ sudo rm -rf /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15\n" +
"[Pipeline] sh\n" +
"[jenkins_pipeline] Running shell script\n" +
"+ sudo rm -rf /home/ec2-user/workspace/jenkins_pipeline/se2_devops_tr_15@tmp\n" +
"[Pipeline] }\n" +
"[Pipeline] // stage\n" +
"[Pipeline] }\n" +
"[Pipeline] // node\n" +
"[Pipeline] End of Pipeline\n" +
"Finished: FAILURE";
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
        scene.getStylesheets().add("/styles/Styles.css");
        javaFXStage.setTitle("Configuration Viewer");
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
        scene.getStylesheets().add("/styles/Styles.css");
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
     * Gets the last Log File received from the Jenkins Server
     * @return Returns a String containing the file.
     */
    @Override
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
        model.makeConfigInput();
        model.setBuildInput(jenkinsURL, branchName);
        model.makeBuildMessage();
//        model.start();
        
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
    /**
     * Function to get the checkout status from the log file
     * @return String representing the checkout status
     */
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
       while(!checkoutArray[++index].contains("BUILD"))
       {
          continue; //LOL
       }
       String buildArray;
       index = index -1;
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
    /**
     * Function to get the status of static analysis from the log file
     * @return String representing the status of the SA stage
     */
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

    /**
     * Function to get the status of unit testing from the log file
     * @return String representing the unit testing status
     */
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
    /**
     * Function to get the integration status from the log file
     * @return String representing the integration stage 
     */
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
    /**
     * Function to get the deployment status from the log file
     * @return String representing the deployment status
     */
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
              + "standards, looking for ways to improve your coding structure.\n";
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
              + "that your changes still pass their respective unit tests.\n";
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
              + " ensuring that the changes have not caused any integration issues.\n";
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
              + "machines and checking that the new code is compatible with your production hardware and software.\n";
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
    
}