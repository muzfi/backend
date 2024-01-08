package com.example.muzfi.Services;

import com.example.muzfi.Dto.ReturnRequestDTO;
import com.example.muzfi.Model.ReturnRequest;

public interface ReturnRequestService {

    ReturnRequest createReturnRequest(ReturnRequestDTO returnRequestDTO);
    ReturnRequest updateReturnRequest(Long requestId, ReturnRequestDTO returnRequestDTO);
    void deleteReturnRequest(Long requestId);
    // other methods
}
