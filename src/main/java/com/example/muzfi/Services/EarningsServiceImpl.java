package com.example.muzfi.Services;

import com.example.muzfi.Model.Earnings;
import com.example.muzfi.Repository.EarningsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EarningsServiceImpl implements EarningsService{
    private final EarningsRepository earningsRepository;

    public List<Earnings> getAllEarnings() {
        return earningsRepository.findAll();
    }

    public Optional<Earnings> getEarningsById(String id) {
        return earningsRepository.findById(id);
    }

    public Earnings createEarnings(Earnings earnings) {
        return earningsRepository.save(earnings);
    }

    public void deleteEarnings(String id) {
        earningsRepository.deleteById(id);
    }

    public List<Earnings> getEarningsByUserId(String userId) {
        return earningsRepository.findByUserId(userId);
    }

    public List<Earnings> getEarningsByOrderId(String orderId) {
        return earningsRepository.findByOrderId(orderId);
    }


    public void updateEarningStatus(String id, String status) {
        Optional<Earnings> existingEarning = getEarningsById(id);

        if (existingEarning.isPresent()) {
            Earnings updatedEarning = existingEarning.get();
            updatedEarning.setStatus(status);
            createEarnings(updatedEarning);
        }
    }
}
