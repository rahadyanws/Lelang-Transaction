package com.rahadyan.lelangtransaction.service;

import com.rahadyan.lelangtransaction.dto.BiddingRequest;
import com.rahadyan.lelangtransaction.model.BidHistory;
import com.rahadyan.lelangtransaction.repository.BidHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidHistoryService {
    @Autowired
    BidHistoryRepository bidHistoryRepository;

    public List<BidHistory> findAll() {
        List<BidHistory> bidHistories = new ArrayList<>();
        bidHistoryRepository.findAll().forEach(auctionItem -> bidHistories.add(auctionItem));
        return bidHistories;
    }

    public BidHistory bidding(BiddingRequest biddingRequest) {
        Optional<BidHistory> bidHistoryOptional = bidHistoryRepository.findFirstByAuctionItemIdOrderByAmountDesc(biddingRequest.getAuctionItemId());
        BidHistory bidHistory = null;
        if(biddingRequest.getAmount() <= bidHistoryOptional.get().getAmount()) {
            bidHistory = null;
        } else {
            bidHistory = BidHistory.builder()
                    .auctionItemId(biddingRequest.getAuctionItemId())
                    .userId(biddingRequest.getUserId())
                    .amount(biddingRequest.getAmount())
                    .bidTime(LocalDateTime.now())
                    .build();
            bidHistory = bidHistoryRepository.save(bidHistory);
        }
        return bidHistory;
    }

    public BidHistory findByAuctionItem(String auctionItemId ) throws Exception {
        return bidHistoryRepository.findFirstByAuctionItemIdOrderByAmountDesc(auctionItemId).orElseThrow(() -> new Exception("User not found"));
    }
}
