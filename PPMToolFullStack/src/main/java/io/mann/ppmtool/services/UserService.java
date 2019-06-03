package io.mann.ppmtool.services;

import io.mann.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Mann
 * @Date: 2019-06-03 21:50
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    
}
