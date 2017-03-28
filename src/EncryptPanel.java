import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;

/**
 * Created by Acerolla (Evgeniy Solovev) on 10.05.2016.
 */
public class EncryptPanel {

    static boolean hasKey;
    static boolean hasFile;

    public static void dysplay(Stage mainPanel) {

        hasKey  = Boolean.FALSE;
        hasFile = Boolean.FALSE;

        mainPanel.hide();


        Stage encryptPanel = new Stage();
        encryptPanel.initModality(Modality.APPLICATION_MODAL);

        Pane pane = new Pane();
        encryptPanel.setScene(new Scene(pane, 300, 400));
        encryptPanel.getScene().setFill(Color.BLUE);
        encryptPanel.setTitle("Encrypt IDEA");
        encryptPanel.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainPanel.show();
            }
        });
        encryptPanel.setResizable(Boolean.FALSE);

        IDEA idea = new IDEA();


        Label keyLabel          = new Label("Key:");
        TextArea keyArea        = new TextArea();
        Button generateButton   = new Button("Generate");
        Button saveKeyButton    = new Button("Save Key");
        TextArea filePathArea   = new TextArea();
        Button loadFileButton   = new Button("Load File");
        Button saveFileButton   = new Button("Encrypt and save to...");


        keyLabel.setLayoutX(20);
        keyLabel.setLayoutY(50);


        keyArea.setEditable(Boolean.FALSE);
        keyArea.setLayoutX(50);
        keyArea.setLayoutY(43);
        keyArea.setPrefSize(150, 20);


        generateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hasKey = Boolean.TRUE;
                saveKeyButton.setDisable(Boolean.FALSE);
                if (hasFile) {
                    saveFileButton.setDisable(Boolean.FALSE);
                }
                idea.generateKey();
                keyArea.setText(idea.getKey());
            }
        });
        generateButton.setLayoutX(90);
        generateButton.setLayoutY(83);


        saveKeyButton.setDisable(Boolean.TRUE);
        saveKeyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save the key");
                fileChooser.setInitialFileName("key.out");
                try {
                    File file = fileChooser.showSaveDialog(encryptPanel);

                    file.createNewFile();
                    PrintWriter writer = new PrintWriter(file.getPath(), "UTF-8");
                    writer.print(keyArea.getText());
                    writer.close();

                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Cannot save file");
                    alert.show();
                } catch (Exception e) {
                    //
                    //
                    //������� � ����
                    //
                    //
                    System.out.println("Non save");
                }


            }
        });
        saveKeyButton.setLayoutX(205);
        saveKeyButton.setLayoutY(47);


        filePathArea.setLayoutX(85);
        filePathArea.setLayoutY(143);
        filePathArea.setPrefSize(150, 20);
        filePathArea.setEditable(Boolean.FALSE);


        loadFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Load File to Encrypt");
                    File file = fileChooser.showOpenDialog(encryptPanel);
                    filePathArea.setText(file.getPath());

                    hasFile = Boolean.TRUE;
                    if (hasKey) {
                        saveFileButton.setDisable(Boolean.FALSE);
                    }
                } catch (Exception e) {
                    //
                    //
                    //������� � ����
                    //
                    //
                    System.out.println("Non open");


                }


            }
        });
        loadFileButton.setLayoutX(20);
        loadFileButton.setLayoutY(150);


        saveFileButton.setDisable(Boolean.TRUE);
        saveFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String fileName = filePathArea.getText();
                fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Path to encrypted file...");
                fileChooser.setInitialFileName(fileName);
                File file = fileChooser.showSaveDialog(encryptPanel);
                //
                //
                //���������� ����!!!
                //
                //
                Crypt crypt = new Crypt(filePathArea.getText(), file, Boolean.TRUE, idea);
                try {
                    crypt.start();
                } catch (Exception e) {
                    System.out.println("Crypt.start()");
                }
            }
        });
        saveFileButton.setLayoutX(80);
        saveFileButton.setLayoutY(250);


        pane.getChildren().addAll(
                keyLabel,
                keyArea,
                generateButton,
                saveKeyButton,
                filePathArea,
                loadFileButton,
                saveFileButton
        );




        encryptPanel.show();



    }
}
