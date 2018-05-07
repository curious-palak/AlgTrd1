/*
public class Api
{
   @RequestMapping(value="getdata",method =RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> urlData(@RequestBody JsoupUrlDTO jsoupurldto ,HttpServletRequest req,@RequestAttribute(name="userId") int userId) 
   {
      List<String> urls=jsoupurldto.getUrls();
      
      for(String url:urls) {
         try {
            
            getDatabyJsoup data=new getDatabyJsoup();
            UrlData info=null;
            
            info = data.getUrlMetaData(url);
            //double insertion happend if clicking same note to update 
            noteService.saveNoteUrl(info,jsoupurldto.getNoteId());  
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return new ResponseEntity<List>(HttpStatus.OK);  
   }

}
*/