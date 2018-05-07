/*
public class TestJsoup
{
   public class UrlData
   {
      private int id;
      
      private int nodeId;
      
      public int getNodeId()
      {
         return nodeId;
      }

      public void setNodeId(int nodeId)
      {
         this.nodeId = nodeId;
      }

      private String title;
      
      private String imageUrl;
     
      private String domain;

      
      public UrlData()
      {
        
        
      }

      public  UrlData(String title,String imageUrl, String domain){
         this.title=title;
         this.imageUrl=imageUrl;
         this.domain=domain;
      }
      
      public int getId()
      {
         return id;
      }

      public void setId(int id)
      {
         this.id = id;
      }

      public String getTitle()
      {
         return title;
      }

      public void setTitle(String title)
      {
         this.title = title;
      }

      public String getImageUrl()
      {
         return imageUrl;
      }

      public void setImageUrl(String imageUrl)
      {
         this.imageUrl = imageUrl;
      }

      public String getDomain()
      {
         return domain;
      }

      public void setDomain(String domain)
      {
         this.domain = domain;
      }
      
      
   }
   _______________________________________________________

   public class getDatabyJsoup
   {
     
      public UrlData getUrlMetaData(String url) throws IOException {

         String title = null;
         String imageUrl = null;
         String domain=null;
         try {
            URI uri=new URI(url);
            domain=uri.getHost();
            
         } catch (URISyntaxException e) {
           
            e.printStackTrace();
         }
         try{
         Document document = Jsoup.connect(url).get();
        
         Elements metaOgTitle = document.select("meta[property=og:title]");
         
         if (metaOgTitle != null) {
            title = metaOgTitle.attr("content");

            if (title == null) {
               title = document.title();

            }
         }
         
            Elements metaOgImage = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            Element image = metaOgImage.get(0);
                 
           if (image != null) {
              imageUrl = image.attr("src");         
           }


         }catch(Exception e) {
            e.printStackTrace();
         }finally {
            
         }
         
         

              return new UrlData(title, imageUrl,domain);
      }
      
   }
}
*/