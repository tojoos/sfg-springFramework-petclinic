package olszowka.springcourse.sfgpetclinic.repositories;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findBySecondName(String secondName);
    List<Owner> findAllBySecondNameLike(String secondName);
}
