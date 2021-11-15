package com.fis.offers.customer.populator;

import com.fis.offers.customer.model.Offer;
import com.fis.offers.customer.response.OfferDetails;
import org.springframework.stereotype.Component;

/**
 * Populates data from dao to response object
 */
@Component
public class DaoToResponsePopulator implements Populator<Offer, OfferDetails>
{
   /**
    * @param source
    * @param target
    * populates from source to target
    */
   @Override
   public void populate(Offer source, OfferDetails target)
   {
      target.setId(source.getId());
      target.setDescription(source.getDescription());
      target.setName(source.getName());
      target.setValidFrom(source.getValidFrom());
      target.setValidTo(source.getValidTo());
      target.setStatus(source.getStatus());
   }
}
