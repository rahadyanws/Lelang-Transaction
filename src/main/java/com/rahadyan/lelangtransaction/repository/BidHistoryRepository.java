package com.rahadyan.lelangtransaction.repository;

import com.rahadyan.lelangtransaction.model.BidHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidHistoryRepository extends JpaRepository<BidHistory, String>  {
    Optional<BidHistory> findFirstByAuctionItemIdOrderByAmountDesc(String auctionItemId);
}
