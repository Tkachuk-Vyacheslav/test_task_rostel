package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void saveUser(User user) {
         userRepo.save(user);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public void edit(User user) {userRepo.save(user);}

    public User getById(Long id) {
        return  userRepo.getReferenceById(id);
    }

}
