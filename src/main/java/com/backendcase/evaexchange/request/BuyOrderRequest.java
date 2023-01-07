package com.backendcase.evaexchange.request;

import com.backendcase.evaexchange.validator.PercentNotNullCheck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuyOrderRequest {


    @NotNull
    private Long userId;

    @NotNull
    private Long sellOrderId;

    @Digits(integer = 2,fraction = 0)
    @Min(10)
    @PercentNotNullCheck
    private BigDecimal percent;

}
