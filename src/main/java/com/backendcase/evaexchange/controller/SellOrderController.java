package com.backendcase.evaexchange.controller;

import com.backendcase.evaexchange.domain.PortfolioShare;
import com.backendcase.evaexchange.domain.SellOrder;
import com.backendcase.evaexchange.domain.User;
import com.backendcase.evaexchange.exception.BadRequestException;
import com.backendcase.evaexchange.request.SellOrderRequest;
import com.backendcase.evaexchange.response.SellOrderResponse;
import com.backendcase.evaexchange.service.PortfolioShareServiceImpl;
import com.backendcase.evaexchange.service.SellOrderServiceImpl;
import com.backendcase.evaexchange.service.ShareServiceImpl;
import com.backendcase.evaexchange.service.UserServiceImpl;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SellOrderController {

    private final SellOrderServiceImpl sellOrderService;
    private final ShareServiceImpl shareService;
    private final UserServiceImpl userService;

    private final PortfolioShareServiceImpl portfolioShareService;

    public SellOrderController(SellOrderServiceImpl sellOrderService, ShareServiceImpl shareService, UserServiceImpl userService,
            PortfolioShareServiceImpl portfolioShareService) {
        this.sellOrderService = sellOrderService;
        this.shareService = shareService;
        this.userService = userService;
        this.portfolioShareService = portfolioShareService;
    }

    @PostMapping("sell/order")
    public void giveSellOrder(@Valid @RequestBody SellOrderRequest sellOrderRequest){
        User user  = userService.findUserById(sellOrderRequest.getUserId());
        if(user.getPortfolio()==null){
            throw new BadRequestException("You should have registered portfolio to sell shares");
        }
        PortfolioShare portfolioShare = portfolioShareService.findPortfolioShare(sellOrderRequest.getShareId(),
                sellOrderRequest.getUserId());

        SellOrder sellOrder = SellOrder.builder()
                .user(user)
                .share(portfolioShare.getShare()).build();
        sellOrderService.giveSellOrder(sellOrderRequest.getPercent(),sellOrder);

    }

    @GetMapping("list/sell/orders")
    public List<SellOrderResponse> listSellOrders(){
        List<SellOrderResponse> sellOrderResponseList = new ArrayList<>();
        for(SellOrder order: sellOrderService.getSellOrders()){
            SellOrderResponse response = SellOrderResponse.builder()
                    .sellOrderId(order.getId())
                    .quantity(order.getShareQuantity())
                    .shareId(order.getShare().getId())
                    .userId(order.getUser().getId()).build();
            sellOrderResponseList.add(response);
        }
        return sellOrderResponseList;
    }


}
