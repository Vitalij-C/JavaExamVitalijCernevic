package lt.java.exam.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table
@Entity(name = "ad_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AdCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
}
