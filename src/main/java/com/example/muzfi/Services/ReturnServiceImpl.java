package com.example.muzfi.Services;

import com.example.muzfi.Enums.ReturnStatus;
import com.example.muzfi.Model.ReturnRequest;
import com.example.muzfi.Model.ReturnResponse;
import com.example.muzfi.Repository.ReturnRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService{

    private final ReturnRequestRepository returnRequestRepository;

    public void respondToReturnRequest(String requestId, ReturnResponse response) {
        Optional<ReturnRequest> optionalRequest = returnRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            ReturnRequest request = optionalRequest.get();
            request.setResponse(response);
            returnRequestRepository.save(request);
        }
    }

    public void sendItemBack(String requestId, String trackingNumber) {
        Optional<ReturnRequest> optionalRequest = returnRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            ReturnRequest request = optionalRequest.get();
            request.setTrackingNumber(trackingNumber);
            request.setStatus(ReturnStatus.ITEM_SHIPPED);
            returnRequestRepository.save(request);
        }
    }

    public void processRefund(String requestId) {
        Optional<ReturnRequest> optionalRequest = returnRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            ReturnRequest request = optionalRequest.get();
            request.setStatus(ReturnStatus.REFUND_PROCESSING);
            returnRequestRepository.save(request);
        }
    }

    public void closeReturnRequest(String requestId) {
        returnRequestRepository.deleteById(requestId);
    }

    public List<ReturnRequest> getAllReturnRequests() {
        return returnRequestRepository.findAll();
    }

    public Optional<ReturnRequest> getReturnRequestById(String requestId) {
        return returnRequestRepository.findById(requestId);
    }

    public void openReturnRequest(ReturnRequest returnRequest) {
        // Set initial status for the return request
        returnRequest.setStatus(ReturnStatus.REQUESTED);

        // Save the return request to the database
        returnRequestRepository.save(returnRequest);
    }
}
