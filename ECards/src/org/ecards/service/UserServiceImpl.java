package org.ecards.service;

import org.ecards.entities.Authority;
import org.ecards.entities.User;
import org.ecards.repository.IAuthorityRepository;
import org.ecards.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
    private IUserRepository userRepository;
    
	@Autowired
    private IAuthorityRepository authorityRepository;
    
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
	@Override
	public void saveAuthority(Authority authority) {
		// TODO Auto-generated method stub
		authorityRepository.save(authority);
	}

}
