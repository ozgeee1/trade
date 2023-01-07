package com.backendcase.evaexchange.service;

import com.backendcase.evaexchange.domain.Portfolio;
import com.backendcase.evaexchange.domain.User;
import com.backendcase.evaexchange.exception.BadRequestException;
import com.backendcase.evaexchange.repository.PortfolioRepository;
import com.backendcase.evaexchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PorfolioServiceImpl {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    public PorfolioServiceImpl(PortfolioRepository portfolioRepository, UserRepository userRepository) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
    }

    public Portfolio createPortfolioForUser(User user){
        Optional<Portfolio> optPortfolio = portfolioRepository.findByUserId(user.getId());
        if(optPortfolio.isPresent()){
           throw new BadRequestException("This user already has portfolio.");
        }
        Portfolio portfolio = new Portfolio();
        portfolio.setUser(user);
        portfolioRepository.save(portfolio);
        user.setPortfolio(portfolio);
        User saved = userRepository.save(user);
        return saved.getPortfolio();
    }
}
