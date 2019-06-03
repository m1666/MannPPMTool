package io.mann.ppmtool.services;

import io.mann.ppmtool.domain.User;
import io.mann.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: Mann
 * @Date: 2019-06-03 21:50
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        // Todo: username has to be unique(exception)

        // Todo: Make sure that password and confirmPassword match

        // Todo: We don't persist or show the confirmPassword
        return userRepository.save(newUser);
    }


}
