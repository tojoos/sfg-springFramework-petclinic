package olszowka.springcourse.sfgpetclinic.services.map;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    private Long id = 1L;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(id).firstName("andrew").secondName("janas").build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Long ownerId = 1L;
        Owner owner1 = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner1.getId());
    }

    @Test
    void save() {
        ownerServiceMap.save(Owner.builder().id(2L).firstName("mark").build());
        assertEquals(2, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        assertEquals(1, ownerServiceMap.findAll().size());
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        Long id = 2L;
        Owner owner1 = Owner.builder().id(id).build();
        ownerServiceMap.save(owner1);
        assertEquals(2, ownerServiceMap.findAll().size());
        ownerServiceMap.deleteById(id);
        assertEquals(1, ownerServiceMap.findAll().size());
    }

    @Test
    void findBySecondName() {
        Long id = 4L;
        String secondName = "tomas";
        Owner owner1 = ownerServiceMap.save(Owner.builder().id(id).secondName(secondName).build());
        assertEquals(id, ownerServiceMap.findBySecondName(secondName).getId());
    }

    @Test
    void findBySecondNameNotFound() {
        Long id = 4L;
        String secondName = "tomas";
        Owner owner1 = ownerServiceMap.save(Owner.builder().id(id).secondName(secondName).build());
        assertNull(ownerServiceMap.findBySecondName("other name"));
    }
}