package com.fundoonotes.utility;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter
{
   private static Logger logger = Logger.getLogger(CorsFilter.class.getName());

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException
   {
      response.addHeader("Access-Control-Allow-Origin", "*");

      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
      response.addHeader("Access-Control-Allow-Headers", "Authorization,Content-Type,Accept, X-Requested-With");
      response.addHeader("Access-Control-Allow-Headers", "Authorization");
      response.addHeader("Access-Control-Expose-Headers", "Authorization, Content-Type");
      response.addHeader("Access-Control-Max-Age", "3600");

      logger.info("In CORS filter...");
      filterChain.doFilter(request, response);
   }
}
