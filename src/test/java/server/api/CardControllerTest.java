package server.api;

import commons.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commons.Card;
import commons.CList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;



public class CardControllerTest {
    private TestCardRepository cardRepository;
    private CardController cardController;
    Board board;
    CList list;

    @BeforeEach
    public void setup() {
        TestBoardRepository boardRepository = new TestBoardRepository();
        TestCListRepository cListRepository = new TestCListRepository();
        cardRepository = new TestCardRepository();

        CListController cListController = new CListController(cListRepository, boardRepository);
        cardController = new CardController(cardRepository, cListRepository);

        board = new Board();
        board.id = 1;
        boardRepository.boards.add(board);

        list = new CList("list");
        list.id = 1;
        cListRepository.lists.add(list);

        cListController.add(board.id, list.title);
    }

    @Test
    public void addInvalidId() {
        var actual = cardController.addCard(-1,"title");
        assertEquals(BAD_REQUEST, actual.getStatusCode());

    }

    @Test
    public void addNullTitle() {
        Card card = new Card(null);
        var actual = cardController.addCard(list.id, card.title);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addEmptyTitle(){
        Card card = new Card("");
        var actual = cardController.addCard(list.id, card.title);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addValid(){
        Card card = new Card("cardTitle");
        var actual = cardController.addCard(list.id, card.title);
        assertEquals("cardTitle", actual.getBody().title);
    }

    @Test
    public void addDatabaseIsUsed(){
        Card card = new Card("cardTitle");
        cardController.addCard(list.id, card.title);
        assertTrue(cardRepository.calledMethods.contains("save"));
    }

    @Test
    public void getCardInvalidId(){
        Card card1 = new Card("title");
        cardController.addCard(list.id, card1.title);
        var actual = cardController.getCard(card1.id + 1);
        assertEquals(BAD_REQUEST, actual.getStatusCode());

    }

    @Test
    public void getCardTest(){
        var card1 = cardController.addCard(list.id, "title1");

        long ID = card1.getBody().id;
        assertEquals(ID, cardController.getCard(ID).getBody().id);
    }

    @Test
    public void getAllCards(){

        List<Card> expected = new ArrayList<>();
        var card1 = expected.add(new Card("title1"));
        var card2 = expected.add(new Card( "title2"));
        var card3 = expected.add(new Card( "title3"));

        list.list.add(expected.get(0));
        list.list.add(expected.get(1));
        list.list.add(expected.get(2));

        assertEquals(expected, cardController.getCards(1).getBody());
    }
}

