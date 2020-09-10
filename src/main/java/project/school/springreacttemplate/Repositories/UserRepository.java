package project.school.springreacttemplate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.school.springreacttemplate.Models.AccountData.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}