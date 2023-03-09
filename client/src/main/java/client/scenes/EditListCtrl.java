package client.scenes;

import client.utils.ServerUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class EditListCtrl implements Initializable {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    private TextField title;

    private String prevTitle;

    /**
     * Constructor for EditListCtrl class.
     * @param server Server
     * @param mainCtrl main scene controller
     */
    @Inject
    public EditListCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     *  TODO when the "edit" button is done in the board overview, have the button down event also send the previous
     *       title to this controller so the text field can display it.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if not known.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prevTitle = "Previous Title";   // Temporary placeholder.
        title.setText(prevTitle);
    }

    /**
     * Replaces the list's title with new title,
     * clears all fields and returns to board.
     */
    public void update() {
        String titleText = title.getText();

        // TODO add title updates to server.

        cancel();
    }

    /**
     * Discards changes and returns to board overview.
     */
    public void cancel() {
        title.clear();
        mainCtrl.showBoard();
    }

    /**
     * Detects key presses used for keyboard shortcuts.
     * @param event Key press
     */
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                update();
                break;
            case ESCAPE:
                cancel();
                break;
            default:
                break;
        }
    }

}
