package com.example.muzfi.Services;

import com.example.muzfi.Dto.ReturnRequestDTO;
import com.example.muzfi.Model.ReturnRequest;
import com.example.muzfi.Repository.ReturnRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnRequestServiceImpl implements ReturnRequestService {
    @Autowired
    private ReturnRequestRepository returnRequestRepository;

    @Override
    public ReturnRequest createReturnRequest(ReturnRequestDTO returnRequestDTO) {
        // Convert DTO to entity
        ReturnRequest returnRequest = new ReturnRequest();
        returnRequest.setProductId(returnRequestDTO.getProductId());
        returnRequest.setReason(returnRequestDTO.getReason());
        returnRequest.setBuyerMessage(returnRequestDTO.getMessage());
        returnRequest.setStatus("Requested"); // Initial status

        // Save to the database
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    public ReturnRequest updateReturnRequest(Long requestId, ReturnRequestDTO returnRequestDTO) {
        // Find the existing return request
        ReturnRequest returnRequest = returnRequestRepository.findById(String.valueOf(requestId))
                .orElseThrow(() -> new RuntimeException("Return Request not found with id: " + requestId));

        // Update fields
        returnRequest.setReason(returnRequestDTO.getReason());
        returnRequest.setBuyerMessage(returnRequestDTO.getMessage());
        // Assuming the status field is updated based on some business logic

        // Save updated return request
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    public void deleteReturnRequest(Long requestId) {
        // Check if the return request exists
        if (!returnRequestRepository.existsById(String.valueOf(requestId))) {
            throw new RuntimeException("Return Request not found with id: " + requestId);
        }

        // Delete the return request
        returnRequestRepository.deleteById(String.valueOf(requestId));
    }
    // other methods
}
