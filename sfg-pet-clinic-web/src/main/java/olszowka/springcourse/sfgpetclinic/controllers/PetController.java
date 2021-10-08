package olszowka.springcourse.sfgpetclinic.controllers;


import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.Pet;
import olszowka.springcourse.sfgpetclinic.model.PetType;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import olszowka.springcourse.sfgpetclinic.services.PetService;
import olszowka.springcourse.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("owners/{ownerId}/pets")
public class PetController {
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return this.ownerService.findById(ownerId);
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initCreateNewPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "pets/createOrAddPetForm";
    }

    @PostMapping("/new")
    public String processCreateNewPetForm(@Valid Pet pet, Owner owner, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrAddPetForm";
        } else {
            Pet savedPet = petService.save(pet);
            owner.getPets().add(savedPet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/{petId}/edit")
    public String initEditPetForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return "pets/createOrAddPetForm";
    }

    @PostMapping("/{petId}/edit")
    public String processEditPetForm(@Valid Pet pet, Owner owner, Model model, BindingResult result) {
        if(result.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrAddPetForm";
        } else {
            Pet savedPet = petService.save(pet);
            owner.getPets().add(savedPet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
