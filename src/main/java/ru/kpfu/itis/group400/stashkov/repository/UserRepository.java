package ru.kpfu.itis.group400.stashkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.group400.stashkov.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.username = :username")
    Optional<User> getByUsername(String username);

    //1 означает что нужно взять первый аргумент из метода
    @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)
    Optional<User> getByUsernameNative(String username);

    @Override
    void delete(User entity);

    @Modifying
    @Transactional
    @Query(value = "insert into users (username) values (:#{user.username})", nativeQuery = true)
    void create(User user);

    @Modifying
    @Transactional
    @Query(value = "update users u set u.username = :#{user.username} where u.id = :#{user.id}",
            nativeQuery = true)
    void update(User user);

}
