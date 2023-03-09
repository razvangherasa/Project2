package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Stage;
import commons.CList;
import commons.Card;
import jakarta.ws.rs.core.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;


import java.util.List;

import static javafx.application.Application.launch;

public class DisplayLists extends Application {

   /* public void start(Stage primaryStage) {

        final ServerUtils server = new ServerUtils();
        List<CList> allLists = server.getLists(1); // retrieve all the lists from the database

        // create VBox layout and add TitledPanes for each list
        VBox vbox = new VBox();
        for (CList list : allLists) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(list.title);

            // create FlowPane and add tasks for this list
            FlowPane flowPane = new FlowPane();
            for (Card card : server.getCards(list.id)) {
                Label taskLabel = new Label(card.title);
                flowPane.getChildren().add(taskLabel);
            }

            titledPane.setContent(flowPane);
            vbox.getChildren().add(titledPane);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);

        Scene scene = new Scene(scrollPane, 600, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }

    */
}
