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
    String title;

    @Column(name = "price", nullable = false)
    @Min(value = 0)
    Float price;

    @Size(min = 10, max = 25)
    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "image_url", nullable = false)
    String imageUrl;

    @Column(name = "owner_id", nullable = false)
    @Min(value = 0)
    Long ownerId;
}
