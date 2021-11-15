package com.fis.offers.customer.controller;

import com.fis.offers.customer.request.OfferData;
import com.fis.offers.customer.response.OfferDetails;
import com.fis.offers.customer.service.OfferService;
import com.fis.offers.customer.validate.Validation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Controller Class
 */
@RestController
public class OffersController
{

   @Resource
   private OfferService<OfferData> offerService;

   @Resource
   private Validation<OfferData> requestValidator;

   /**
    * @param offer
    * @return
    *
    * creates an offer
    */
   @PostMapping(path = "/createoffer")
   public OfferDetails createOffer(@RequestBody OfferData offer)
   {
      requestValidator.validate(offer);
     return offerService.createOffer(offer);
   }

   /**
    * @param id
    * @return
    *
    * gets a offer
    */
   @GetMapping(path = "/offer/{id}")
   public OfferDetails getOffer(@PathVariable("id") String id)
   {
      return offerService.getOffer(id);
   }

   /**
    * @return
    *
    * returns all offers
    */
   @GetMapping(path = "/offers")
   public List<OfferDetails> getOffers()  {
      return offerService.getAllOffers();
   }

   /**
    * @param id
    * @return
    *
    * deletes an offer from data base
    */
   @DeleteMapping(path = "/removeoffer/{id}")
   public OfferDetails removeOffer (@PathVariable(value = "id" )  String id)
   {
      return offerService.removeOffer(id);
   }
}
