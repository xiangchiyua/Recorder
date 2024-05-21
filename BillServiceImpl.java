package com.recorder.service.impl;

import com.recorder.dal.bean.Bill;
import com.recorder.dal.repository.BillRepository;
import com.recorder.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void deleteBillById(int billId) {
        billRepository.deleteById(billId);
    }

    @Override
    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill getBillById(int billId) {
        return billRepository.findById(billId).orElse(null);
    }

    @Override
    public Page<Bill> getBillsByPage(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public List<Bill> queryBillsByDate(Date startDate, Date endDate) {
       
        return billRepository.findByDateTimeBetween(startDate, endDate);
    }

    @Override
    public List<Bill> queryBillsByCategory(int cateId) {
       
        return billRepository.findByCateId(cateId);
    }
}