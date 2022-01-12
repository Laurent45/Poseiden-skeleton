package com.nnk.springboot.service;

import com.nnk.springboot.exception.IdRequestUnknown;

import java.util.List;

public interface ICrudOperations<T> {
    /**
     * Create - create a new model
     * @param model new model object
     * @return new model saved
     */
    T saveModel(T model);

    /**
     * Read - get model by id
     * @param id id model
     * @return a model object
     * @throws IdRequestUnknown ID unknown
     */
    T getModel(int id) throws IdRequestUnknown;

    /**
     * Read - get all models
     * @return a list of all models
     */
    List<T> getAllModels();

    /**
     * Update - update a model
     * @param id ID's model
     * @param model Model within new fields
     * @throws IdRequestUnknown ID unknown
     * @return Model updated
     */
    T updateModel(int id, T model) throws IdRequestUnknown;

    /**
     * Delete - remove a model by ID
     * @param id ID's model
     */
    void deleteModel(int id);
}
