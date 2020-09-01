package org.ecards.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity(name = "users")
@Table(name = "Users")
public class User implements Serializable{
 	
	@Id
    @Column(name = "username")
	private String username;
    
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;
	
	
	/*
	 * FKs
	*/
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authority> userAuthorities;
    
	
	/**
	 * 
	*/
	private static final long serialVersionUID = -1503605011765516280L;

	
    public User() {
		super();
	}

	public User(String username, String password,Boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled  = enabled;
	}
	
	public User(String username, String password, Boolean enabled, Set<Authority> userAuthority) {
		super();
		this.username = username;
		this.password = password;
		this.enabled  = enabled;
		this.userAuthorities = userAuthority;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	public Set<Authority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(Set<Authority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}
    
    
}