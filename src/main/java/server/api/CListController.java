package server.api;

import commons.Board;
import commons.CList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.BoardRepository;
import server.database.CListRepository;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class CListController {
    private final CListRepository listRepo;
    private final BoardRepository boardRepo;

    /**
     * Constructor for the CListController class
     * @param listRepo the repository (database interface for the CList class)
     * @param boardRepo the repository (database interface for the Board class)
     */
    public CListController(CListRepository listRepo, BoardRepository boardRepo) {
        this.listRepo = listRepo;
        this.boardRepo = boardRepo;
    }

    /**
     * Endpoint for adding a new list to a board.
     * @param boardId ID of the board the CList is being added to
     * @param title title of the list being added
     *
     * @return the list added
     */
    @PostMapping(path = { "/add/{boardId}/{title}",})
    public ResponseEntity<CList> add(@PathVariable("boardId") long boardId, @PathVariable("title") String title) {
        if (boardId < 0 || !boardRepo.existsById(boardId)) {
            return ResponseEntity.badRequest().build();
        }
        Board parentBoard = boardRepo.findById(boardId).get();
        if(title == null || title.length() == 0){
            return ResponseEntity.badRequest().build();
        }

        CList list = new CList(title);

        parentBoard.list.add(list);

        listRepo.save(list);

        boardRepo.save(parentBoard);

        return ResponseEntity.ok(list);
    }

    /**
     * Endpoint for retrieving all CLists belonging to a certain board, specified by ID
     * @param id ID of the board whose lists are being retrieved
     * @return the list of CLists
     */
    @GetMapping("/get/{boardId}")
    public ResponseEntity<List<CList>> getLists(@PathVariable("boardId") long id) {
        if (id < 0 || !boardRepo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(boardRepo.findById(id).get().list);
    }
}
