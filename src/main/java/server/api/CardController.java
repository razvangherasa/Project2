package server.api;

import commons.Card;
import commons.CList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.CListRepository;
import server.database.CardRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CListRepository cListRepository;
    private final CardRepository cardRepository;

    /**
     * Constructor for the CardControllerClass
     * @param cListRepository the repository (database interface for the Clist class)
     * @param cardRepository  the repository (database interface for the Card class)
     */
    public CardController(CardRepository cardRepository, CListRepository cListRepository) {
        this.cListRepository = cListRepository;
        this.cardRepository = cardRepository;

    }

    /**
     * Endpoint for returning a card.
     * @param cardId ID of the card to be retrieved
     * @return the retrieved card
     */
    @GetMapping("/getCard/{cardId}")
    public ResponseEntity<Card> getCard(@PathVariable("cardId") long cardId) {
        if (!cardRepository.existsById(cardId)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(cardRepository.findById(cardId).get());
    }

    /**
     * Endpoint for returning all cards from a given list
     * @param listId The id of the lists
     * @return All the cards from the specified list
     */
    @GetMapping("/get/{listID}")
    public ResponseEntity<List<Card>> getCards(@PathVariable("listId") long listId) {
        if(!cListRepository.existsById(listId)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(cListRepository.findById(listId).get().list);
    }

    /**
     * Endpoint for adding a card.
     * @param listId ID of the list the card is added to
     * @param title the title of the card to be added
     * @return the added card
     */
    @PostMapping("/addCard/{listId}/{title}")
    public ResponseEntity<Card> addCard(@PathVariable("listId") long listId, @PathVariable("title") String title) {

        if (!cListRepository.existsById(listId)) {
            return ResponseEntity.badRequest().build();
        }

        if(title == null || title.length() == 0){
            return ResponseEntity.badRequest().build();
        }

        CList parentList = cListRepository.findById(listId).get();

        Card card = new Card(title);

        parentList.list.add(card);

        cardRepository.save(card);

        cListRepository.save(parentList);

        return ResponseEntity.ok(card);
    }
}

