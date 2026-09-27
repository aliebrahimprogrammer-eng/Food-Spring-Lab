package com.ga.food.repository;
import com.ga.food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //for registration
    boolean existsByEmailAddress(String emailAddress);
    //for login
    User findUserByEmailAddress(String emailAddress);

}
