package olszowka.springcourse.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity{

    public Person(Long id, String firstName, String secondName) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Column(name = "first_name") // not needed - Hibernate is doing it by default
    private String firstName;

    @Column(name = "second_name")
    private String secondName;
}
