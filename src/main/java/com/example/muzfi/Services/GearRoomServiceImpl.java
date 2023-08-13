package com.example.muzfi.Services;

import com.example.muzfi.Model.GearRoom;
import com.example.muzfi.Repository.GearRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GearRoomServiceImpl implements GearRoomService {

    private final GearRoomRepository gearRoomRepository;

    @Autowired
    public GearRoomServiceImpl(GearRoomRepository gearRoomRepository) {
        this.gearRoomRepository = gearRoomRepository;
    }

    @Override
    public Optional<List<GearRoom>> getAllGearRooms() {
        return Optional.of(gearRoomRepository.findAll());
    }

    @Override
    public Optional<GearRoom> getGearRoomById(String gearRoomId) {
        return gearRoomRepository.findById(gearRoomId);
    }

    @Override
    public List<GearRoom> getGearRoomsByUserId(Integer userId) {
        return gearRoomRepository.findAllByUserId(userId);
    }

    @Override
    public GearRoom createGearRoom(GearRoom gearRoom) {
        return gearRoomRepository.save(gearRoom);
    }

    @Override
    public GearRoom updateGearRoom(String gearRoomId, GearRoom updatedGearRoom) {
        GearRoom existingGearRoom = gearRoomRepository.findById(gearRoomId).orElse(null);
        if (existingGearRoom != null) {
            existingGearRoom.setGearIds(updatedGearRoom.getGearIds());
            // More updates as needed

            return gearRoomRepository.save(existingGearRoom);
        }
        return null;
    }

    @Override
    public void deleteGearRoom(String gearRoomId) {
        gearRoomRepository.deleteById(gearRoomId);
    }
}
