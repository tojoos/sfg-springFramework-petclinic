package olszowka.springcourse.sfgpetclinic.services.map;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.Pet;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import olszowka.springcourse.sfgpetclinic.services.PetService;
import olszowka.springcourse.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object!=null) {
            if(object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if(pet.getPetType() != null) {
                        if(pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet type is required!");
                    }

                    if(pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findBySecondName(String secondName) {
        return this.findAll()
                .stream()
                .filter(o -> o.getSecondName().equalsIgnoreCase(secondName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllBySecondNameLike(String secondName) {
        //todo - not used currently. (DATAJPA is usage)
        return null;
    }
}
