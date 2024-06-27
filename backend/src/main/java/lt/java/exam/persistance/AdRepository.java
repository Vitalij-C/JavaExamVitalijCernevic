package lt.java.exam.persistance;


import lt.java.exam.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdRepository extends JpaRepository<Ad, UUID> {
    Ad findOneById(UUID id);
}