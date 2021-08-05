package olszowka.springcourse.sfgpetclinic.services;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
