package io.mann.ppmtool.repositories;

import io.mann.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mann
 * @Date: 2019-06-03 21:49
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
