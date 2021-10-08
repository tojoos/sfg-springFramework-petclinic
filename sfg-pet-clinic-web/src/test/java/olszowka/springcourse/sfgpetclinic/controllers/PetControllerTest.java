package olszowka.springcourse.sfgpetclinic.controllers;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.Pet;
import olszowka.springcourse.sfgpetclinic.model.PetType;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import olszowka.springcourse.sfgpetclinic.services.PetService;
import olszowka.springcourse.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().name("cat").build());
        petTypes.add(PetType.builder().name("dog").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    void initCreateNewPetForm() throws Exception {
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrAddPetForm"));

        verifyNoInteractions(petService);
    }

    @Test
    void processCreateNewPetForm() throws Exception {
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService,times(1)).save(any(Pet.class));
        verify(petTypeService,times(1)).findAll();
        verify(ownerService,times(1)).findById(anyLong());
    }

    @Test
    void initEditPetForm() throws Exception {
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(3L).name("some name").build());

        mockMvc.perform(get("/owners/1/pets/3/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrAddPetForm"));

        verify(petService,times(1)).findById(anyLong());
    }

    @Test
    void processEditPetForm() throws Exception {
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(post("/owners/1/pets/3/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService,times(1)).save(any(Pet.class));
    }
}