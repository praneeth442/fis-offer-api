package com.fis.offers.customer.repository;

import com.fis.offers.customer.model.Offer;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data Reposirory for offer
 */
public interface OfferRepository extends CrudRepository<Offer, String>
{
}
