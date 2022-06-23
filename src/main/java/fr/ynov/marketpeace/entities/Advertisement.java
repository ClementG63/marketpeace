package fr.ynov.marketpeace.entities;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity(name = "advertisements")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0)
    Long id;

    @Column(name = "title", nullable = false)
    @Size(min = 3, max = 25)
    private String title;

    @Column(name = "price", nullable = false)
    @Min(value = 0)
    private Float price;

    @Size(min = 10, max = 25)
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "owner_id", nullable = false)
    @Min(value = 0)
    private Long ownerId;

    public Advertisement(String title, Float price, String description, String imageUrl, Long ownerId) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ownerId = ownerId;
    }
}
