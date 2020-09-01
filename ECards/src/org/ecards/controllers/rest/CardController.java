package org.ecards.controllers.rest;

import java.util.Optional;

import org.ecards.entities.Card;
import org.ecards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CardController.BASE_URL)
public class CardController {
    
	public static final String BASE_URL = "/api/card";
    
	@Autowired
	private CardService cardService; 
	
    @GetMapping("/{id}")
    public Optional<Card> get(@PathVariable Long id){
        return 	cardService.getCardByID(id);
    }
}
