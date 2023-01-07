package com.backendcase.evaexchange.service;

import com.backendcase.evaexchange.domain.User;
import com.backendcase.evaexchange.exception.BadRequestException;
import com.backendcase.evaexchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addAllUsers(List<User> users){
        userRepository.saveAll(users);

    }

    public User findUserById(Long userId){
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isEmpty()){
            throw new BadRequestException("There is no user with id "+userId);
        }
        return byId.get();
    }

}
