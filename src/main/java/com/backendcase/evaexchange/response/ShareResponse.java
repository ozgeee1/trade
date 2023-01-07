package com.backendcase.evaexchange.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShareResponse {

    private String symbol;

    private BigDecimal price;

    private LocalDateTime updatedTime;
}
