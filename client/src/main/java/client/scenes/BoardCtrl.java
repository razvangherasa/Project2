package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.CList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardCtrl implements Initializable {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private ObservableList<CList> data;

    @FXML
    private TableView<CList> table;


    @FXML
    private TableColumn<CList, String> title;

    /**
     * Constructor for BoardCtrl
     * A constructor for injecting the server utils and the new main controller
     * @param server The instance of server utils
     * @param mainCtrl the main controller
     */
    @Inject
    public BoardCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }


    /**
     * The initialize method of the BoardCtrl class
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    //TODO Implement initializable interface to store lists
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(q -> new SimpleStringProperty(q.getValue().title));
    }

    /**
     * Forwards to the page where a new list can be added
     */
    public void addList() {
        mainCtrl.showAddList();
    }

    /**
     * Forwards to the page where a list can be edited
     */
    public void editList() {
        mainCtrl.showAddList();
    }

    //TODO Implement refresh method

    /**
     * Refreshes overview, or at least is supposed to when it works
     */
    public void refresh() {
        var lists = server.getLists(1);
        data = FXCollections.observableList(lists);
        table.setItems(data);
    }

}
