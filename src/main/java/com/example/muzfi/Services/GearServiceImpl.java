package com.example.muzfi.Services;

import com.example.muzfi.Model.Gear;
import com.example.muzfi.Repository.GearRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GearServiceImpl implements GearService{
    private GearRepository gearRepository;


    @Override
    public Optional<List<Gear>> getAllGears() {
        return Optional.empty();
    }

    @Override
    public Optional<Gear> getGearById(String gearId) {
        return Optional.empty();
    }

    @Override
    public Gear createGear(Gear gear) {
        // Add any validation or business logic before saving the gear


        // Check if the gear with the same name already exists
        Optional<Gear> existingGear = gearRepository.findByName(gear.getName());
        if (existingGear.isPresent()) {
            // You may want to throw an exception or handle this case differently based on your requirements
            throw new IllegalArgumentException("Gear with the same name already exists");
        }

        // Additional validation or business logic can be added here based on your requirements

        // Save the gear to the database
        Gear savedGear = gearRepository.save(gear);

        // You can perform any additional logic here after the gear is saved, such as triggering events or notifications

        return savedGear;
    }


    @Override
    public Gear updateGear(String gearId, Gear gear) {
        return null;
    }

    @Override
    public List<Gear> searchGears(String searchTerm, String category) {
        // This is a basic example. In a real-world scenario,
        // you'd make use of advanced query specifications or criteria.
        if (category != null && !category.isEmpty()) {
            return gearRepository.findByNameContainingAndCategory(searchTerm, category);
        } else {
            return gearRepository.findByNameContaining(searchTerm);
        }
    }

    @Override
    public void deleteGear(String gearId) {

    }
}
