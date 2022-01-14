package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameService implements ICrudOperations<RuleName> {
    
    private final RuleNameRepository ruleNameRepository;

    @Transactional
    @Override
    public RuleName saveModel(RuleName model) {
        log.info("saveModel(RuleName) called, parameter -> bid:" + model);
        return ruleNameRepository.save(model);
    }

    @Override
    public RuleName getModel(int id) throws IdRequestUnknown {
        log.info("getModel(RuleName) called, parameter -> id:" + id);
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new IdRequestUnknown("id RuleName unknown"));
    }

    @Override
    public List<RuleName> getAllModels() {
        log.info("getAllModels(RuleName) called, parameter -> none");
        return ruleNameRepository.findAll();
    }

    @Transactional
    @Override
    public RuleName updateModel(int id, RuleName model) throws IdRequestUnknown {
        log.info("updateModel(RuleName) called, parameter -> id:" + id + " bid" +
                ":" + model);
        Optional<RuleName> currentBid = ruleNameRepository.findById(id);
        if (currentBid.isPresent()) {
            return saveModel(model);
        }
        return null;
    }

    @Override
    public void deleteModel(int id) {
        log.info("deleteModel(RuleName) called, parameter -> id:" + id);
        ruleNameRepository.deleteById(id);
    }
}
