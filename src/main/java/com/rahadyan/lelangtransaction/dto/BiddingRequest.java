package com.rahadyan.lelangtransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BiddingRequest {
    public String auctionItemId;
    public String userId;
    public double amount;
}
