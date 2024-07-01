package com.refugeesys.repository;

import com.refugeesys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username , String password);

    @Query(value = "SELECT * FROM login where phone like %?1%",
            nativeQuery=true)
    public List<User> search(@Param("keyword") String keyword);
}
