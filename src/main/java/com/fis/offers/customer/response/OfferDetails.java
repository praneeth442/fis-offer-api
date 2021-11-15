package com.fis.offers.customer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fis.offers.customer.enums.OfferStatus;

/**
 * Response Object
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDetails
{

   private String message;

   private String id;

   private String description;

   private String name;

   private OfferStatus status;

   private String validFrom;

   private String validTo;

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public OfferStatus getStatus()
   {
      return status;
   }

   public void setStatus(OfferStatus status)
   {
      this.status = status;
   }

   public String getValidFrom()
   {
      return validFrom;
   }

   public void setValidFrom(String validFrom)
   {
      this.validFrom = validFrom;
   }

   public String getValidTo()
   {
      return validTo;
   }

   public void setValidTo(String validTo)
   {
      this.validTo = validTo;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }
}
