package org.ecards.service;

import java.util.List;
import java.util.Optional;

import org.ecards.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICardService {
	/*Query Functions*/
	Page<Card> getAllPageCards(int pageNum, int pageSize);
	Page<Card> getAllPageCardsPerNum(String num);
	Page<Card> getPageCardsPerUser(String userName, int pageNum, int pageSize);
	Page<Card> getPageCardsPerUserPerNum(String userName, String num);
    Float      getCountOfAllCardPages(int pageSize);
    Float      getCountOfCardPagesPerUser(String userName, int pageSize);
    Optional<Card>       getCardByID(long id);
    Optional<Card>       getCardByUserAndID(String username,long id);
    
    /*DML Operations*/
    Card       createCard(Card card);
    Card       updateCard(Card card);
    void       deleteCard(Card card);
    
}
