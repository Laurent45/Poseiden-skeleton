package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.ICrudOperations;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class BidListService implements ICrudOperations<BidList> {

    private BidListRepository bidListRepository;

    @Transactional
    @Override
    public BidList saveModel(BidList model) {
        log.info("saveModel(BidList) called, parameter -> bid:" + model);
        return bidListRepository.save(model);
    }

    @Override
    public BidList getModel(int id) throws IdRequestUnknown {
        log.info("getModel(BidList) called, parameter -> id:" + id);
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IdRequestUnknown("id bidList unknown"));
    }

    @Override
    public List<BidList> getAllModels() {
        log.info("getAllModels(BidList) called, parameter -> none");
        return bidListRepository.findAll();
    }

    @Transactional
    @Override
    public BidList updateModel(int id, BidList model) throws IdRequestUnknown {
        log.info("updateModel(BidList) called, parameter -> id:" + id + " bid" +
                ":" + model);
        Optional<BidList> currentBid = bidListRepository.findById(id);
        if (currentBid.isPresent()) {
            return saveModel(model);
        }
        return null;
    }

    @Override
    public void deleteModel(int id) {
        log.info("deleteModel(BidList) called, parameter -> id:" + id);
        bidListRepository.deleteById(id);
    }
}
