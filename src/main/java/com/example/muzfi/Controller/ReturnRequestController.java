package com.example.muzfi.Controller;

import com.example.muzfi.Dto.ReturnRequestDTO;
import com.example.muzfi.Model.ReturnRequest;
import com.example.muzfi.Services.ReturnRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/returns")
public class ReturnRequestController {

    @Autowired
    private ReturnRequestService returnService;

    @PostMapping
    public ResponseEntity<?> createReturnRequest(@RequestBody ReturnRequestDTO returnRequestDTO) {
        try {
            ReturnRequest returnRequest = returnService.createReturnRequest(returnRequestDTO);
            return new ResponseEntity<>(returnRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception details
            return new ResponseEntity<>("Error creating return request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{requestId}")
    public ResponseEntity<?> updateReturnRequest(@PathVariable Long requestId, @RequestBody ReturnRequestDTO returnRequestDTO) {
        try {
            ReturnRequest updatedRequest = returnService.updateReturnRequest(requestId, returnRequestDTO);
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception details
            return new ResponseEntity<>("Error updating return request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<?> deleteReturnRequest(@PathVariable Long requestId) {
        try {
            returnService.deleteReturnRequest(requestId);
            return new ResponseEntity<>("Return request deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception details
            return new ResponseEntity<>("Error deleting return request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // other endpoints
}
