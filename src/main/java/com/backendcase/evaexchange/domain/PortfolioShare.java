package com.backendcase.evaexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedNotification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "portfolio_share")
public class PortfolioShare {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_share_id_seq")
    @SequenceGenerator(name = "portfolio_share_id_seq", sequenceName = "portfolio_share_id_seq",allocationSize = 7)
    private Long id;

    @OneToOne
    private Portfolio portfolio;

    @ManyToOne
    private User user;

    @ManyToOne
    private Share share;

    private BigDecimal quantity;
}
