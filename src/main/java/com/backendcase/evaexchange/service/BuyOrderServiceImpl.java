package com.backendcase.evaexchange.service;

import com.backendcase.evaexchange.domain.BuyOrder;
import com.backendcase.evaexchange.domain.PortfolioShare;
import com.backendcase.evaexchange.domain.SellOrder;
import com.backendcase.evaexchange.domain.TradingLogs;
import com.backendcase.evaexchange.domain.User;
import com.backendcase.evaexchange.exception.BadRequestException;
import com.backendcase.evaexchange.repository.BuyOrderRepository;
import com.backendcase.evaexchange.repository.PortfolioShareRepository;
import com.backendcase.evaexchange.repository.SellOrderRepository;
import com.backendcase.evaexchange.repository.TradingLogsRepository;
import com.backendcase.evaexchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BuyOrderServiceImpl {

    private final BuyOrderRepository buyOrderRepository;
    private final TradingLogsRepository tradingLogsRepository;
    private final SellOrderRepository sellOrderRepository;

    private final PortfolioShareRepository portfolioShareRepository;

    private final UserRepository userRepository;

    public BuyOrderServiceImpl(BuyOrderRepository buyOrderRepository, TradingLogsRepository tradingLogsRepository,
            SellOrderRepository sellOrderRepository, PortfolioShareRepository portfolioShareRepository, UserRepository userRepository) {
        this.buyOrderRepository = buyOrderRepository;
        this.tradingLogsRepository = tradingLogsRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.portfolioShareRepository = portfolioShareRepository;
        this.userRepository = userRepository;
    }

    public void giveBuyOrder(Long sellOrderId, BigDecimal percent,BuyOrder buyOrder){
        SellOrder sellOrder = sellOrderRepository.findById(sellOrderId).get();
        BigDecimal totalQuantity = sellOrder.getShareQuantity();
        BigDecimal toBuyQuantity = buyOrder.setShareQuantityGivenQuantity(percent,totalQuantity);
        BigDecimal totalPrice = buyOrder.getShare().getPrice().multiply(toBuyQuantity);
        User user = buyOrder.getUser();
        if(buyOrder.getUser().getPortfolio()==null){
            throw new BadRequestException("You should have registered portfolio to buy shares");
        }
        if(totalPrice.compareTo(user.getBalance()) > 0){
            throw new BadRequestException("You dont have enough balance to buy this share");
        }

        user.setBalance(user.getBalance().subtract(totalPrice));
        userRepository.save(user);
        sellOrder.setShareQuantity(totalQuantity.subtract(toBuyQuantity));
        sellOrderRepository.save(sellOrder);
        buyOrderRepository.save(buyOrder);

        Optional<PortfolioShare> byShareIdAndUserId = portfolioShareRepository.findByShareIdAndUserId(buyOrder.getShare().getId(),
                buyOrder.getUser().getId());

        if (byShareIdAndUserId.isEmpty()){
            PortfolioShare portfolioShare = PortfolioShare.builder()
                    .share(buyOrder.getShare())
                    .portfolio(buyOrder.getUser().getPortfolio())
                    .user(buyOrder.getUser())
                    .quantity(toBuyQuantity).build();
            portfolioShareRepository.save(portfolioShare);
        }
        else{
            PortfolioShare portfolioShare = byShareIdAndUserId.get();
            BigDecimal quantity = portfolioShare.getQuantity();
            BigDecimal updatedQuantity = quantity.add(toBuyQuantity);
            portfolioShare.setQuantity(updatedQuantity);
            portfolioShareRepository.save(portfolioShare);
        }
        TradingLogs tradingLogs = TradingLogs.builder()
                .sellOrder(sellOrder)
                .buyOrder(buyOrder)
                .date(LocalDateTime.now())
                .seller(sellOrder.getUser())
                .buyer(buyOrder.getUser())
                .quantity(toBuyQuantity)
                .totalPrice(totalPrice)
                .build();
        tradingLogsRepository.save(tradingLogs);




    }
}
