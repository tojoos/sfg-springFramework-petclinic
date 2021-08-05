package olszowka.springcourse.sfgpetclinic.services;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
