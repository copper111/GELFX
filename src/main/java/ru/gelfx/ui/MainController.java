package ru.gelfx.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gelfx.ControllersConfig;
import ru.gelfx.services.BashComandService;

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

    @Autowired
    private BashComandService bashComandService;


    public void onMouseClick(MouseEvent mouseEvent) {
        bashOut.setText(bashComandService.executeCommand(inputCommand.getText()));
        //bashComandService.runSafe(() -> inputCommand.getText());
    }


    public void onOpenRep(ActionEvent actionEvent) throws IOException {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Open repository directory");
        bashOut.setText(chooser.showDialog(mainRoot.getScene().getWindow()).getAbsolutePath());



        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/OpenRepDialog.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();*/

    }
}
