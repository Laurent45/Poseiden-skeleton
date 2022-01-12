package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointService implements ICrudOperations<CurvePoint> {

    private final CurvePointRepository curvePointRepository;

    @Override
    @Transactional
    public CurvePoint saveModel(CurvePoint model) {
        log.info("saveModel called, parameter -> curvePoint:" + model);
        return curvePointRepository.save(model);
    }

    @Override
    public CurvePoint getModel(int id) throws IdRequestUnknown {
        log.info("getModel(CurvePoint) called, parameter -> id:" + id);
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new IdRequestUnknown("id CurvePoint " +
                        "unknown"));
    }

    @Override
    public List<CurvePoint> getAllModels() {
        log.info("getAllModels(CurvePoint) called, parameter -> none");
        return curvePointRepository.findAll();
    }

    @Override
    @Transactional
    public CurvePoint updateModel(int id, CurvePoint model) {
        log.info("updateModel(CurvePoint) called, parameter -> id:" + id + " " +
                "curvePoint:" + model);
        Optional<CurvePoint> currCurvePoint = curvePointRepository.findById(id);
        if (currCurvePoint.isPresent()) {
            return saveModel(model);
        }
        return null;
    }

    @Override
    public void deleteModel(int id) {
        log.info("deleteModel(CurvePoint) called, parameter -> id:" + id);
        curvePointRepository.deleteById(id);
    }
}
