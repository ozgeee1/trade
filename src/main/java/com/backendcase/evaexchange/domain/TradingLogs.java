package com.backendcase.evaexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "trading_logs")
public class TradingLogs {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_seq")
    @SequenceGenerator(name = "log_id_seq", sequenceName = "log_id_seq")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "sell_order_id")
    private SellOrder sellOrder;

    @ManyToOne
    @JoinColumn(name = "buy_order_id")
    private BuyOrder buyOrder;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    private BigDecimal quantity;

    private BigDecimal totalPrice;


}
