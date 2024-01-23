package com.example.muzfi.Services;

import com.example.muzfi.Model.MyBill;
import com.example.muzfi.Repository.MyBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyBillServiceImpl implements MyBillService {


    private final MyBillRepository myBillRepository;

    public List<MyBill> getAllMyBills() {
        return myBillRepository.findAll();
    }

    public Optional<MyBill> getMyBillById(String id) {
        return myBillRepository.findById(id);
    }

    public MyBill createMyBill(MyBill myBill) {
        NotificationServiceImpl notificationService = new NotificationServiceImpl();
        return myBillRepository.save(myBill);
    }

    public void deleteMyBill(String id) {
        myBillRepository.deleteById(id);
    }
}
