package com.fis.offers.customer.service.impl;

import com.fis.offers.customer.model.Offer;
import com.fis.offers.customer.populator.DaoToResponsePopulator;
import com.fis.offers.customer.populator.Populator;
import com.fis.offers.customer.repository.OfferRepository;
import com.fis.offers.customer.request.OfferData;
import com.fis.offers.customer.response.OfferDetails;
import com.fis.offers.customer.service.OfferService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class OffersServiceImplTest
{

   @Mock
   private OfferRepository offerRepository;

   @Mock
   private Populator<OfferData, Offer> requestToDaoPopulator;

   @Mock
   private DaoToResponsePopulator daoToResponsePopulator;

   @Mock
   OfferData offerData;

   @Mock
   Offer offer;

   @InjectMocks
   private OfferService offersService = new OffersServiceImpl();

   Offer createOfferStub()
   {
      Offer offerStub = new Offer();
      offerStub.setId("id");
      offerStub.setName("name");
      offerStub.setDescription("name");
      offerStub.setValidFrom("2021-10-14T18:12:00");
      offerStub.setValidTo("2021-10-14T18:20:00");
      return offerStub;
   }

   @Test
   void createOffer()
   {
      when(offerRepository.save(Mockito.any(Offer.class))).thenReturn(createOfferStub());
      OfferDetails offerDetails = offersService.createOffer(offerData);
      Assert.assertTrue("Offer Created".equalsIgnoreCase(offerDetails.getMessage()));
   }

   @Test
   void createOfferNotCreated()
   {
      when(offerRepository.save(Mockito.any(Offer.class))).thenReturn(new Offer());
      OfferDetails offerDetails = offersService.createOffer(offerData);
      Assert.assertTrue("Offer Not Created".equalsIgnoreCase(offerDetails.getMessage()));
   }

   @Test
   void getOffer()
   {
      Offer offerStub = createOfferStub();
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      LocalDateTime dateAndTime = LocalDateTime.now().plusDays(2);
      offerStub.setValidTo(formatter.format(dateAndTime));
      when(offerRepository.findById(Mockito.any(String.class)))
               .thenReturn(Optional.of(offerStub));
      doCallRealMethod().when(daoToResponsePopulator)
               .populate(Mockito.any(Offer.class), Mockito.any(OfferDetails.class));
      OfferDetails offerDetails = offersService.getOffer("id");
      Assert.assertTrue("id".equalsIgnoreCase(offerDetails.getId()));
   }

   @Test
   void getOfferNO()
   {
      when(offerRepository.findById(Mockito.any(String.class)))
               .thenReturn(Optional.of(createOfferStub()));
      OfferDetails offerDetails = offersService.getOffer("id");
      Assert.assertTrue(
               "Offer Not Found Or Inactive".equalsIgnoreCase(offerDetails.getMessage()));
   }

   @Test
   void getAllOffers()
   {
      when(offerRepository.findAll())
               .thenReturn(Arrays.asList(createOfferStub()));
      doCallRealMethod().when(daoToResponsePopulator)
               .populate(Mockito.any(Offer.class), Mockito.any(OfferDetails.class));
      List offerDetails = offersService.getAllOffers();
      Assert.assertFalse(offerDetails.isEmpty());

   }

   @Test
   void removeOffer()
   {
      doNothing().when(offerRepository).deleteById(Mockito.anyString());
      OfferDetails response = offersService.removeOffer("id");
      Assert.assertTrue("Offer Deleted".equalsIgnoreCase(response.getMessage()));
   }

   @Test
   void removeOffer_Exception()
   {
      doThrow(EmptyResultDataAccessException.class).when(offerRepository)
               .deleteById(Mockito.anyString());
      OfferDetails response = offersService.removeOffer("id");
      Assert.assertTrue("Offer with id id not found".equalsIgnoreCase(response.getMessage()));
   }
}