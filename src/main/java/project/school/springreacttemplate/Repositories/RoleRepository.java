package project.school.springreacttemplate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.school.springreacttemplate.Models.AccountData.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
