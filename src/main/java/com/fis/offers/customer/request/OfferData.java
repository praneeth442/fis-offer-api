package com.fis.offers.customer.request;

/**
 * Request Object
 */
public class OfferData
{

   private String id;

   private String description;

   private String name;

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

}
