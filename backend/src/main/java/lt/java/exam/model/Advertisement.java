package lt.java.exam.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table
@Entity(name = "Advertisement")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private double price;

    private String city;

    /*
    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<AdvertisementCategory> photoItemList = new HashSet<>();

    public void addPhotoItem(AdvertisementCategory photoItem) {
        if (null == photoItemList) {
            photoItemList = new HashSet<>();
        }

        photoItemList.add(photoItem);
    }*/
}
