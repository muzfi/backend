package com.example.muzfi.Services;

import com.example.muzfi.Enums.ReturnStatus;
import com.example.muzfi.Model.ReturnRequest;
import com.example.muzfi.Model.ReturnResponse;

import java.util.List;
import java.util.Optional;

public interface ReturnService {
    public void respondToReturnRequest(String requestId, ReturnResponse response);

    public void sendItemBack(String requestId, String trackingNumber);

    public void processRefund(String requestId);

    public void closeReturnRequest(String requestId);
    public List<ReturnRequest> getAllReturnRequests();

    public Optional<ReturnRequest> getReturnRequestById(String requestId);

    void openReturnRequest(ReturnRequest returnRequest);
}
