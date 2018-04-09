package com.fundoonotes.bean.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyObjectMapper extends ObjectMapper 
{    
   private static final long serialVersionUID = 1L;

   public MyObjectMapper() {
       this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
   }
}