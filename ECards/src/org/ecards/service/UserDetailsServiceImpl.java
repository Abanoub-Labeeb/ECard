package org.ecards.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.ecards.entities.Authority;
import org.ecards.entities.User;
import org.ecards.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Authority authority : user.getUserAuthorities()){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthorityID().getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

	}

}
