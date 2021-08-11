package olszowka.springcourse.sfgpetclinic.bootstrap;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.Vet;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import olszowka.springcourse.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    //@Autowired - not required
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Jan");
        owner1.setSecondName("Kowalski");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Michal");
        owner2.setSecondName("Majkowski");

        ownerService.save(owner2);

        System.out.println("Loading vets...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Zbigniew");
        vet1.setSecondName("Miłek");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Andrzej");
        vet2.setSecondName("Grabowski");

        vetService.save(vet2);

        System.out.println("Loading owners...");
    }
}
