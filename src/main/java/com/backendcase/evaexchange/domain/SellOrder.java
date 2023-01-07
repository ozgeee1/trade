package com.backendcase.evaexchange.domain;

import com.backendcase.evaexchange.utility.QuantityQalculation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Builder
public class SellOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sellorder_id_seq")
    @SequenceGenerator(name = "sellorder_id_seq", sequenceName = "sellorder_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "share_id")
    private Share share;


    @Column(name = "share_quantity")
    private BigDecimal shareQuantity;

    public BigDecimal setShareQuantityGivenQuantity(BigDecimal percent,BigDecimal quantity){
        this.shareQuantity= QuantityQalculation.calculateQuantity(percent,quantity);
        return shareQuantity;

    }



}
