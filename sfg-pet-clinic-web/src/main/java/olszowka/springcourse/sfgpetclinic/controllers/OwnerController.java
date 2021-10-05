package olszowka.springcourse.sfgpetclinic.controllers;

import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwner() {
        return "notImplemented";
    }

    @GetMapping("/{id}")
    public ModelAndView showOwner(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("owners/show");
        mav.addObject("owner", ownerService.findById(Long.valueOf(id)));
        return mav;
    }
}
