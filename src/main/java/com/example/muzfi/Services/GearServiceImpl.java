package com.example.muzfi.Services;

import com.example.muzfi.Model.Gear;
import com.example.muzfi.Repository.GearRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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
        return null;
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
