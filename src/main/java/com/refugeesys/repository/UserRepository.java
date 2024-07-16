package com.refugeesys.repository;

import com.refugeesys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Method to find a user by their username and password.
     * Used for authentication purposes.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the user matching the username and password, or null if no match is found
     */
    User findByUsernameAndPassword(String username , String password);

    /**
     * Custom query to search for users by phone number.
     * The query looks for phone numbers that contain the provided keyword.
     *
     * @param keyword the keyword to search for in phone numbers
     * @return a list of users whose phone numbers match the keyword
     */
    @Query(value = "SELECT * FROM login where phone like %?1%",
            nativeQuery=true)
    public List<User> search(@Param("keyword") String keyword);
}
