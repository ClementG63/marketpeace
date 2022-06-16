package fr.ynov.marketpeace.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@Entity(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
