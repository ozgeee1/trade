package com.backendcase.evaexchange.controller;

import com.backendcase.evaexchange.domain.BuyOrder;
import com.backendcase.evaexchange.domain.SellOrder;
import com.backendcase.evaexchange.domain.User;
import com.backendcase.evaexchange.request.BuyOrderRequest;
import com.backendcase.evaexchange.service.BuyOrderServiceImpl;
import com.backendcase.evaexchange.service.SellOrderServiceImpl;
import com.backendcase.evaexchange.service.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BuyOrderController {

    private final BuyOrderServiceImpl buyOrderService;
    private final UserServiceImpl userService;
    private final SellOrderServiceImpl sellOrderService;

    public BuyOrderController(BuyOrderServiceImpl buyOrderService, UserServiceImpl userService, SellOrderServiceImpl sellOrderService) {
        this.buyOrderService = buyOrderService;
        this.userService = userService;
        this.sellOrderService = sellOrderService;
    }

    @PostMapping("buy/order")
    public void giveBuyOrder(@Valid @RequestBody BuyOrderRequest buyOrderRequest){
        User userById = userService.findUserById(buyOrderRequest.getUserId());
        SellOrder sellOrderById = sellOrderService.findSellOrderById(buyOrderRequest.getSellOrderId());
        BuyOrder buyOrder = BuyOrder.builder()
                .user(userById)
                .share(sellOrderById.getShare()).build();
        buyOrderService.giveBuyOrder(buyOrderRequest.getSellOrderId(),buyOrderRequest.getPercent(),buyOrder);

    }

}
