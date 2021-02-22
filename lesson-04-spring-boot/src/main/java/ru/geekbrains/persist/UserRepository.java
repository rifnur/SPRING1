package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserByUsernameLike(String username);

    @Query("select u from User u where u.username like :username")
    List<User> someQuery(@Param("username") String username);

}
