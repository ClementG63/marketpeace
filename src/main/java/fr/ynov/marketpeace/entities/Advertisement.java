package fr.ynov.marketpeace.entities;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Advertisement DTO
 */
@Entity(name = "advertisements")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {
    /**
     * Advertisement's id, auto generated
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0)
    Long id;

    /**
     * Advertisement's title
     */
    @Column(name = "title", nullable = false)
    @Size(min = 3, max = 25)
    private String title;

    /**
     * Advertisement's price
     */
    @Column(name = "price", nullable = false)
    @Min(value = 0)
    private Float price;

    /**
     * Advertisement's description
     */
    @Size(min = 10, max = 25)
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Ad's image url, store in Firebase Storage
     */
    @Column(name = "image_url", nullable = false)
    private String imageUrl;


    /**
     * Owner's id
     */
    @Column(name = "owner_id", nullable = false)
    @Min(value = 0)
    private Long ownerId;

    /**
     * All args constructor
     */
    public Advertisement(String title, Float price, String description, String imageUrl, Long ownerId) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ownerId = ownerId;
    }
}
