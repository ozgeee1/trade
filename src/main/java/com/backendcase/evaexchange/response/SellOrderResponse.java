package com.backendcase.evaexchange.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SellOrderResponse {

    private Long sellOrderId;

    private Long userId;

    private Long shareId;

    private BigDecimal quantity;
}
