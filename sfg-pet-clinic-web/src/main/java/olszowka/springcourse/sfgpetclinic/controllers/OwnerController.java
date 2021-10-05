package olszowka.springcourse.sfgpetclinic.controllers;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
