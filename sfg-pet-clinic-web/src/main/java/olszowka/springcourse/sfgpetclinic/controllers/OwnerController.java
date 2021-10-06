package olszowka.springcourse.sfgpetclinic.controllers;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedField(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({ "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/find";
    }

    @GetMapping("")
    public String processFindOwner(Owner owner, BindingResult result, Model model) {
        if(owner.getSecondName() == null) {
            owner.setSecondName("");
        }

        List<Owner> ownersFound = ownerService.findAllBySecondNameLike("%" + owner.getSecondName() + "%");

        if(ownersFound.isEmpty()) {
            result.rejectValue("secondName", "notFound", "not found");
            return "owners/find";
        } else if (ownersFound.size() == 1) {
            return "redirect:/owners/" + ownersFound.get(0).getId();
        } else {
            model.addAttribute("selections", ownersFound);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{id}")
    public ModelAndView showOwner(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("owners/show");
        mav.addObject("owner", ownerService.findById(Long.valueOf(id)));
        return mav;
    }

    @GetMapping("/new")
    public String initCreateNewOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/createOrAddForm";
    }

    @PostMapping("/new")
    public String processCreateNewOwnerForm(@Valid Owner owner, BindingResult result) {
        if(result.hasErrors()) {
            return "owners/createOrAddForm";
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String initEditOwnerForm(@PathVariable Long id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owners/createOrAddForm";
    }

    @PostMapping("/{id}/edit")
    public String processEditOwnerForm(@Valid Owner owner, @PathVariable Long id,
                                       BindingResult result) {
        if(result.hasErrors()) {
            return "owners/createOrAddForm";
        } else {
            owner.setId(id);
            ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
