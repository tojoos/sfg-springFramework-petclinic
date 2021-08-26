package olszowka.springcourse.sfgpetclinic.repositories;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findBySecondName(String secondName);
}
