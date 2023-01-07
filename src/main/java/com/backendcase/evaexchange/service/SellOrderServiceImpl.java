package com.backendcase.evaexchange.service;

import com.backendcase.evaexchange.domain.PortfolioShare;
import com.backendcase.evaexchange.domain.SellOrder;
import com.backendcase.evaexchange.exception.BadRequestException;
import com.backendcase.evaexchange.repository.PortfolioShareRepository;
import com.backendcase.evaexchange.repository.SellOrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SellOrderServiceImpl {

    private final SellOrderRepository sellOrderRepository;
    private final PortfolioShareRepository portfolioShareRepository;

    public SellOrderServiceImpl(SellOrderRepository sellOrderRepository, PortfolioShareRepository portfolioShareRepository) {
        this.sellOrderRepository = sellOrderRepository;
        this.portfolioShareRepository = portfolioShareRepository;
    }


    public void giveSellOrder(BigDecimal percent,SellOrder sellOrder){
        PortfolioShare portfolioShare = portfolioShareRepository.findByShareIdAndUserId(sellOrder.getShare().getId(), sellOrder.getUser().getId()).get();
        BigDecimal totalQuantity = portfolioShare.getQuantity();
        BigDecimal toSellQuantity = sellOrder.setShareQuantityGivenQuantity(percent,totalQuantity);
        portfolioShare.setQuantity(totalQuantity.subtract(toSellQuantity));
        portfolioShareRepository.save(portfolioShare);
        sellOrderRepository.save(sellOrder);
    }

    public List<SellOrder> getSellOrders(){
        return (List<SellOrder>) sellOrderRepository.findAll();
    }

    public SellOrder findSellOrderById(Long sellOrderId){
        Optional<SellOrder> byId = sellOrderRepository.findById(sellOrderId);  if(byId.isEmpty()){
            throw new BadRequestException("There is no sell order with id "+sellOrderId);
        }
        return byId.get();

    }
}
