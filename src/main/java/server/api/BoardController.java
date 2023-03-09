package server.api;

import commons.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.BoardRepository;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardRepository repo;

    /**
     * Constructor for the BoardController class
     * @param repo the repository (database interface for the Board class)
     */
    public BoardController(BoardRepository repo) {
        this.repo = repo;
    }

    /**
     * Endpoint for getting a specific board by its unique ID
     * @param id the ID of the board being sought
     * @return the board if found, bad request otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Board> getById(@PathVariable("id") long id) {
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.findById(id).get());
    }

    /**
     * Endpoint for adding a new board.
     * Currently, a Board just holds a list of CLists. This will be changed
     * when implementing the advanced features
     * @param board the board to be added to the database
     * @return the board added
     */
    @PostMapping(path = { "/add",})
    public ResponseEntity<Board> add(@RequestBody Board board) {
        if(board == null){
            return ResponseEntity.badRequest().build();
        }

        Board saved = repo.save(board);
        return ResponseEntity.ok(saved);
    }
}
