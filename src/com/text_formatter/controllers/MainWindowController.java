package com.text_formatter.controllers;

import com.text_formatter.Run;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class MainWindowController {

    private static int repeatedSpaces, individualLines;
    @FXML
    TextArea textArea;
    @FXML
    Button btnCopy;

    @FXML
    public void handleBtnCancel() {
        Run.stage.close();
    }

    @FXML
    public void handleBtnFormat() {
        String inputText = textArea.getText().trim();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) == '\n') {
                builder.append(' ');
                individualLines++;
                continue;
            }

            builder.append(inputText.charAt(i));
        }

        String filtered = removeRepetitiveSpaces(builder.toString());
        textArea.setText(filtered);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Text Enhancements");
        alert.setHeaderText("Found " + individualLines + " individual lines and " + repeatedSpaces + " repeated spaces.");
        alert.setContentText("Thanks for using our service.");
        alert.show();

        btnCopy.setDisable(false);
        repeatedSpaces = 0;
        individualLines = 0;
    }

    @FXML
    public void handleBtnCopy() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(textArea.getText());
        clipboard.setContent(content);
        btnCopy.setDisable(true);
    }

    @FXML
    public void handleBtnContact() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(URI.create("https://www.twitter.com/ssadawi__"));
        desktop.browse(URI.create("https://www.github.com/ssadawi"));
    }

    private String removeRepetitiveSpaces(String input) {
        StringBuilder builder = new StringBuilder();
        boolean isInSpaceSequence = false;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 32 || input.charAt(i) == 9) {
                if (isInSpaceSequence) {
                    repeatedSpaces++;
                    continue;
                }

                builder.append(' ');
                isInSpaceSequence = true;
            } else {
                isInSpaceSequence = false;
                builder.append(input.charAt(i));
            }
        }

        return builder.toString();
    }
}
