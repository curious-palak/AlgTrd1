package com.fundoonotes.utility;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

//@ContextConfiguration("classpath:spring-context.xml")

//@Configuration
//@PropertySource(value={"classpath:development.properties"})
//@PropertySource(value="classpath:spring-context.xml", ignoreResourceNotFound=true)

@Component
public class RandomTest
{
   @Value("${development.properties}")
   static String email; 
   
  /* @Autowired
   Dev mailProp;*/
  
   public static void main(String[] args) throws IOException
   {
      Properties prop=new Properties();
      InputStream input = null;
      
      String filename = "development.properties";
      input = Dev.class.getClassLoader().getResourceAsStream(filename);
      System.out.println(filename);
      //System.out.println(input);
      prop.load(input);
      
 
      System.out.println(prop.getProperty("mail.user"));
      System.out.println(prop.getProperty("mail.password"));

      if(input==null){
               System.out.println("Sorry, unable to find " + filename);
          return;
      }
      System.out.println("Check email..." +email);
     
   }
}
