package com.fundoonotes.utility;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup
{
   public FetchUrlData getUrlData(String url) throws URISyntaxException, IOException
   {
      //String domain = "www.google.com";
      String domain=null;

      URI uriObject = new URI(url);
      domain = uriObject.getHost();
      //System.out.println("Check Domain->>" +domain);
      
      Document document=org.jsoup.Jsoup.connect(url).get();
      
      String title=null;
      Elements metaOgTitle= document.select("meta[property=og:title]");
    
      if(metaOgTitle!=null) {
         title=metaOgTitle.attr("content");
      }
      else {
         title=document.title();
      }
      
      String imageUrl = null;
      Elements metaOgImage=document.select("meta[property=og:image]");
      if(metaOgImage!=null) {
         imageUrl=metaOgImage.attr("content");
      }
      
      return new FetchUrlData(title, imageUrl, domain);
   }
}
