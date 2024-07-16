package com.refugeesys.service;

import com.refugeesys.model.User;
import com.refugeesys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    // Method to authenticate a user by username and password
    public User login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }
    // Method to add a new user to the database
    public void addUser(User user) {
        repo.save(user);
    }
    // Method to update an existing user's details in the database
    public void updateUser(User user) {
        repo.save(user);
    }
    // Method to delete a user from the database by ID
    public void deleteUser(long id) {
        repo.deleteById(id);
    }
    // Method to retrieve all users or search for users by keyword
    public List<User> getAllUsers(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    // Method to retrieve a user by ID
    public User getUserById(long id) {
        return repo.findById(id).orElse(null);
    }
}
