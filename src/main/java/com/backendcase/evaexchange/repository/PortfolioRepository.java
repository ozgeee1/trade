package com.backendcase.evaexchange.repository;

import com.backendcase.evaexchange.domain.Portfolio;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PortfolioRepository  extends CrudRepository<Portfolio,Long> {


    Optional<Portfolio> findByUserId(Long userId);
}
