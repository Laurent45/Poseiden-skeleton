package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.RatingRepository;
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
public class RatingService implements ICrudOperations<Rating> {
    
    private final RatingRepository ratingRepository;

    @Override
    @Transactional
    public Rating saveModel(Rating model) {
        log.info("saveModel called, parameter -> Rating:" + model);
        return ratingRepository.save(model);
    }

    @Override
    public Rating getModel(int id) throws IdRequestUnknown {
        log.info("getModel(Rating) called, parameter -> id:" + id);
        return ratingRepository.findById(id)
                .orElseThrow(() -> new IdRequestUnknown("id Rating " +
                        "unknown"));
    }

    @Override
    public List<Rating> getAllModels() {
        log.info("getAllModels(Rating) called, parameter -> none");
        return ratingRepository.findAll();
    }

    @Override
    @Transactional
    public Rating updateModel(int id, Rating model) {
        log.info("updateModel(Rating) called, parameter -> id:" + id + " " +
                "Rating:" + model);
        Optional<Rating> currRating = ratingRepository.findById(id);
        if (currRating.isPresent()) {
            return saveModel(model);
        }
        return null;
    }

    @Override
    public void deleteModel(int id) {
        log.info("deleteModel(Rating) called, parameter -> id:" + id);
        ratingRepository.deleteById(id);
    }
}
