package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.IdRequestUnknown;

import java.util.List;

public interface IBidListService {

    /**
     * Create - create a new bid
     * @param bidList new bidList object
     * @return new bid saved
     */
    BidList saveBid(BidList bidList);

    /**
     * Read - get bid by id
     * @param id id bid
     * @return a bidList object
     * @throws IdRequestUnknown ID unknown
     */
    BidList getBid(int id) throws IdRequestUnknown;

    /**
     * Read - get all bids
     * @return a list of all bids
     */
    List<BidList> getAllBids();

    /**
     * Update - update a bid
     * @param id ID's bid
     * @param bidList Bid within new fields
     * @throws IdRequestUnknown ID unknown
     * @return Bid updated
     */
    BidList updateBid(int id, BidList bidList) throws IdRequestUnknown;

    /**
     * Delete - remove a bid by ID
     * @param id ID's bid
     */
    void deleteBid(int id);

}
