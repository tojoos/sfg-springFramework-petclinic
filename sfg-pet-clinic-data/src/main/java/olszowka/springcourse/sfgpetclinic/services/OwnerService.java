package olszowka.springcourse.sfgpetclinic.services;

import olszowka.springcourse.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner> {
    Owner findBySecondName(String secondName);
    List<Owner> findAllBySecondNameLike(String secondName);
}
