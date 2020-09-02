package com.rahadyan.lelangtransaction.model;

import com.rahadyan.lelangtransaction.enums.PaymentType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "wallet_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WalletHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private double amount;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentType payment;

    @Column
    private String note;

    @Column
    private String walletId;

    @Column
    private String userId;

    @Column
    private LocalDateTime transactionTime;
}
