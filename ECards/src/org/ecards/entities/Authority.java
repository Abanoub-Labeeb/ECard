package org.ecards.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ecards.entities.FKs.AuthorityID;


@Entity(name = "Authorities")
@Table(name = "authorities")
public class Authority {
    

    /*PKs*/ 
	@EmbeddedId
	private  AuthorityID authorityID;
	
	/*
	 * FKs
	*/
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", insertable = false, updatable = false)
	private User user;
    
	public Authority() {
	
	}
	
	public Authority(AuthorityID authorityID) {
		this.authorityID = authorityID;
	}
	
	public AuthorityID getAuthorityID() {
		return authorityID;
	}

	public void setAuthorityID(AuthorityID authorityID) {
		this.authorityID = authorityID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
}

