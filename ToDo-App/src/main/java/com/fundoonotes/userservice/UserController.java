package com.fundoonotes.userservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fundoonotes.utility.RegistrationValidation;

/**
 * <p>
 * This is a Rest Controller for User With
 * {@link RestController @RestController}, we have added all general purpose
 * methods here those method will accept a rest request in JSON and will return
 * a JSON response.
 * </p>
 * <p>
 * The methods are self explanatory we have used <b>{@code @RestController}</b>
 * annotation to point incoming requests to this class, and
 * <b>{@link ResponseBody @ResponseBody}</b> annotation to point incoming
 * requests to appropriate Methods. <b>{@link RequestBody @RequestBody}</b>
 * annotation is used to accept data with request in JSON and Spring
 * ResponseEntity is used to return JSON as response to incoming request.
 * </p>
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 *
 */
@RestController
public class UserController
{

   @Autowired
   IUserService userService;

   @Autowired
   RegistrationValidation registrationValidation;

   /**
    * <p>
    * This rest API for new user registration With
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * </p>
    * 
    * @param user Object to be save
    * @return ResponseEntity with HTTP status and message.
    */
   @RequestMapping(value = "register", method = RequestMethod.POST)
   public ResponseEntity<String> registerUser(@RequestBody UserDTO userDto, BindingResult bindingResult,
         HttpServletRequest request)
   {

      registrationValidation.validate(userDto, bindingResult);
      if (bindingResult.hasErrors()) {

         return new ResponseEntity<String>("Invalid user details.", HttpStatus.CONFLICT);
      }
      
      String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));
      userService.registerUser(userDto, url);

      return new ResponseEntity<String>("Successfully registered.", HttpStatus.OK);
   }
   
   /**<p>
    * This rest API for activating user account
    * {@link RequestMapping @RequestMapping} .
    * </p>
    * 
    * @param randomUUId
    * @param request HttpServletRequest
    * @return
    */
   @RequestMapping(value = "/activateAccount/{randomUUId}", method = RequestMethod.GET)
   public ResponseEntity<Void> activateAccount(@PathVariable("randomUUId") String randomUUId,
         HttpServletRequest request)
   {
      userService.activateAccount(randomUUId, request);
      return new ResponseEntity<Void>(HttpStatus.OK);
   }

   /**
    * <p>
    * This is simple login rest API where validate with valid existing user from
    * DB.
    * </p>
    * 
    * @param user login
    * @param resp HttpServletResponse
    * @return Response Entity.
    */
   @RequestMapping(value = "login", method = RequestMethod.POST)
   public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO, HttpServletRequest request)
   {

      
         UserDTO userObject = userService.loginUser(userDTO);
         if (userObject != null) 
         {
            
            HttpSession session = request.getSession();
            session.setAttribute("userId", userObject);
            return new ResponseEntity<>("Login successfull..", HttpStatus.OK);
         }
         
         return new ResponseEntity<String>("User Not Found..", HttpStatus.NOT_FOUND);
   }

   /**
    * <p>
    * This is simple rest API to get a user by Id
    * </p>
    * 
    * @param userId
    * @return
    */
   @RequestMapping(value = "getUser/{userId}", method = RequestMethod.GET)
   public ResponseEntity<User> getUser(@PathVariable("userId") int userId)
   {

      System.out.println(userId);
      User user = userService.getUserById(userId);
      return new ResponseEntity<User>(user, HttpStatus.OK);
   }

   /**
    * <p>
    * This is simple forgot password rest API where we get user by its email Id
    * and send a link to reset password.
    * </p>
    * 
    * @param user
    * @param req
    * @return
    */
   @RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
   public ResponseEntity<?> forgotPassword(@RequestBody User user, HttpServletRequest request)
   {
      String user1 = user.getEmail();
      String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));
      
      try {
         if (userService.forgotPass(user.getEmail(), request)) {
            return new ResponseEntity<>(HttpStatus.OK);
         }

      }
      catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
      return new ResponseEntity<>(HttpStatus.OK);
   }

   /**<p>This is simple API or resetting password
    * </p>
    * @param user
    * @param request
    * @return
    */
   @RequestMapping(value = "/resetPassword/{randomUUId}", method = RequestMethod.POST)
   public ResponseEntity<String> resetPassword(@RequestBody User user, @PathVariable("randomUUId") String randomUUId, HttpServletRequest request)
   {
     //String email=userService.getUserByEmail(randomUUId);
      userService.resetPassword(user);
      return new ResponseEntity<>("Password reset successfully..", HttpStatus.OK);
   }
  
}
