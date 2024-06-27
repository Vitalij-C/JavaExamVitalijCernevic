package lt.java.exam.persistance;


import lt.java.exam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    User findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}