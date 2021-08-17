package olszowka.springcourse.sfgpetclinic.bootstrap;

import olszowka.springcourse.sfgpetclinic.model.Owner;
import olszowka.springcourse.sfgpetclinic.model.PetType;
import olszowka.springcourse.sfgpetclinic.model.Vet;
import olszowka.springcourse.sfgpetclinic.services.OwnerService;
import olszowka.springcourse.sfgpetclinic.services.PetTypeService;
import olszowka.springcourse.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    //@Autowired - not required
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

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

        Vet vet3 = new Vet();
        vet3.setFirstName("Marek");
        vet3.setSecondName("Chokula");

        vetService.save(vet3);

        System.out.println("Loading owners...");
    }
}
