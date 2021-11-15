package com.fis.offers.customer.service;

import com.fis.offers.customer.response.OfferDetails;

import java.util.List;

public interface OfferService<T>
{
   OfferDetails createOffer(T offer);

   OfferDetails getOffer(String id);

   List<OfferDetails> getAllOffers();

   OfferDetails removeOffer(String id);
}
