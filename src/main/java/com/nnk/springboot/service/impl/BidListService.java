package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class BidListService implements IBidListService {

    private BidListRepository bidListRepository;

    @Override
    @Transactional
    public BidList saveBid(BidList bidList) {
        log.info("saveBid called, parameter -> bid:" + bidList);
        return bidListRepository.save(bidList);
    }

    @Override
    public BidList getBid(int id) throws IdRequestUnknown {
        log.info("getBid called, parameter -> id:" + id);
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IdRequestUnknown("id bid unknown"));
    }

    @Override
    public List<BidList> getAllBids() {
        log.info("getAllBids called, parameter -> none");
        return bidListRepository.findAll();
    }

    @Override
    @Transactional
    public BidList updateBid(int id, BidList bidList) throws IdRequestUnknown {
        log.info("updateBid called, parameter -> id:" + id + " bid:" + bidList);
        Optional<BidList> currentBid = bidListRepository.findById(id);
        if (currentBid.isPresent()) {
            return saveBid(bidList);
        }
        return null;
    }

    @Override
    public void deleteBid(int id) {
        bidListRepository.deleteById(id);
    }
}
