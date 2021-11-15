package com.fis.offers.customer.service.impl;

import com.fis.offers.customer.enums.OfferStatus;
import com.fis.offers.customer.model.Offer;
import com.fis.offers.customer.populator.Populator;
import com.fis.offers.customer.repository.OfferRepository;
import com.fis.offers.customer.request.OfferData;
import com.fis.offers.customer.response.OfferDetails;
import com.fis.offers.customer.service.OfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class which interacts with  crud operations
 */
@Service
public class OffersServiceImpl implements OfferService<OfferData>
{

   public static final String OFFER_DELETED = "Offer Deleted";

   public static final String OFFER_NOT_FOUND_OR_INACTIVE = "Offer Not Found Or Inactive";

   public static final String OFFER_CREATED = "Offer Created";

   public static final String NOT_FOUND = " not found";

   public static final String OFFER_WITH_ID = "Offer with id ";

   public static final String OFFER_NOT_CREATED = "Offer Not Created";

   private final Logger logger = LogManager.getLogger(OffersServiceImpl.class);

   @Resource
   private OfferRepository offerRepository;

   @Resource
   private Populator<OfferData, Offer> requestToDaoPopulator;

   @Resource
   private Populator<Offer, OfferDetails> daoToResponsePopulator;

   /**
    * @param offer
    * @return
    *
    * creates a offer
    */
   @Override
   public OfferDetails createOffer(OfferData offer)
   {
      Offer offerDao = new Offer();
      OfferDetails offerDetails = new OfferDetails();
      requestToDaoPopulator.populate(offer, offerDao);

      Offer offerResponse = offerRepository.save(offerDao);

      offerDetails.setMessage(offerResponse.getId() != null ? OFFER_CREATED : OFFER_NOT_CREATED);

      return offerDetails;
   }

   /**
    * @param id
    * @return
    *
    * returns a offer if a offer a valid (ie.. Active)
    */
   @Override
   public OfferDetails getOffer(String id)
   {
      OfferDetails response = new OfferDetails();
      Optional<Offer> optionalOfferResponse = offerRepository.findById(id);
      if (optionalOfferResponse.isPresent() && validateOffer(optionalOfferResponse.get()))
      {
         Offer offerResponse = optionalOfferResponse.get();
         offerResponse.setStatus(OfferStatus.ACTIVE);
         daoToResponsePopulator.populate(offerResponse, response);
      }
      else
      {
         response.setMessage(OFFER_NOT_FOUND_OR_INACTIVE);
      }
      return response;
   }

   /**
    * @return
    *
    * returns all offers present in db
    */
   @Override
   public List<OfferDetails> getAllOffers()
   {
      List<OfferDetails> offers = new ArrayList<>();
      offerRepository.findAll().forEach(eachOffer -> {
         OfferDetails response = new OfferDetails();
         if (validateOffer(eachOffer))
         {
            eachOffer.setStatus(OfferStatus.ACTIVE);
         }
         else
         {
            eachOffer.setStatus(OfferStatus.INACTIVE);
         }
         daoToResponsePopulator.populate(eachOffer, response);
         offers.add(response);
      });

      return offers;
   }

   /**
    * @param id
    * @return
    *
    * deletes a offer from db
    */
   @Override
   public OfferDetails removeOffer(String id)
   {
      OfferDetails response = new OfferDetails();
      try
      {
         offerRepository.deleteById(id);
         response.setMessage(OFFER_DELETED);
      }
      catch (EmptyResultDataAccessException ex)
      {
         logger.error(OFFER_WITH_ID + id + NOT_FOUND, ex);
         response.setMessage(OFFER_WITH_ID + id + NOT_FOUND);
      }

      return response;
   }

   /**
    * @param offer
    * @return
    *
    * validates offer whether is in Active or InActive State
    */
   private boolean validateOffer(Offer offer)
   {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      LocalDateTime dateAndTime = LocalDateTime.now();
      if (dateAndTime.isBefore(LocalDateTime.parse(offer.getValidTo(), formatter)) &&
               dateAndTime.isAfter(LocalDateTime.parse(offer.getValidFrom(), formatter)))
      {
         return Boolean.TRUE;
      }
      return Boolean.FALSE;
   }

}
