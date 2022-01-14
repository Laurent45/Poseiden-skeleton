package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.ICrudOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TradeService implements ICrudOperations<Trade> {
    
    private final TradeRepository tradeRepository;

    @Transactional
    @Override
    public Trade saveModel(Trade model) {
        log.info("saveModel(Trade) called, parameter -> bid:" + model);
        return tradeRepository.save(model);
    }

    @Override
    public Trade getModel(int id) throws IdRequestUnknown {
        log.info("getModel(Trade) called, parameter -> id:" + id);
        return tradeRepository.findById(id)
                .orElseThrow(() -> new IdRequestUnknown("id Trade unknown"));
    }

    @Override
    public List<Trade> getAllModels() {
        log.info("getAllModels(Trade) called, parameter -> none");
        return tradeRepository.findAll();
    }

    @Transactional
    @Override
    public Trade updateModel(int id, Trade model) throws IdRequestUnknown {
        log.info("updateModel(Trade) called, parameter -> id:" + id + " bid" +
                ":" + model);
        Optional<Trade> currentBid = tradeRepository.findById(id);
        if (currentBid.isPresent()) {
            return saveModel(model);
        }
        return null;
    }

    @Override
    public void deleteModel(int id) {
        log.info("deleteModel(Trade) called, parameter -> id:" + id);
        tradeRepository.deleteById(id);
    }
}
