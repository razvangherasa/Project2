package server.api;

import commons.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

public class BoardControllerTest {

    private TestBoardRepository repo;
    private BoardController sut;

    @BeforeEach
    public void setup() {
        repo = new TestBoardRepository();
        sut = new BoardController(repo);
    }

    @Test
    public void addBoardStatusCode(){
        var actual = sut.add(new Board());
        assertEquals(OK, actual.getStatusCode());
    }

    @Test
    public void addBoardResult(){
        var actual = sut.add(new Board());
        assertNotNull(actual.getBody());
    }

    @Test
    public void databaseIsUsed(){
        sut.add(new Board());
        assertTrue(repo.calledMethods.contains("save"));
    }

    @Test
    public void getNonExistentBoard(){
        var actual = sut.getById(1);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void invalidId(){
        var actual = sut.getById(-1);
        assertEquals(BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void getExistentBoard(){
        Board board = new Board();
        board.id = 1;

        repo.boards.add(board);

        assertEquals(board, sut.getById(1).getBody());
    }
}
