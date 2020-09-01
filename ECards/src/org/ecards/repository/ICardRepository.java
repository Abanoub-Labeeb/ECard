package org.ecards.repository;

import java.util.List;
import java.util.Optional;

import org.ecards.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends PagingAndSortingRepository<Card, Long> {
   Page<Card> findByUserName(String userName,Pageable pageable);
   Page<Card> findByUserNameAndCardNumberLike(String userName, String num,Pageable pageable);
   List<Card> findByUserNameAndCardNumberLike(String userName, String num);
   Page<Card> findByCardNumberLike(String num,Pageable pageable);
   List<Card> findByCardNumberLike(String num);
   Optional<Card> findByIdAndUserName(long id,String userName);
   Integer    countByUserName(String userName);
}
