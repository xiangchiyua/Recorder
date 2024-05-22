package com.recorder.service;

import com.recorder.dal.bean.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillService {
    // 保存一个新的账单记录
    Bill saveBill(Bill bill);

    // 通过ID删除账单记录
    void deleteBillById(int billId);

    // 更新账单记录
    Bill updateBill(Bill bill);

    // 通过ID获取账单记录
    Bill getBillById(int billId);

    // 分页查询账单记录
    Page<Bill> getBillsByPage(Pageable pageable);

    // 根据日期范围查询账单记录
    List<Bill> queryBillsByDate(Date startDate, Date endDate);

    // 根据分类ID查询账单记录
    List<Bill> queryBillsByCategory(int cateId);
}