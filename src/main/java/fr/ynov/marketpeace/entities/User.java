package fr.ynov.marketpeace.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0)
    Long id;

    @Column(name = "username", nullable = false)
    @Size(min = 2, max = 25)
    private String username;
    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 120)
    private String password;

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 25)
    private String name;

    @Column(name = "surname", nullable = false)
    @Size(min = 2, max = 25)
    private String surname;

    @Column(name = "mail_address", nullable = false)
    @Size(min = 3, max = 25)
    @Email
    private String mailAddress;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String name, String surname, String mailAddress) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mailAddress = mailAddress;
    }
}
