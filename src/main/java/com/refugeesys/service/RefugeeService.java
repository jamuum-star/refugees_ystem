package com.refugeesys.service;

import com.refugeesys.model.Refugee;
import com.refugeesys.repository.RefugeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefugeeService {
    @Autowired
    private RefugeeRepository repos;

    public void addRefugee(Refugee refugee) {
        repos.save(refugee);
    }

    // Update user
    public void updateRefugee(Refugee refugee) {
        repos.save(refugee);
    }

    // Delete user
    public void deleteRefugee(long id) {
        repos.deleteById(id);
    }

    // Get all users
    public List<Refugee> getAllRefugees(String keyword) {
        if (keyword != null) {
            return repos.search(keyword);
        }
        return repos.findAll();
    }

    // Get user by ID
    public Refugee getRefugeeById(long id) {
        return repos.findById(id).orElse(null);
    }

}