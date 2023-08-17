package com.example.muzfi.Services;

import com.example.muzfi.Model.Gear;

import java.util.List;
import java.util.Optional;

public interface GearService {
    Optional<List<Gear>> getAllGears();

    Optional<Gear> getGearById(String gearId);

    Gear createGear(Gear gear);

    Gear updateGear(String gearId, Gear gear);

    List<Gear> searchGears(String searchTerm, String category);


    void deleteGear(String gearId);
}
