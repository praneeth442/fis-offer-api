package com.fis.offers.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fis.offers.customer.enums.OfferStatus;

import javax.persistence.*;

/**
 *  Pojo for data persistence
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer
{

   @Id
   @Column(unique = true)
   private String id;

   @Column
   private String description;

   @Column
   private String name;

   @Transient
   private OfferStatus status;

   @Column(nullable = false)
   private String validFrom;

   @Column(nullable = false)
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

}
