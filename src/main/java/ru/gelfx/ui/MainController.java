package ru.gelfx.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gelfx.ControllersConfig;
import ru.gelfx.services.BashComandService;
import ru.gelfx.services.GitService;

import java.io.File;
import java.io.IOException;

/**
 * Created by aer on 25.11.16.
 */

public class MainController {

    public Button bashEnter;
    public Label bashOut;
    public TextField inputCommand;
    public MenuItem openRepMenu;
    public AnchorPane mainRoot;
    public MenuItem openTerminal;
    public MenuItem gitStatusRepMenu;
    public TextArea commonTextArea;

    @Autowired
    private BashComandService bashComandService;

    @Autowired
    private GitService gitService;

    private Git git;


    public void onMouseClick(MouseEvent mouseEvent) {
        bashOut.setText(bashComandService.executeCommand(inputCommand.getText()));
    }


    public void onOpenRep(ActionEvent actionEvent){

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Open repository directory");
        try {

            git = gitService.openExistingLocalRepository(chooser.showDialog(mainRoot.getScene().getWindow()).getAbsoluteFile());
            bashOut.setText("Успешно открыт репозиторий: " + git.getRepository().toString());
        } catch (IOException e) {
            bashOut.setText("Ошибка при открытии репозитория(IOException): " + e.getMessage());

        } catch (GitAPIException e) {
            bashOut.setText("Ошибка при открытии репозитория(GitAPIException): " + e.getMessage());
        }



        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/OpenRepDialog.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();*/

    }

    public void onOpenTerminal(ActionEvent actionEvent) throws IOException {
        bashComandService.openTerminal();
    }

    public void onGetStatus(ActionEvent actionEvent) throws GitAPIException {
        Status status = git.status().call();
        commonTextArea.setText(status.getUntracked().toString());
    }
}
