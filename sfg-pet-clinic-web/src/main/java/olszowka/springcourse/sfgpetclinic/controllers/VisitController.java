package olszowka.springcourse.sfgpetclinic.controllers;

import olszowka.springcourse.sfgpetclinic.model.Pet;
import olszowka.springcourse.sfgpetclinic.model.Visit;
import olszowka.springcourse.sfgpetclinic.services.PetService;
import olszowka.springcourse.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;

    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("pet")
    public Pet getPet(@PathVariable Long petId) {
        return petService.findById(petId);
    }

    @GetMapping("/new")
    public String initAddNewVisitForm(Model model, Pet pet) {
        Visit newVisit = new Visit();
        pet.getVisits().add(newVisit);
        model.addAttribute("visit", newVisit);
        return "visits/createOrAddVisitForm";
    }

    @PostMapping("/new")
    public String processAddNewVisitForm(@Valid Visit visit, Pet pet, @PathVariable Long ownerId, BindingResult result) {
        if(result.hasErrors()) {
            return "visits/createOrAddVisitForm";
        } else {
            visit.setPet(pet);
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }

}
