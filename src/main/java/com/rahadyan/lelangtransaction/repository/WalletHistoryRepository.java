package com.rahadyan.lelangtransaction.repository;

import com.rahadyan.lelangtransaction.model.WalletHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletHistoryRepository extends JpaRepository<WalletHistory, String> {
}
