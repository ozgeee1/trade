package com.backendcase.evaexchange.service;

import com.backendcase.evaexchange.domain.Share;
import com.backendcase.evaexchange.exception.BadRequestException;
import com.backendcase.evaexchange.repository.ShareRepository;
import com.backendcase.evaexchange.response.ShareResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ShareServiceImpl {

    private final ShareRepository shareRepository;

    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    public void addAllShares(List<Share> shares){
        shareRepository.saveAll(shares);
    }


    public ShareResponse updateSharePrice(BigDecimal price,Long shareId){
        Optional<Share> byId = shareRepository.findById(shareId);
        if(byId.isEmpty()){
            throw new BadRequestException("There is no share with id "+shareId);
        }
        Share share = byId.get();
        LocalDateTime updatedTime = share.getUpdatedTime();
        LocalDateTime now = LocalDateTime.now();

        long hours = ChronoUnit.HOURS.between(now,updatedTime);
        if(hours==0){
            throw new BadRequestException("The price of the share should be updated on an hourly basis");
        }
        share.setPrice(price);
        share.setUpdatedTime(LocalDateTime.now());
        Share saved = shareRepository.save(share);
        ShareResponse shareResponse = ShareResponse.builder()
                .symbol(saved.getSymbol())
                .price(saved.getPrice())
                .updatedTime(LocalDateTime.now()).build();
        return shareResponse;
    }

    public Share findShareById(Long shareId){
        Optional<Share> byId = shareRepository.findById(shareId);
        if(byId.isEmpty()){
            throw new BadRequestException("There is no share with id "+shareId);
        }
        return byId.get();
    }


}
