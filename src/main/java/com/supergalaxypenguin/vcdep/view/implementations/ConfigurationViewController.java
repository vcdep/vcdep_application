package com.supergalaxypenguin.vcdep.view.implementations;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.supergalaxypenguin.vcdep.controller.interfaces.iMainController;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ConfigurationViewController implements Initializable {
    
    private String gitTxt;
    private String branchTxt;
    private String jenkinsTxt;
    public static ConfigurationViewController instance;
    private static iMainController controller;
    private Stage stage;
    
    /**
     * Creates the ConfigurationViewController
     */
    public ConfigurationViewController()
    {
        instance = this;
    }
    
    /**
     * Sets the controller variable to the interface of the MainController,
     * This must be done before the view can be used.
     * @param _controller interface of the MainController
     */
    public void setMainControllerInterface(iMainController _controller)
    {
        this.controller = _controller;
    }
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    
    @FXML
    private ChoiceBox<String> lang;
    @FXML
    private Label label;
    @FXML
    private Label testLabel;
    @FXML
    private TextField gitUrl;
    @FXML
    private TextField branch;
    @FXML
    private TextField jenkins;
    @FXML
    private TextField localGitRepo;
    @FXML
    private Button submit;
    @FXML
    private Button browse;
    @FXML
    private ImageView penguin;
    @FXML
    private ImageView background;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        testLabel.setText(gitUrl.getText()+"\n"+branch.getText()+"\n"+jenkins.getText());
        // this area will change
        gitTxt = gitUrl.getText();
        branchTxt = branch.getText();
        jenkinsTxt = jenkins.getText();
        System.out.println(gitTxt+"\n"+branchTxt+"\n"+jenkinsTxt);
        //Check that all inputs are entered properly...

        //Set all inputs in Controller
        controller.setBranchName(branchTxt);
        controller.setGitHubURL(gitTxt);
        controller.setJenkinsURL(jenkinsTxt);
        //controller.setLanguage();
        //controller.setLocalRepo();
        //controller.setStages();
        //Ready to Run the Pipeline?

    }
    @FXML
    private void handleBrowseButton(ActionEvent event) {
        File selectedDirectory = controller.displayDirectoryChooser();
        if(selectedDirectory == null){
                    localGitRepo.setText("No Directory selected");
                }else{
                    localGitRepo.setText(selectedDirectory.getAbsolutePath());
                }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     lang.getItems().add("PHP");
     lang.getItems().add("Java");
     lang.setValue("Java");
    //this is initialization//   

// TODO
    }    
}
