package com.example.muzfi.Services;

import com.example.muzfi.Model.Earnings;

import java.util.List;
import java.util.Optional;

public interface EarningsService {
    public List<Earnings> getAllEarnings();
    public Optional<Earnings> getEarningsById(String id);
    public Earnings createEarnings(Earnings earnings);
    public void deleteEarnings(String id);
    public List<Earnings> getEarningsByUserId(String userId);
    public List<Earnings> getEarningsByOrderId(String orderId);
    public void updateEarningStatus(String id, String status);
}
