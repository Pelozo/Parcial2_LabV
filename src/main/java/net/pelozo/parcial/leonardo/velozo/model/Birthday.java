package net.pelozo.parcial.leonardo.velozo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Birthday{
    @Id
    Long id;

    LocalDate date;

    @ManyToOne
    Manager birthdayPerson;

    @OneToMany
    Set<Person> guests;
}
