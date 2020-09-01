package org.ecards.entities.FKs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*Authority Composite Key*/
@Embeddable
public class AuthorityID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4562863376387636956L;

	@Column(name = "username")
	public String username;
	
    @Column(name = "authority")
	public String authority;

	public AuthorityID() {

	}

	public AuthorityID(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}