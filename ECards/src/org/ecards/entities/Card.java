package org.ecards.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.ecards.entities.validation.CardExpiration;


@Table(name  = "card")
@Entity(name = "Card")
public class Card {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "holder_name")
	@Size(min = 3 , max = 45)
	private String cardHolderName;
	
	@Column(name = "card_number")
	@Size(min = 14 , max = 14)
	private String cardNumber;
	
	@Column(name = "card_expiry_date")
	@Size(min = 5 , max = 5)
	@CardExpiration
	private String cardExpiryDate;
	
	
	public Card() {
		
	}
	
	public Card(String cardHolderName, String cardNumber, String cardExpiryDate) {
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cardExpiryDate = cardExpiryDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}
	
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardExpiryDate() {
		return cardExpiryDate;
	}
	
	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
		
}
