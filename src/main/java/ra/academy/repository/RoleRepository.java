package ra.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.academy.model.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
