//package com.refugeesys.repository;
//
//import com.refugeesys.model.Refugee;
//import com.refugeesys.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//@Repository
//public interface RefugeeRepository extends JpaRepository<Refugee, Long> {
//    @Query(value = "SELECT * FROM refugee_tb where phone like %?1%",
//            nativeQuery=true)
//    public List<Refugee> search(@Param("mykeyword") String mykeyword);
//}

package com.refugeesys.repository;

        import com.refugeesys.model.Refugee;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface RefugeeRepository extends JpaRepository<Refugee, Long> {

    @Query(value = "SELECT * FROM refugee_tb WHERE phone LIKE %:mykeyword%",
            nativeQuery = true)
    List<Refugee> search(@Param("mykeyword") String mykeyword);
}
