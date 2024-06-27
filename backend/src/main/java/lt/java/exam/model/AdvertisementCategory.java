package lt.java.exam.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table
@Entity(name = "advertisement-category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AdvertisementCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
}
