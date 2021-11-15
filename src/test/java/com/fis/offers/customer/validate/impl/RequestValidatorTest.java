package com.fis.offers.customer.validate.impl;

import com.fis.offers.customer.exception.BadException;
import com.fis.offers.customer.request.OfferData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class RequestValidatorTest
{

   @Mock
   private OfferData offerData;

   @InjectMocks
   private RequestValidator requestValidator = new RequestValidator();

   @BeforeEach
   void setUp()
   {
      offerData = new OfferData();
   }

   @Test
   void validateID()
   {
      BadException thrown = Assertions.assertThrows(BadException.class, () -> {
         requestValidator.validate(offerData);
      }, "NumberFormatException was expected");

      Assertions.assertEquals("missing mandatory value for the param : id", thrown.getMessage());
   }

   @Test
   void validateDate()
   {
      offerData.setId("123");
      offerData.setValidFrom("123");
      BadException thrown = Assertions.assertThrows(BadException.class, () -> {
         requestValidator.validate(offerData);
      }, "");

      Assertions.assertEquals(
               "unable to parse the value : validFrom expected format : 2021-11-14T18:20",
               thrown.getMessage());
   }

   @Test
   void validateTwoDates()
   {
      offerData.setId("123");
      offerData.setValidFrom("2021-11-14T18:21:00");
      offerData.setValidTo("2021-11-14T18:20:00");
      BadException thrown = Assertions.assertThrows(BadException.class, () -> {
         requestValidator.validate(offerData);
      }, "NumberFormatException was expected");

      Assertions.assertEquals("validTo value cannot be past compared to validFrom",
               thrown.getMessage());
   }

}