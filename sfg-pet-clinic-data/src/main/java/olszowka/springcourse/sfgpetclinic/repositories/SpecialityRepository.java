package olszowka.springcourse.sfgpetclinic.repositories;

import olszowka.springcourse.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
