package com.example.muzfi.Controller;

import com.example.muzfi.Model.GearRoom;
import com.example.muzfi.Services.GearRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gearRooms")
public class GearRoomController {
    private final GearRoomService gearRoomService;

    @Autowired
    public GearRoomController(GearRoomService gearRoomService) {
        this.gearRoomService = gearRoomService;
    }

    @GetMapping
    public ResponseEntity<?> getAllGearRooms() {
        try {
            Optional<List<GearRoom>> gearRooms = gearRoomService.getAllGearRooms();
            return new ResponseEntity<>(gearRooms, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getGearRoomsByUserId(@PathVariable("userId") Integer userId) {
        try {
            List<GearRoom> gearRooms = gearRoomService.getGearRoomsByUserId(userId);
            return new ResponseEntity<>(gearRooms, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createGearRoom(@RequestBody GearRoom gearRoom) {
        try {
            GearRoom createdGearRoom = gearRoomService.createGearRoom(gearRoom);
            return new ResponseEntity<>(createdGearRoom, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{gearRoomId}")
    public ResponseEntity<?> updateGearRoom(@PathVariable("gearRoomId") String gearRoomId, @RequestBody GearRoom gearRoom) {
        try {
            GearRoom updatedGearRoom = gearRoomService.updateGearRoom(gearRoomId, gearRoom);
            if (updatedGearRoom != null) {
                return new ResponseEntity<>(updatedGearRoom, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gear Room Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{gearRoomId}")
    public ResponseEntity<?> deleteGearRoom(@PathVariable String gearRoomId) {
        try {
            gearRoomService.deleteGearRoom(gearRoomId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/latest")
    public ResponseEntity<List<GearRoom>> getLatestGearRooms() {
        List<GearRoom> latestGearRooms = gearRoomService.getLatestGearRooms();
        if (latestGearRooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(latestGearRooms);
    }
}
