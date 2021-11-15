package com.fis.offers.customer.validate.impl;

import com.fis.offers.customer.exception.BadException;
import com.fis.offers.customer.request.OfferData;
import com.fis.offers.customer.service.impl.OffersServiceImpl;
import com.fis.offers.customer.validate.Validation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * validates request data
 */
@Component
public class RequestValidator implements Validation<OfferData>
{

   Logger logger = LogManager.getLogger(OffersServiceImpl.class);

   /**
    * @param request validates all request mandatory params
    */
   @Override
   public void validate(OfferData request)
   {
      validateParam(request.getId(), "id");
      validateDate(request.getValidFrom(), "validFrom");
      validateDate(request.getValidTo(), "validTo");
      validateDateRange(request.getValidFrom(), request.getValidTo());
   }

   /**
    * @param value
    * @param param validates the date whether its in expected format(2021-11-14T18:20)
    *              throws BadException if validation fails
    */
   private void validateDate(String value, String param)
   {
      validateParam(value, param);
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      try
      {
         LocalDateTime.parse(value, formatter.withResolverStyle(ResolverStyle.STRICT));
      }
      catch (Exception e)
      {
         logger.error("unable to parse the value : " + param + e);
         throw new BadException(
                  "unable to parse the value : " + param + " expected format : 2021-11-14T18:20",
                  e);
      }

   }

   /**
    * @param value
    * @param param validates the mandatory param
    *              throws BadException if validation fails
    */
   private void validateParam(String value, String param)
   {
      if (StringUtils.isEmpty(value))
      {
         throw new BadException("missing mandatory value for the param : " + param);
      }
   }

   /**
    * @param from
    * @param to   validates whether from date is always less than equal to to date
    *             throws BadException if validation fails
    */
   private void validateDateRange(String from, String to)
   {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      if (LocalDateTime.parse(to, formatter).isBefore(LocalDateTime.parse(from, formatter)))
      {
         throw new BadException("validTo value cannot be past compared to validFrom");
      }
   }
}
