package com.backendcase.evaexchange.repository;

import com.backendcase.evaexchange.domain.PortfolioShare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PortfolioShareRepository extends CrudRepository<PortfolioShare,Long> {


    @Query(value = "SELECT * FROM portfolio_share WHERE share_id=?1 AND user_id=?2",nativeQuery = true)
    Optional<PortfolioShare> findByShareIdAndUserId(Long shareId,Long userId);


}
