package com.rahadyan.lelangtransaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bid_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BidHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String auctionItemId;

    @Column
    private String userId;

    @Column
    private double amount;

    @Column
    private LocalDateTime bidTime;

    public BidHistory(String auctionItemId, String userId, double amount, LocalDateTime bidTime) {
        this.auctionItemId = auctionItemId;
        this.userId = userId;
        this.amount = amount;
        this.bidTime = bidTime;
    }
}
