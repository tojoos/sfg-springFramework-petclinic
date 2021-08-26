package olszowka.springcourse.sfgpetclinic.bootstrap;

import olszowka.springcourse.sfgpetclinic.model.*;
import olszowka.springcourse.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    //@Autowired - not required
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Jan");
        owner1.setSecondName("Kowalski");
        owner1.setAddress("42A");
        owner1.setCity("Katowice");
        owner1.setTelephone("356717822");

        Pet pet1 = new Pet();
        pet1.setPetType(savedDogPetType);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());
        pet1.setName("Just Dog");

        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Michal");
        owner2.setSecondName("Majkowski");
        owner1.setAddress("43/3");
        owner1.setCity("Warszawa");
        owner1.setTelephone("282859783");

        Pet pet2 = new Pet();
        pet2.setPetType(savedCatPetType);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());
        pet2.setName("Just Cat");

        owner2.getPets().add(pet2);

        ownerService.save(owner2);

        System.out.println("Loading vets...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Zbigniew");
        vet1.setSecondName("Miłek");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Andrzej");
        vet2.setSecondName("Grabowski");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Marek");
        vet3.setSecondName("Chokula");
        vet3.getSpecialities().add(savedDentistry);

        vetService.save(vet3);

        System.out.println("Loading owners...");

        Visit visit1 = new Visit();
        visit1.setPet(pet2);
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Just regular cat visit.");

        visitService.save(visit1);

        System.out.println("Loading visits...");
    }
}
