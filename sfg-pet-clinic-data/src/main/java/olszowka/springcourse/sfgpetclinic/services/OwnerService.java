package olszowka.springcourse.sfgpetclinic.services;

import olszowka.springcourse.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner> {
    Owner findBySecondName(String lastName);
}
