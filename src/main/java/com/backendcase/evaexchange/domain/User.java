package com.backendcase.evaexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq",allocationSize = 6)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.PERSIST,mappedBy = "user")
    private Portfolio portfolio;

    @OneToMany(mappedBy = "user")
    private Set<SellOrder> sellOrders = new HashSet<>();


    @OneToMany(mappedBy = "user")
    private Set<BuyOrder> buyOrders = new HashSet<>();

    private BigDecimal balance;

}
