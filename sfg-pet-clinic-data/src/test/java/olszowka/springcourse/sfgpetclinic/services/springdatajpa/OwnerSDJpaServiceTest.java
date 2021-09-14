package olszowka.springcourse.sfgpetclinic.services.springdatajpa;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.repositories.OwnerRepository;
import olszowka.springcourse.sfgpetclinic.repositories.PetRepository;
import olszowka.springcourse.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService sdJpaService;

    Owner owner1;
    Owner owner2;

    String name1 = "John";

    @BeforeEach
    void setUp() {
        owner1 = Owner.builder().id(1L).secondName(name1).build();
        owner2 = Owner.builder().id(2L).secondName("name2").build();
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> ownersFromService = sdJpaService.findAll();

        assertEquals(2, ownersFromService.size());
        assertNotNull(ownersFromService);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        Owner owner = sdJpaService.findById(1L);

        assertNull(owner);
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(owner2));

        Owner owner = sdJpaService.findById(2L);

        assertNotNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner1);
        when(ownerRepository.save(null)).thenThrow(new NullPointerException());

        Owner returnedOwner = sdJpaService.save(owner1);

        assertNotNull(returnedOwner);
        assertThrows(NullPointerException.class, () -> sdJpaService.save(null));
    }

    @Test
    void delete() {
        sdJpaService.delete(owner1);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        sdJpaService.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findBySecondName() {
        when(ownerRepository.findBySecondName(any())).thenReturn(owner1);

        Owner smith = sdJpaService.findBySecondName(name1);

        assertEquals(name1, smith.getSecondName());

        verify(ownerRepository, times(1)).findBySecondName(any());
    }
}