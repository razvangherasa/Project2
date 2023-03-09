/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainCtrl {

    private Stage primaryStage;

    private QuoteOverviewCtrl overviewCtrl;
    private Scene overview;

    private BoardCtrl boardCtrl;
    private Scene board;

    private AddQuoteCtrl addCtrl;
    private Scene add;

    private AddListCtrl addListCtrl;
    private Scene addList;

    private EditListCtrl editListCtrl;
    private Scene editList;

    private ServerUtils server;


    /**
     * Constructor of the MainCtrl class
     * @param server interface for sending HTTP requests to the server
     */
    @Inject
    public MainCtrl(ServerUtils server) {
        this.server = server;
    }


    /**
     * Initialises the stage and sets the scene
     * @param primaryStage primary stage (main window)
     * @param overview scene for the main scene
     * @param add scene for adding quotes
     * @param addList scene for adding lists
     * @param board scene for displaying a board
     */
    public void initialize(Stage primaryStage, Pair<QuoteOverviewCtrl, Parent> overview,
            Pair<AddQuoteCtrl, Parent> add, Pair<AddListCtrl, Parent> addList,Pair<EditListCtrl, Parent> editList
            , Pair<BoardCtrl, Parent> board) {

        this.primaryStage = primaryStage;
        this.overviewCtrl = overview.getKey();
        this.overview = new Scene(overview.getValue());

        this.board = new Scene(board.getValue());
        this.boardCtrl = board.getKey();
        server.createBoard();


        this.addCtrl = add.getKey();
        this.add = new Scene(add.getValue());

        this.addListCtrl = addList.getKey();
        this.addList = new Scene(addList.getValue());

        this.editListCtrl = editList.getKey();
        this.editList = new Scene(editList.getValue());

        showBoard();
        primaryStage.show();
    }

    /**
     * Method for displaying the overview of quotes
     */
    public void showOverview() {
        primaryStage.setTitle("Quotes: Overview");
        primaryStage.setScene(overview);
        overviewCtrl.refresh();
    }

    /**
     * Sets the title to "Board" and shows the board overview
     * Devoid of refresh capabilities, will be added later
     */
    public void showBoard() {
        primaryStage.setTitle("Board");
        primaryStage.setScene(board);
    }

    /**
     * Displays the scene for adding a new quote
     */
    public void showAdd() {
        primaryStage.setTitle("Quotes: Adding Quote");
        primaryStage.setScene(add);
        add.setOnKeyPressed(e -> addCtrl.keyPressed(e));
    }

    /**
     * Sets the title for the scene of specifying a title to add a CList
     * Input handling via Javafx to be implemented later
     */
    public void showAddList() {
        primaryStage.setTitle("Lists: Add List");
        primaryStage.setScene(addList);
        addList.setOnKeyPressed(e -> addListCtrl.keyPressed(e));
    }

    /**
     * Sets the title for the scene of editing a list
     * Input handling via Javafx to be implemented later
     */
    public void showEditList() {
        primaryStage.setTitle("Lists: Edit List");
        primaryStage.setScene(editList);
        editList.setOnKeyPressed(e -> editListCtrl.keyPressed(e));
    }
}