package com.bouaziz.saraha.repositories;

import com.bouaziz.saraha.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
//exemple qu'on n'utilise pas dans notre app
    //exemple1
    List<User> findAllByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrderByFirstName(String firstname,String lastname);
//exemple 2(requete jpql)
    @Query("select u from User u where u.firstName=:firstname and u.lastName=:lastname")
List<User>findCustom(@Param("firstname") String x, @Param("lastname")String y);
//exemple 3(Requete native)
    @Query(value="select * from _user where firstName =:firstName and lastName =:lastName",nativeQuery = true)
    List<User> findCustomComplicated(@Param("firstName") String firstname,@Param("lastName") String lastName);
//Les méthodes qu'on a besoin pour notre application
    Optional<User> findByEmail(String email);

}
