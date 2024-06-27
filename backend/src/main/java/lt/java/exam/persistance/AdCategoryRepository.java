package lt.java.exam.persistance;


import lt.java.exam.model.Ad;
import lt.java.exam.model.AdCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdCategoryRepository extends JpaRepository<AdCategory, UUID> {
    AdCategory findOneById(UUID id);
}