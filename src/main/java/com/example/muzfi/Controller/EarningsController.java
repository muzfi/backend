package com.example.muzfi.Controller;

import com.example.muzfi.Model.Earnings;
import com.example.muzfi.Services.EarningsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/earnings")
@RequiredArgsConstructor
public class EarningsController {
    private final EarningsService earningsService;

    @GetMapping
    public List<Earnings> getAllEarnings() {
        return earningsService.getAllEarnings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Earnings> getEarningById(@PathVariable("id") String id) {
        Optional<Earnings> earning = earningsService.getEarningsById(id);

        if (earning.isPresent()) {
            return new ResponseEntity<>(earning.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Earnings> createEarning(@RequestBody Earnings earning) {
        try {
            Earnings _earning = earningsService.createEarnings(earning);
            return new ResponseEntity<>(_earning, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEarning(@PathVariable("id") String id) {
        try {
            earningsService.deleteEarnings(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Earnings> getEarningByUserId(@PathVariable("userId") String userId) {
        return earningsService.getEarningsByUserId(userId);
    }

    @GetMapping("/order/{orderId}")
    public List<Earnings> getEarningByOrderId(@PathVariable("orderId") String orderId) {
        return earningsService.getEarningsByOrderId(orderId);
    }


    @PutMapping("/{id}/status/{status}")
    public void updateStatus(@PathVariable("id") String id, @PathVariable("status") String status) {
        earningsService.updateEarningStatus(id, status);
    }
}
