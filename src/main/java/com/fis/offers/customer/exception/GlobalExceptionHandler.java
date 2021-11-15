package com.fis.offers.customer.exception;

import com.fis.offers.customer.response.OfferDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * This class handles known exception
 */
@ControllerAdvice
public class GlobalExceptionHandler
{

   /**
    * @param e
    * @return
    *
    * returns 404
    * with a error message
    */
   @ResponseBody
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(value = BadException.class)
   public OfferDetails handleBadRequestException(BadException e)
   {
      OfferDetails offerDetails = new OfferDetails();
      offerDetails.setMessage(e.getMessage());
      return offerDetails;
   }

   /**
    * @param e
    * @return
    *
    * Handles unknown run time exceptions
    */
   @ResponseBody
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(value = Exception.class)
   public OfferDetails handleUnKnown(Exception e)
   {
      OfferDetails offerDetails = new OfferDetails();
      offerDetails.setMessage(e.getMessage());
      return offerDetails;
   }
}