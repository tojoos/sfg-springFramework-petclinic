package olszowka.springcourse.sfgpetclinic.bootstrap;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.Vet;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import olszowka.springcourse.sfgpetclinic.services.VetService;
import olszowka.springcourse.sfgpetclinic.services.map.OwnerServiceMap;
import olszowka.springcourse.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Jan");
        owner1.setSecondName("Kowalski");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Michal");
        owner1.setSecondName("Majkowski");

        ownerService.save(owner2);

        System.out.println("Loading vets...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Zbigniew");
        vet1.setSecondName("Miłek");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Andrzej");
        vet1.setSecondName("Grabowski");

        vetService.save(vet2);

        System.out.println("Loading owners...");
    }
}
