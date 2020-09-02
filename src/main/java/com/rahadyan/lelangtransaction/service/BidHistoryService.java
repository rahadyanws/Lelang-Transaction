package com.rahadyan.lelangtransaction.service;

import com.rahadyan.lelangtransaction.dto.BiddingRequest;
import com.rahadyan.lelangtransaction.model.BidHistory;
import com.rahadyan.lelangtransaction.repository.BidHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BidHistoryService {
    @Autowired
    BidHistoryRepository bidHistoryRepository;

    public List<BidHistory> findAll() {
        List<BidHistory> bidHistories = new ArrayList<>();
        this.bidHistoryRepository.findAll().forEach(auctionItem -> bidHistories.add(auctionItem));
        return bidHistories;
    }

    public BidHistory bidding(BiddingRequest biddingRequest) {
        BidHistory bidHistory = BidHistory.builder()
                .auctionItemId(biddingRequest.getAuctionItemId())
                .userId(biddingRequest.getUserId())
                .amount(biddingRequest.getAmount())
                .bidTime(LocalDateTime.now())
                .build();
        return bidHistoryRepository.save(bidHistory);
    }

    public BidHistory findByAuctionItem(String auctionItemId ) throws Exception {
        return this.bidHistoryRepository.findFirstByAuctionItemIdOrderByAmountDesc(auctionItemId).orElseThrow(() -> new Exception("User not found"));
    }
}
