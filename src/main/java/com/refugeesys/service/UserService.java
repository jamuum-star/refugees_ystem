//package com.refugeesys.service;
//
//import com.refugeesys.model.User;
//import com.refugeesys.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository repo;
//
//    public User Login(String username,String password){
//        User user = repo.findByUsernameAndPassword(username,password);
//        return user;
//    }
//
//    public void addUser(User user) {
//        repo.save(user);
//    }
//
//    // Update user
//    public void updateUser(User user) {
//        repo.save(user);
//    }
//
//    // Delete user
//    public void deleteUser(long id) {
//        repo.deleteById(id);
//    }
//
//    // Get all users
//    public List<User> getAllUsers(String keyword) {
//        if (keyword != null) {
//            return repo.search(keyword);
//        }
//        return repo.findAll();
//    }
//
//    // Get user by ID
//    public User getUserById(long id) {
//        return repo.findById(id).orElse(null);
//    }
//
//
//}

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

    public User login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }

    public void addUser(User user) {
        repo.save(user);
    }

    public void updateUser(User user) {
        repo.save(user);
    }

    public void deleteUser(long id) {
        repo.deleteById(id);
    }

    public List<User> getAllUsers(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public User getUserById(long id) {
        return repo.findById(id).orElse(null);
    }
}
