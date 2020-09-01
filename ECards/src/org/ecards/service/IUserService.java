package org.ecards.service;

import org.ecards.entities.Authority;
import org.ecards.entities.User;

public interface IUserService {
	
	void saveUser(User user);
	void saveAuthority(Authority authority);
	User findByUsername(String username);

}
