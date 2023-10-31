package com.example.muzfi.Services;

import com.example.muzfi.Model.MyBill;

import java.util.List;
import java.util.Optional;

public interface MyBillService {
    public List<MyBill> getAllMyBills();

    public Optional<MyBill> getMyBillById(String id);

    public MyBill createMyBill(MyBill myBill);

    public void deleteMyBill(String id);
}
