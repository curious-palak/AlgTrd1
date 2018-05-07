package com.fundoonotes.userservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fundoonotes.exception.CustomResponse;
import com.fundoonotes.exception.EmailIdNotExists;
import com.fundoonotes.exception.IncorrectEmailException;
import com.fundoonotes.exception.InvalidCredentialsException;
import com.fundoonotes.exception.RegistrationValidationException;
import com.fundoonotes.utility.JwtTokenUtility;
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
//@MultipartConfig
public class UserController
{

   @Autowired
   IUserService userService;

   @Autowired
   RegistrationValidation registrationValidation;

   private static Logger logger = Logger.getLogger(UserController.class.getName());

   // Scope is request
   // @Qualifier("customResponse") // if bean id different than variable name
   // @Autowired

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
   public ResponseEntity<CustomResponse> registerUser(@RequestBody UserDTO userDto, BindingResult bindingResult,
         HttpServletRequest request, HttpServletResponse response)
   {
      logger.info("In register after filter...");
      registrationValidation.validate(userDto, bindingResult);

      if (bindingResult.hasErrors()) {

         throw new RegistrationValidationException();
      }

      String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));
      String token = userService.registerUser(userDto, url);

      if (token != null) {
         response.setHeader("Authorization", token);
         logger.info("Successfully Registered..");
      }
      customResponse.setMessage("Successfully registered.");
      customResponse.setStatusCode(1);
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

   @RequestMapping(value = "/activateaccount/{token:.+}", method = RequestMethod.GET)
   public ResponseEntity<CustomResponse> activateAccount(@PathVariable("token") String token,
         HttpServletRequest request)
   {

      userService.activateAccount(token, request);

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

   @RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> loginUser(@RequestBody UserDTO userDto, HttpServletRequest request,
         HttpServletResponse resp)
   {
      logger.info("In login after CORS filter");
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

   @RequestMapping(value = "forgetpassword", method = RequestMethod.POST)
   public ResponseEntity<?> forgotPassword(@RequestBody UserDTO userDto, HttpServletRequest request)
   {
      if (userService.forgotPass(userDto, request) == true) {

         customResponse.setMessage("Link sent to your mail to reset password..");
         customResponse.setStatusCode(1);
         return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

      } else {
         throw new IncorrectEmailException();
      }
   }

   /**
    * <p>
    * This is simple reset password rest API where we confirm token
    * And redirect to Reset password web page {@link RequestMapping @RequestMapping}
    * to mapped rest address.
    * </p>
    * @param token
    * @param request
    * @param response
    * @throws IOException
    */
   @RequestMapping(value = "/resetpassword/{token:.+}", method = RequestMethod.GET)
   public void resetPassword(@PathVariable("token") String token, HttpServletRequest request,
         HttpServletResponse response) throws IOException
   {
      int validateToken = JwtTokenUtility.verifyToken(token);

      if (validateToken == 0) {
         response.sendRedirect("http://localhost:4200/error");
         return;
      }

      /*HttpSession session = request.getSession();
       session.setAttribute("name","sana");*/
      
      response.sendRedirect("http://localhost:4200/resetpassword?token=" + token);
      return;

   }

   /**
    * <p>
    * This is simple API or resetting password
    * </p>
    * 
    * @param token to get user details
    * @param userDto object
    * @param response
    * @return Response Entity with HTTP status and our custom message.
    * @throws IOException
    */

   //@RequestMapping(value = "/resetnewpassword/{token:.+}", method =RequestMethod.POST)
   @RequestMapping(value = "/resetnewpassword", method = RequestMethod.POST)
   public ResponseEntity<CustomResponse> resetPassword(@RequestParam("token") String token,
         @RequestBody UserDTO userDto, HttpServletRequest request, HttpServletResponse response) throws IOException
   {
      /*HttpSession session = request.getSession();
        String name= (String) session.getAttribute("name");*/

      System.out.println("Checkk-> " + token);

      int uId = JwtTokenUtility.verifyToken(token);

      User userData = userService.getUserById(uId);
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

   @RequestMapping(value = "getuser", method = RequestMethod.GET)
   public ResponseEntity<User> getUser(HttpServletRequest request)
   {
      int userId = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
      System.out.println(userId);
      User user = userService.getUserById(userId);
      return new ResponseEntity<User>(user, HttpStatus.OK);
   }

   /**
    * <p> This is simple rest API to upload user profile image
    * {@link RequestMapping @RequestMapping} to mapped rest address.
    * </p>
    * @param uploadProfileImage
    * @param request
    * @return
    * @throws IOException
    * @throws SerialException
    * @throws SQLException
    */
   @RequestMapping(value = "upload", method = RequestMethod.POST)
   public ResponseEntity<CustomResponse> uploadProfileImage(@RequestParam("file") MultipartFile uploadProfileImage,
         HttpServletRequest request) throws IOException, SerialException, SQLException
   {

      int getUid = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));

      System.out.println("Check image->>" + uploadProfileImage.getOriginalFilename());

      if (getUid == 0) {
         customResponse.setMessage("Error uploading");
         customResponse.setStatusCode(300);
         return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.BAD_REQUEST);
      } else {
         userService.uploadImage(uploadProfileImage, getUid);
         customResponse.setMessage("Upload image successfully..");
         customResponse.setStatusCode(200);
         return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.ACCEPTED);
      }
   }
}