package com.rahadyan.lelangtransaction.dto;

import com.rahadyan.lelangtransaction.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletHistoryRequest {
    public double amount;
    public PaymentType payment;
    public String note;
    public String walletId;
    public String userId;
}
