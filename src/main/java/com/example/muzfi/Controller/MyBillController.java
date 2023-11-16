package com.example.muzfi.Controller;

import com.example.muzfi.Model.MyBill;
import com.example.muzfi.Services.MyBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/mybills")
@RequiredArgsConstructor
public class MyBillController {
    private final MyBillService myBillService;

    @GetMapping
    public List<MyBill> getAllMyBills(){
        return myBillService.getAllMyBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyBill> getMyBillId(@PathVariable("id")String id){
        Optional<MyBill> myBill = myBillService.getMyBillById(id);
        if (myBill.isPresent()){
            return new ResponseEntity<>(myBill.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMyBill(@RequestBody MyBill myBill) {
        try {
            // Check if the user has reviewed billing information before submitting
            if (!myBill.isBillingInfoReviewed()) {
                return ResponseEntity.badRequest().body("Please review your billing information before submitting.");
            }

            MyBill _myBill = myBillService.createMyBill(myBill);
            return new ResponseEntity<>(_myBill, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating billing record.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMyBill(@PathVariable("id") String id){
        try {
            myBillService.deleteMyBill(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
