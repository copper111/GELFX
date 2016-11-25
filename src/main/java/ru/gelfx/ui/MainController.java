package ru.gelfx.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gelfx.services.BashComandService;

/**
 * Created by aer on 25.11.16.
 */


public class MainController {

    public Button bashEnter;
    public Label bashOut;
    public TextField inputCommand;

    @Autowired
    private BashComandService bashComandService;

    public void onMouseClick(MouseEvent mouseEvent) {
        bashOut.setText(bashComandService.executeCommand(inputCommand.getText()));
    }


}
