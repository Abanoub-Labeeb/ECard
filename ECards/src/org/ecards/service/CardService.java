package org.ecards.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ecards.entities.Card;
import org.ecards.repository.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardService implements ICardService{

	
	@Autowired
	private ICardRepository cardRepository;

    /*
	@Override
	public Page<Card> getAllPageCards(Pageable pageable) {
		// TODO Auto-generated method stub
		return cardRepository.findAll(pageable);
	}
    */
	
	@Override
	public Page<Card> getAllPageCards(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable =  PageRequest.of(pageNum, pageSize);
		return cardRepository.findAll(pageable);
		
	}
	
	/*
	@Override
	public Page<Card> getPageCardsPerUser(String userName, Pageable pageable) {
		// TODO Auto-generated method stub
		return cardRepository.findAll(pageable);
	}
	*/
	
	@Override
	public Page<Card> getPageCardsPerUser(String userName, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable =  PageRequest.of(pageNum, pageSize);
		return cardRepository.findByUserName(userName, pageable);
	}
    
	@Override
	public Float getCountOfCardPagesPerUser(String userName, int pageSize) {
		// TODO Auto-generated method stub
	
		float   count       = cardRepository.countByUserName(userName);
		float   pages       = 0 ;  
		float   pagesRes    = count/pageSize; 
		
		if (count != 0) {
			
		    pages = (int) Math.ceil(pagesRes); 	
		}
		
		return pages;
	}

	@Override
	public Float getCountOfAllCardPages(int pageSize) {
		// TODO Auto-generated method stub
		float   count       = cardRepository.count();
		float   pages       = 0 ;  
		float   pagesRes    = count/pageSize; 
		
		if (count != 0) {
			
		    pages = (int) Math.ceil(pagesRes); 	
		}
		
		return pages;
	}

	@Transactional
	@Override
	public Card createCard(Card card) {
		// TODO Auto-generated method stub
		if(card.getId() == null && card.getUserName() != null && !card.getUserName().isBlank())
			return cardRepository.save(card);
		else
		    return null;
	}

	@Transactional
	@Override
	public Card updateCard(Card card) {
		// TODO Auto-generated method stub
		if(card.getId() == null || card.getUserName() == null || card.getUserName().isBlank())
			return null;
		else
		    return cardRepository.save(card);
	}
	
	@Transactional
	@Override
	public void deleteCard(Card card) {
		// TODO Auto-generated method stub
		cardRepository.delete(card);
	}

	@Override
	public Optional<Card> getCardByID(long id) {
		// TODO Auto-generated method stub
		return cardRepository.findById(id);
	}

	@Override
	public Optional<Card> getCardByUserAndID(String userName, long id) {
		// TODO Auto-generated method stub
		return cardRepository.findByIdAndUserName(id, userName);
	}

	@Override
	public Page<Card> getAllPageCardsPerNum(String num) {
		// TODO Auto-generated method stub
		return cardRepository.findByCardNumberLike("%"+num+"%",null);
	}

	@Override
	public Page<Card> getPageCardsPerUserPerNum(String userName, String num) {
		// TODO Auto-generated method stub
		Page<Card> l1 = cardRepository.findByUserNameAndCardNumberLike(userName, "%"+num+"%", null);
		List<Card> l2 = cardRepository.findByUserNameAndCardNumberLike(userName, "%"+num+"%");
		return cardRepository.findByUserNameAndCardNumberLike(userName, "%"+num+"%", null);
	}


	    
}
