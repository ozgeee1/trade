package com.backendcase.evaexchange.controller;

import com.backendcase.evaexchange.response.ShareResponse;
import com.backendcase.evaexchange.service.ShareServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ShareController {

    private final ShareServiceImpl shareService;

    public ShareController(ShareServiceImpl shareService) {
        this.shareService = shareService;
    }

    @PostMapping(path = "update/price/{shareId}")
    public ResponseEntity<ShareResponse> updateChangePrice(@RequestParam BigDecimal price,@PathVariable Long shareId){
            return ResponseEntity.ok(shareService.updateSharePrice(price,shareId));



    }
}
