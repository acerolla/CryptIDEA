import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * Created by Acerolla (Evgeniy Solovev) on 10.05.2016.
 */
public class PanelIDEA extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 100, 180);
        primaryStage.setScene(scene);

        primaryStage.setResizable(Boolean.FALSE);

        Text text = new Text("Hello (:");
        text.setX(45);
        text.setY(40);

        Button  encButton = new Button("Encode"),
                decButton = new Button("Decode");

        encButton.setLayoutX(37);
        encButton.setLayoutY(85);
        encButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //
                //
                // ����� �� �����������
                //
                //
                EncryptPanel.dysplay(primaryStage);

            }
        });

        decButton.setLayoutX(37);
        decButton.setLayoutY(135);
        decButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //
                //
                //����� �� �������������
                //
                //
                DecryptPanel.dysplay(primaryStage);
            }
        });

        pane.getChildren().addAll(text, encButton, decButton);




        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
