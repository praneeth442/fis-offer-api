package com.fis.offers.customer.populator;

import com.fis.offers.customer.model.Offer;
import com.fis.offers.customer.request.OfferData;
import org.springframework.stereotype.Component;

/**
 *  populates from request to dao
 */
@Component
public class RequestToDaoPopulator implements Populator<OfferData, Offer>
{
   /**
    * @param source
    * @param target
    *
    *  populates from source to target
    */
   @Override
   public void populate(OfferData source, Offer target)
   {
      target.setId(source.getId());
      target.setDescription(source.getDescription());
      target.setName(source.getName());
      target.setValidFrom(source.getValidFrom());
      target.setValidTo(source.getValidTo());

   }
}
