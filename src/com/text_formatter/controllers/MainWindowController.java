package com.text_formatter.controllers;

import com.text_formatter.Run;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainWindowController {

    @FXML
    TextArea textArea;

    @FXML
    Button btnCancel, btnFormat;

    @FXML
    public void handleBtnCancel() {
        Run.stage.close();
    }

    @FXML
    public void handleBtnFormat() {
        String inputText = textArea.getText();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) == '\n') {
                builder.append(' ');
                continue;
            }

            builder.append(inputText.charAt(i));
        }

        textArea.setText(builder.toString());
    }
}
