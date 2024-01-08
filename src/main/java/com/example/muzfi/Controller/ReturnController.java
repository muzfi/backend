package com.example.muzfi.Controller;

import com.example.muzfi.Model.ReturnRequest;
import com.example.muzfi.Model.ReturnResponse;
import com.example.muzfi.Services.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class ReturnController {
    private final ReturnService returnService;

    @PostMapping("/open")
    public ResponseEntity<String> openReturnRequest(@RequestBody ReturnRequest returnRequest) {
        returnService.openReturnRequest(returnRequest);
        return new ResponseEntity<>("Return request opened successfully", HttpStatus.CREATED);
    }

    @PostMapping("/respond/{requestId}")
    public ResponseEntity<String> respondToReturnRequest(@PathVariable String requestId, @RequestBody ReturnResponse response) {
        returnService.respondToReturnRequest(requestId, response);
        return new ResponseEntity<>("Response submitted successfully", HttpStatus.OK);
    }

    @PostMapping("/send-item-back/{requestId}")
    public ResponseEntity<String> sendItemBack(@PathVariable String requestId, @RequestParam String trackingNumber) {
        returnService.sendItemBack(requestId, trackingNumber);
        return new ResponseEntity<>("Item shipped back successfully", HttpStatus.OK);
    }

    @PostMapping("/process-refund/{requestId}")
    public ResponseEntity<String> processRefund(@PathVariable String requestId) {
        returnService.processRefund(requestId);
        return new ResponseEntity<>("Refund processing initiated", HttpStatus.OK);
    }

    @PostMapping("/close/{requestId}")
    public ResponseEntity<String> closeReturnRequest(@PathVariable String requestId) {
        returnService.closeReturnRequest(requestId);
        return new ResponseEntity<>("Return request closed successfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReturnRequest>> getAllReturnRequests() {
        List<ReturnRequest> returnRequests = returnService.getAllReturnRequests();
        return new ResponseEntity<>(returnRequests, HttpStatus.OK);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<ReturnRequest> getReturnRequestById(@PathVariable String requestId) {
        Optional<ReturnRequest> optionalRequest = returnService.getReturnRequestById(requestId);
        return optionalRequest.map(request -> new ResponseEntity<>(request, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
