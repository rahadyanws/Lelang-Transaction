package com.rahadyan.lelangtransaction.service;

import com.rahadyan.lelangtransaction.dto.BiddingRequest;
import com.rahadyan.lelangtransaction.dto.WalletHistoryRequest;
import com.rahadyan.lelangtransaction.model.BidHistory;
import com.rahadyan.lelangtransaction.model.WalletHistory;
import com.rahadyan.lelangtransaction.repository.WalletHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletHistoryService {
    @Autowired
    WalletHistoryRepository walletHistoryRepository;

    public WalletHistory transaction(WalletHistoryRequest walletHistoryRequest) {
        WalletHistory walletHistory = WalletHistory.builder()
                .payment(walletHistoryRequest.getPayment())
                .amount(walletHistoryRequest.getAmount())
                .note(walletHistoryRequest.getNote())
                .walletId(walletHistoryRequest.getWalletId())
                .userId(walletHistoryRequest.getUserId())
                .transactionTime(LocalDateTime.now())
                .build();
        return walletHistoryRepository.save(walletHistory);
    }
}
