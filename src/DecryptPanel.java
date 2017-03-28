import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;

/**
 * Created by Acerolla (Evgeniy Solovev) on 10.05.2016.
 */
public class DecryptPanel {

    static boolean hasKey;
    static boolean hasFile;

    public static void dysplay(Stage mainPanel) {

        hasKey = Boolean.FALSE;
        hasFile = Boolean.FALSE;

        mainPanel.hide();

        Stage decryptPanel = new Stage();
        decryptPanel.initModality(Modality.APPLICATION_MODAL);

        Pane pane = new Pane();
        decryptPanel.setScene(new Scene(pane, 300, 400));
        decryptPanel.setTitle("Decrypt IDEA");
        decryptPanel.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainPanel.show();
            }
        });
        decryptPanel.setResizable(Boolean.FALSE);

        IDEA idea = new IDEA();


        Label keyLabel              = new Label("Key:");
        TextArea keyArea            = new TextArea();
        Button loadKeyButton        = new Button("Load Key");
        TextArea filePathArea       = new TextArea();
        Button loadFileButton       = new Button("Load File");
        Button saveFileButton       = new Button("Decrypt and save to...");


        keyLabel.setLayoutX(20);
        keyLabel.setLayoutY(50);


        keyArea.setEditable(Boolean.FALSE);
        keyArea.setLayoutX(50);
        keyArea.setLayoutY(43);
        keyArea.setPrefSize(150, 20);


        loadKeyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Key");
                File file = fileChooser.showOpenDialog(decryptPanel);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String key = br.readLine();
                    keyArea.setText(key);

                    hasKey = Boolean.TRUE;
                    if (hasFile) {
                        saveFileButton.setDisable(Boolean.FALSE);
                    }

                    idea.setKey(key);

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Cannot read a key from file");
                    alert.show();
                }
            }
        });
        loadKeyButton.setLayoutX(90);
        loadKeyButton.setLayoutY(83);


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
                    File file = fileChooser.showOpenDialog(decryptPanel);
                    filePathArea.setText(file.getPath());
                    hasFile = Boolean.TRUE;
                    if (hasKey) {
                        saveFileButton.setDisable(Boolean.FALSE);
                    }
                } catch (Exception e) {
                    //
                    //
                    //”ƒ¿À»“‹   ’”ﬂÃ
                    //
                    //
                    System.out.println("Non load");
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
                fileChooser.setTitle("Path to decrypted file...");
                fileChooser.setInitialFileName(fileName);
                File file = fileChooser.showSaveDialog(decryptPanel);
                //
                //
                //«¿ƒ≈ –»œ“»“‹ ‘¿…À!!!
                //
                //
                Crypt crypt = new Crypt(filePathArea.getText(), file, Boolean.FALSE, idea);
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
                loadKeyButton,
                filePathArea,
                loadFileButton,
                saveFileButton
        );


        decryptPanel.show();
    }
}
