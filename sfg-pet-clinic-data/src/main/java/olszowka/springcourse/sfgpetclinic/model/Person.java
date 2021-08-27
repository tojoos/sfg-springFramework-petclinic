package olszowka.springcourse.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class Person extends BaseEntity{

    @Column(name = "first_name") // not needed - Hibernate is doing it by default
    private String firstName;

    @Column(name = "second_name")
    private String secondName;
}
