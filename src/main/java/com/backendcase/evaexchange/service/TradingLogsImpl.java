package com.backendcase.evaexchange.service;

import com.backendcase.evaexchange.domain.TradingLogs;
import com.backendcase.evaexchange.repository.TradingLogsRepository;
import org.springframework.stereotype.Service;

@Service
public class TradingLogsImpl {

    private final TradingLogsRepository tradingLogsRepository;

    public TradingLogsImpl(TradingLogsRepository tradingLogsRepository) {
        this.tradingLogsRepository = tradingLogsRepository;
    }

    public void addTradingLog(TradingLogs tradingLogs){
        tradingLogsRepository.save(tradingLogs);
    }
}
