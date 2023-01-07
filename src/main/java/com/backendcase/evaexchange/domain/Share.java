package com.backendcase.evaexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Builder
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "share_id_seq")
    @SequenceGenerator(name = "share_id_seq", sequenceName = "share_id_seq",allocationSize = 5)
    private Long id;

    @Size(min = 3,max = 3)
    @Pattern(regexp = "[A-Z]{3}")
    private String symbol;

    private boolean registered;

    private BigDecimal price;

    private LocalDateTime updatedTime;

}
