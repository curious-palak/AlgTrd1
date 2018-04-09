package com.fundoonotes.userservice;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.exception.CustomResponse;
import com.fundoonotes.exception.EmailIdNotExists;
import com.fundoonotes.exception.IncorrectEmailException;
import com.fundoonotes.exception.InvalidCredentialsException;
import com.fundoonotes.exception.RegistrationValidationException;
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
/* @CrossOrigin(origins = "http://localhost:4200") */
@RestController
/* @RequestMapping({"/api"}) */
public class UserController
{

   @Autowired
   IUserService userService;

   @Autowired
   RegistrationValidation registrationValidation;

   private static Logger logger = Logger.getLogger(UserController.class.getName());

   //Scope is request
   //@Qualifier("customResponse")  // if bean id different than variable name
   //@Autowired
   
   private CustomResponse customResponse = new CustomResponse();

   /**
    * <p>
    * This rest API for new user registration With
    * {@link RequestMapping @RequestMapping} to mapped rest address
    * </p>
    * 
    * @param userDto Object
    * @param bindingResult binds the error message
    * @param request
    * @return ResponseEntity with HTTP status and message.
    */

   @PostMapping(value = "register")
   // @RequestMapping(value = "register", method = RequestMethod.POST)
   public ResponseEntity<CustomResponse> registerUser(@RequestBody UserDTO userDto, BindingResult bindingResult,
         HttpServletRequest request)
   {
      registrationValidation.validate(userDto, bindingResult);

      if (bindingResult.hasErrors()) {

         throw new RegistrationValidationException();
      }

      String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));

      userService.registerUser(userDto, url);
      customResponse.setMessage("Successfully registered.");
      customResponse.setStatusCode(1);
      logger.info("Successfully Registered..");
      return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
   }

   /**  
    * <p>
    * This rest API for activating user account
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * </p>
    * 
    * @param randomUUId to get user
    * @param request HttpServletRequest
    * @return ResponseEntity with HTTP status and message.
    */

   @RequestMapping(value = "/activateAccount/{randomUUId}", method = RequestMethod.GET)
   public ResponseEntity<CustomResponse> activateAccount(@PathVariable("randomUUId") String randomUUId,
         HttpServletRequest request)
   {
      userService.activateAccount(randomUUId, request);

      customResponse.setMessage("Account activated successfully..");
      customResponse.setStatusCode(1);

      logger.info("Account activated successfully..");
      return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
   }

   /**
    * <p>
    * This is simple login rest API where validate user with valid existing user
    * from DB.{@link RequestMapping @RequestMapping} to mapped rest address.
    * </p>
    * 
    * @param userDto object to get login details
    * @param request HttpServletRequest to get session
    * @return Response Entity with HTTP status our custom message.
    */

   @RequestMapping(value = "login", method = RequestMethod.POST)
   public ResponseEntity<?> loginUser(@RequestBody UserDTO userDto, HttpServletRequest request,
         HttpServletResponse resp)
   {
      String token = userService.loginUser(userDto);
      if (token != null) {
         resp.setHeader("Authorization", token);
         customResponse.setMessage("Login successfully");
         customResponse.setStatusCode(200);
         return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
      } else {
         throw new InvalidCredentialsException();
      } 
   }

   /**
    * <p>
    * This is simple forgot password rest API where we get user by its email Id
    * and send a link to reset password. {@link RequestMapping @RequestMapping}
    * to mapped rest address.
    * </p>
    * 
    * @param userDto
    * @param request
    * @return Response Entity with HTTP status and our custom message.
    */

   @RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
   public ResponseEntity<?> forgotPassword(@RequestBody UserDTO userDto, HttpServletRequest request)
   {
      CustomResponse response = new CustomResponse();

      if (userService.forgotPass(userDto, request) == true) {
         response.setMessage("Link sent to your mail to reset password..");

         response.setStatusCode(1);
         return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
      } else {
         throw new IncorrectEmailException();
      }
   }

   /**
    * <p>
    * This is simple API or resetting password
    * </p>
    * 
    * @param randomUUId to get user details
    * @param userDto object
    * @return Response Entity with HTTP status and our custom message.
    */

   @RequestMapping(value = "/resetPassword/{randomUUId}", method = RequestMethod.POST)
   public ResponseEntity<CustomResponse> resetPassword(@PathVariable("randomUUId") String randomUUId,
         @RequestBody UserDTO userDto,HttpServletRequest request)
   {
      
      User userData = userService.getEmailByUUID(randomUUId);
      if (userData != null) {

         if (userService.resetPassword(userData, userDto) == true) {

            customResponse.setMessage("Password reset successfully");
            customResponse.setStatusCode(200);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
         } else {
            throw new RuntimeException();
         }

      } else {
         throw new EmailIdNotExists();
      }
   }

   /**
    * <p>
    * This is simple rest API to get a user by Id
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * </p>
    * 
    * @param userId
    * @return Response Entity with HTTP status and message.
    */

   @RequestMapping(value = "getUser/{userId}", method = RequestMethod.GET)
   public ResponseEntity<User> getUser(@PathVariable("userId") int userId)
   {
      
      System.out.println(userId);
      User user = userService.getUserById(userId);
      return new ResponseEntity<User>(user, HttpStatus.OK);
   }
}
