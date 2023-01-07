package com.backendcase.evaexchange.service;

import com.backendcase.evaexchange.domain.PortfolioShare;
import com.backendcase.evaexchange.exception.BadRequestException;
import com.backendcase.evaexchange.repository.PortfolioShareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioShareServiceImpl {

    private final PortfolioShareRepository portfolioShareRepository;

    public PortfolioShareServiceImpl(PortfolioShareRepository portfolioShareRepository) {
        this.portfolioShareRepository = portfolioShareRepository;
    }

    public void addPortfolioShares(List<PortfolioShare> portfolioShareList){
        portfolioShareRepository.saveAll(portfolioShareList);
    }

    public PortfolioShare findPortfolioShare(Long shareId,Long userId){
        Optional<PortfolioShare> byShareIdAndUserId = portfolioShareRepository.findByShareIdAndUserId(shareId, userId);
        if(byShareIdAndUserId.isEmpty()){
            throw new BadRequestException("There is no portfolio for user "+userId+" with share "+shareId);
        }
        return byShareIdAndUserId.get();

    }
}
