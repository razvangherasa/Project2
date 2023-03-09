package server.api;

import commons.Board;
import commons.CList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class CListControllerTest {
    private TestBoardRepository boardRepo;
    private TestCListRepository listRepo;
    private CListController sut;

    @BeforeEach
    public void setup() {
        boardRepo = new TestBoardRepository();
        listRepo = new TestCListRepository();
        sut = new CListController(listRepo, boardRepo);
    }

    @Test
    public void addInvalidId(){
        var actual = sut.add(-1, "title");
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addNonExistentId(){
        var actual = sut.add(1, "title");
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addNullTitle(){
        Board board = new Board();
        board.id = 1;
        boardRepo.boards.add(board);
        var actual = sut.add(1, null);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addEmptyTitle(){
        Board board = new Board();
        board.id = 1;
        boardRepo.boards.add(board);
        var actual = sut.add(1, "");
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void addValid(){
        Board board = new Board();
        board.id = 1;
        boardRepo.boards.add(board);
        var actual = sut.add(1, "title1");
        assertEquals("title1", actual.getBody().title);
    }

    @Test
    public void addDatabaseIsUsed(){
        Board board = new Board();
        board.id = 1;
        boardRepo.boards.add(board);
        var actual = sut.add(1, "title1");
        assertTrue(listRepo.calledMethods.contains("save"));
    }

    @Test
    public void getListsInvalidId(){
        var actual = sut.getLists(-1);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void getListsNonExistentId(){
        var actual = sut.getLists(2);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void getListsTest(){
        Board board = new Board();
        board.id = 1;

        List<CList> expected = new ArrayList<>();

        expected.add(new CList("Title1"));
        expected.add(new CList("Title2"));

        board.list.add(expected.get(0));
        board.list.add(expected.get(1));

        boardRepo.boards.add(board);

        assertEquals(expected, sut.getLists(1).getBody());
    }
}
