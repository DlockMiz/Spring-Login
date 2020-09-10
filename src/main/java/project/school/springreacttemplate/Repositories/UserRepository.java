package project.school.springreacttemplate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.school.springreacttemplate.Models.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}