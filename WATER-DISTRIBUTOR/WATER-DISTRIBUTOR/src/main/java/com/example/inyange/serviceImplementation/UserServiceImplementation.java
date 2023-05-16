package com.example.inyange.serviceImplementation;

import com.example.inyange.model.User;
import com.example.inyange.repository.UserRepository;
import com.example.inyange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository repo;
    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public User findUserById(int code) {
        return repo.findById(code).orElse(null);
    }

    @Override
    public List<User> displayUsers() {
        return repo.findAll();
    }

    @Override
    public User updateUser(User user) {
        User savedUser = repo.findById(user.getId()).orElse(null);
        if (savedUser!=null){
            User updatedUser = new User();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());

            return repo.save(updatedUser);
        }
        return repo.save(user);
    }

    @Override
    public void deleteUser(int code) {
        User savedUser = repo.findById(code).orElse(null);
        if (savedUser!=null){
            repo.delete(savedUser);
        }

    }

    @Override
    public User findUserUsername(User name)
    {
        return repo.findByUsername(name.getUsername().toString());
    }
}
