package com.fundoonotes.utility;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtility
{
   /*The JWT signature algorithm we will be using to sign the token*/
   private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

   private static String key = "YSgfdtkkYY";

   public static String generateToken(int id)
   {
      /*get current time and date*/
      long currentTime = System.currentTimeMillis();
      Date startDate = new Date(currentTime);

      /*Token active One hour from now*/
      Date expireDate = new Date(currentTime + 24 * 60 * 60 * 1000);

      /*Let's set the JWT Claim*/
      JwtBuilder builder = Jwts.builder().setId(Integer.toString(id)).signWith(signatureAlgorithm, key)
            .setIssuedAt(startDate).setExpiration(expireDate);
      String generateToken = builder.compact();
      return generateToken;
   }

   public static int verifyToken(String header)
   {
      int id = 0;
      Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(header).getBody();
      id = Integer.parseInt(claims.getId());
      System.out.println("ID: " + claims.getId());
      System.out.println("Expiration: " + claims.getExpiration());
      return id;
   }
}
