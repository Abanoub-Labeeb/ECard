package org.ecards.repository;

import org.ecards.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
	User findByUsername(String username);
}
