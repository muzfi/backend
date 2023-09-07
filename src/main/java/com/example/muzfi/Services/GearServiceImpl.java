package com.example.muzfi.Services;

import com.example.muzfi.Model.Gear;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GearServiceImpl implements GearService {
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
    public void deleteGear(String gearId) {

    }
}
