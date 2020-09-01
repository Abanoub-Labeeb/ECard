package org.ecards.repository;


import java.util.List;

import org.ecards.entities.Authority;
import org.ecards.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IAuthorityRepository extends CrudRepository<Authority, String>{
	  List<Authority> findByUser(User user);
}
