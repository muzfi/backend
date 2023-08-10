package com.example.muzfi.Services;

import com.example.muzfi.Model.GearRoom;

import java.util.List;
import java.util.Optional;

public interface GearRoomService {
    Optional<List<GearRoom>> getAllGearRooms();

    Optional<GearRoom> getGearRoomById(String gearRoomId);

    List<GearRoom> getGearRoomsByUserId(Integer userId);

    GearRoom createGearRoom(GearRoom gearRoom);

    GearRoom updateGearRoom(String gearRoomId, GearRoom gearRoom);

    void deleteGearRoom(String gearRoomId);
}
