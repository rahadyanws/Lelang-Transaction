package com.rahadyan.lelangtransaction.controller;

import com.rahadyan.lelangtransaction.dto.BiddingRequest;
import com.rahadyan.lelangtransaction.dto.ResponseWrapper;
import com.rahadyan.lelangtransaction.dto.WalletHistoryRequest;
import com.rahadyan.lelangtransaction.model.BidHistory;
import com.rahadyan.lelangtransaction.model.WalletHistory;
import com.rahadyan.lelangtransaction.service.BidHistoryService;
import com.rahadyan.lelangtransaction.service.WalletHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String BROWSE_BIDDING_SUCCESS = "Bidding Shown Success!";
    private static final String BROWSE_BIDDING_FAILED = "Bidding Shown Failed!";
    private static final String READ_MAX_AMOUNT_SUCCESS = "Read Max Amount Success!";
    private static final String READ_MAX_AMOUNT_FAILED = "Read Max Amount Failed!";
    private static final String ADD_BIDDING_SUCCESS = "Bidding Item Success!";
    private static final String ADD_BIDDING_FAILED = "Bidding Item Failed!";
    private static final String BIDDING_NEED_MORE = "Amount Bidding Lower Than Current!";
    private static final String ADD_TRANSACTION_SUCCESS = "Transaction Item Success!";
    private static final String ADD_TRANSACTION_FAILED = "Transaction Item Failed!";

    @Autowired
    BidHistoryService bidHistoryService;

    @Autowired
    WalletHistoryService walletHistoryService;

    @CrossOrigin(origins = "*")
    @GetMapping("/read-max-bidding")
    public ResponseEntity<ResponseWrapper> readMaxBidding(@RequestParam("auctionItemId") String auctionItemId) {
        BidHistory bidHistory = null;
        List<String> errors = new ArrayList<>();
        ResponseWrapper responseMessage = null;
        try {
            bidHistory = bidHistoryService.findByAuctionItem(auctionItemId);
            responseMessage = new ResponseWrapper(READ_MAX_AMOUNT_SUCCESS, bidHistory, 200, errors);
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseMessage = new ResponseWrapper(READ_MAX_AMOUNT_FAILED, bidHistory, 404, errors);
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/browse-bidding")
    public ResponseEntity<ResponseWrapper> browse() {
        ResponseWrapper responseMessage = null;
        List<String> errors = new ArrayList<>();
        try {
            List<BidHistory> bidHistories = bidHistoryService.findAll();
            responseMessage = new ResponseWrapper(BROWSE_BIDDING_SUCCESS, bidHistories, 200, errors);
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseMessage = new ResponseWrapper(BROWSE_BIDDING_FAILED, null, 404, errors);
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/bidding")
    public ResponseEntity<ResponseWrapper> bidding(@RequestBody BiddingRequest biddingRequest, Errors err) {
        BidHistory bidHistory = null;
        ResponseWrapper responseMessage = null;
        List<String> errors = new ArrayList<>();
        try {
            bidHistory = bidHistoryService.bidding(biddingRequest);
            if (bidHistory != null) {
                responseMessage = new ResponseWrapper(ADD_BIDDING_SUCCESS, bidHistory, 200, errors);
            } else {
                responseMessage = new ResponseWrapper(BIDDING_NEED_MORE, bidHistory, 200, errors);
            }
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseMessage = new ResponseWrapper(ADD_BIDDING_FAILED, bidHistory, 500, errors);
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/transaction")
    public ResponseEntity<ResponseWrapper> transaction(@RequestBody WalletHistoryRequest walletHistoryRequest, Errors err) {
        WalletHistory walletHistory = null;
        ResponseWrapper responseMessage = null;
        List<String> errors = new ArrayList<>();
        try {
            walletHistory = walletHistoryService.transaction(walletHistoryRequest);
            responseMessage = new ResponseWrapper(ADD_TRANSACTION_SUCCESS, walletHistory, 200, errors);
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseMessage = new ResponseWrapper(ADD_TRANSACTION_FAILED, walletHistory, 500, errors);
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }
}
