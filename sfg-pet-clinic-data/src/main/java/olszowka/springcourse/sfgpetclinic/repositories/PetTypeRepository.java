package olszowka.springcourse.sfgpetclinic.repositories;

import olszowka.springcourse.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
