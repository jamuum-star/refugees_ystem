package com.refugeesys.repository;

import com.refugeesys.model.Refugee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RefugeeRepository extends JpaRepository<Refugee, Long> {
    /**
     * Custom query to search for refugees by phone number.
     * The query looks for phone numbers that contain the provided keyword.
     *
     * @param mykeyword the keyword to search for in phone numbers
     * @return a list of refugees whose phone numbers match the keyword
     */
    @Query(value = "SELECT * FROM refugee_tb WHERE phone LIKE %:mykeyword%",
            nativeQuery = true)
    List<Refugee> search(@Param("mykeyword") String mykeyword);
}
