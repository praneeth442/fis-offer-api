package com.fis.offers.customer.exception;

/**
 *  Exception to hold Request validation error
 */
public class BadException extends RuntimeException
{

   public BadException(String message)
   {
      super(message);

   }

   public BadException(String message, Throwable cause)
   {
      super( message, cause);

   }
}
