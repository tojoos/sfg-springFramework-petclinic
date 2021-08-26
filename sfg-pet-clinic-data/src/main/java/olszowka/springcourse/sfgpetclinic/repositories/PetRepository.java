package olszowka.springcourse.sfgpetclinic.repositories;

import olszowka.springcourse.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
