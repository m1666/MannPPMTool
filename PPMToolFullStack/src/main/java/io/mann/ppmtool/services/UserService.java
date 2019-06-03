package io.mann.ppmtool.services;

import io.mann.ppmtool.domain.User;
import io.mann.ppmtool.exceptions.UsernameAlreadyExistsException;
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
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //  username has to be unique(exception)
            newUser.setUsername(newUser.getUsername());

            // Make sure that password and confirmPassword match

            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exits");
        }

    }


}
