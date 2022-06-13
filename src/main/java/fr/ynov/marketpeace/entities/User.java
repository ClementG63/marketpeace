package fr.ynov.marketpeace.entities;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity(name = "users")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0)
    Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 25)
    String name;

    @Column(name = "surname", nullable = false)
    @Size(min = 2, max = 25)
    String surname;

    @Column(name = "mail_address", nullable = false)
    @Size(min = 3, max = 25)
    @Email
    String mailAddress;
}
