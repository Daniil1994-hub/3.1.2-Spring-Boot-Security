package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Базовый метод без ролей (может вызвать LazyInitializationException)
    User findByUsername(String username);

    // Для аутентификации - загружаем роли
    @EntityGraph(attributePaths = {"roles"})
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsernameWithRoles(@Param("username") String username);

    // Для получения пользователя по ID с ролями
    @EntityGraph(attributePaths = {"roles"})
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdWithRoles(@Param("id") Long id);

    // Для получения всех пользователей с ролями
    @EntityGraph(attributePaths = {"roles"})
    @Query("SELECT u FROM User u")
    List<User> findAllWithRoles();

    // Альтернативный вариант с использованием NamedEntityGraph
    @EntityGraph(value = "User.roles")
    User findWithRolesByUsername(String username);
}