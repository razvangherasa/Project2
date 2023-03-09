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
package client.utils;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import commons.Board;
import commons.CList;
import commons.Card;
import javafx.collections.ObservableList;
import org.glassfish.jersey.client.ClientConfig;

import commons.Quote;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;

public class ServerUtils {

    private static final String SERVER = "http://localhost:8080/";

    /**
     * Method for demonstrating the use of sockets to get the quotes from the server
     * @throws IOException
     */
    public void getQuotesTheHardWay() throws IOException {
        var url = new URL("http://localhost:8080/api/quotes");
        var is = url.openConnection().getInputStream();
        var br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * Method for getting all the quotes from the server
     * @return the quotes
     */
    public List<Quote> getQuotes() {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/quotes") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<List<Quote>>() {});
    }

    /**
     * Method for adding a quote to the database
     * @param quote the quote object to be added
     * @return the added quote object
     */
    public Quote addQuote(Quote quote) {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/quotes") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(quote, APPLICATION_JSON), Quote.class);
    }

    /**
     * Method for getting all the CLists from the database
     * @return the CLists
     */
    public ObservableList<CList> getLists(long boardId) {
        return (ObservableList<CList>) ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/lists/get/" + boardId)
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<List<CList>>(){});
    }

    /**
     * Adds a list
     * @param cList list
     */
    public void addList(CList cList) {
        ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/lists/add/1/" + cList.title )
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .post(Entity.entity(cList, APPLICATION_JSON), CList.class);
    }

    /**
     * Adds a card
     * @param card
     */
    public void addCard(Card card) {
        ClientBuilder.newClient(new ClientConfig())
            .target(SERVER).path("api/cards/add/1/" + card.title )
            .request(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .post(Entity.entity(card, APPLICATION_JSON), Card.class);
    }

    /**
     * Method for getting all the Cards from the database
     * @return the Card
     */
    public ObservableList<Card> getCards(long listId) {
        return (ObservableList<Card>) ClientBuilder.newClient(new ClientConfig())
            .target(SERVER).path("api/cards/getCards/" + listId)
            .request(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .get(new GenericType<List<Card>>(){});
    }

    /**
     * Automatically creates an empty board to add lists to
     */
    public void createBoard() {
        ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/boards/add/")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .post(Entity.entity(new Board(), APPLICATION_JSON), Board.class);
    }


}