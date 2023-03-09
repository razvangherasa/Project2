package client.scenes;

import client.utils.ServerUtils;
import commons.CList;
import jakarta.ws.rs.WebApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

import javax.inject.Inject;

public class AddListCtrl {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    private TextField title;

    @FXML
    private ColorPicker colourPicker;

    /**
     * Constructor for AddListCtrl class.
     * @param server Server
     * @param mainCtrl main scene controller
     */
    @Inject
    public AddListCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    /**
     * Closes the add list scene,
     * clears all fields, and returns to overview.
     */
    public void cancel() {
        title.clear();
        mainCtrl.showBoard();
    }

    /**
     * Creates new list entry when "Create" button
     * is clicked.
     */
    public void create() {
        String titleText = title.getText();
        Color colour = colourPicker.getValue();

        try {
            server.addList(getList());
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
     * Retrieves the title from the form and returns a CList
     * @return a CList with a title
     */
    private CList getList() {
        var titleText = title.getText();
        return new CList(titleText);
    }

    /**
     * Clears all fields, in this case just the listTitle
     * If we want to pair it to a button later
     */
    public void clearFields() {
        title.clear();
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
