package com.backendcase.evaexchange.repository;

import com.backendcase.evaexchange.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
