package com.fis.offers.customer.populator;

public interface Populator<S, T>
{
   void populate(S source, T target);
}
