package com.example.muzfi.Controller;

import com.example.muzfi.Model.Gear;
import com.example.muzfi.Services.GearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gears")
public class GearController {

    private final GearService gearService;

    @Autowired
    public GearController(GearService gearService) {
        this.gearService = gearService;
    }

    @GetMapping
    public ResponseEntity<?> getAllGears() {
        try {
            Optional<List<Gear>> gears = gearService.getAllGears();

            if (gears.isPresent()) {
                return new ResponseEntity<>(gears, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Gears Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{gearId}")
    public ResponseEntity<?> getGearById(@PathVariable String gearId) {
        try {
            Optional<Gear> gear = gearService.getGearById(gearId);

            if (gear.isPresent()) {
                return new ResponseEntity<>(gear, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gear Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createGear(@RequestBody Gear gear) {
        try {
            Gear createdGear = gearService.createGear(gear);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGear);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{gearId}")
    public ResponseEntity<?> updateGear(@PathVariable String gearId, @RequestBody Gear gear) {
        try {
            Gear updatedGear = gearService.updateGear(gearId, gear);
            if (updatedGear != null) {
                return new ResponseEntity<>(updatedGear, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Gear Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{gearId}")
    public ResponseEntity<?> deleteGear(@PathVariable String gearId) {
        try {
            gearService.deleteGear(gearId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // You can continue to add other endpoints to handle specific requirements such as managing reviews, ratings, etc.
}
