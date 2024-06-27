package lt.java.exam.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table
@Entity(name = "ads")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private double price;

    private String city;

    private UUID author;
}
