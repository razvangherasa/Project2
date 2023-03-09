package client.scenes;

import client.utils.ServerUtils;
import commons.Card;
import jakarta.ws.rs.WebApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javax.inject.Inject;

public class AddCardCtrl {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    private TextField title;

    /**
     * Constructor for AddCardCtrl class.
     * @param server Server
     * @param mainCtrl main scene controller
     */
    @Inject
    public AddCardCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    /**
     * Closes the add card scene,
     * clears all fields, and returns to overview.
     */
    public void cancel() {
        title.clear();
        mainCtrl.showBoard();
    }

    /**
     * Creates new card entry when "Create" button
     * is clicked.
     */
    public void create() {
        String titleText = title.getText();

        // TODO add card to database correctly, each list should have a button which can add the cards.
        //  The card should be added to the correct list in the database.

        try {
            server.addCard(getCard());
        } catch (WebApplicationException e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

        title.clear();
        mainCtrl.showBoard();
    }

    /**
     * Retrieves the title from the form and returns a Card
     * @return a Card with a title
     */
    private Card getCard() {
        var titleText = title.getText();
        return new Card(titleText);
    }

    /**
     * Registers methods to happen on events such as form submission on enter and cancellation on escape
     * @param event the event which has been triggered
     */
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                create();
                break;
            case ESCAPE:
                cancel();
                break;
            default:
                break;
        }
    }

}
