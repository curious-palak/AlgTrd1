/*package com.fundoonotes.userservice;

public class Test
{


->>>API for add collaborator.....

@RequestMapping(value = "/addCollaborator", method = RequestMethod.POST)
   public ResponseEntity<Void> createCollaborator(@RequestBody Collaborator collaborator, HttpServletRequest request) {

      ->>>check here
          collaborator.getSharedUser() +collaborator.getOwner()+collaborator.getNote());

      int id = JwtTokenUtility.verifyToken(request.getHeader("Authorization"));
               logger.info("token->>" + request.getHeader("Authorization"));
         
      if (request.getHeader("Authorization").isEmpty()) {
                throw new EmptyToken();
             }
      
      collaboratorService.saveCollaborator(collaborator, id);
      response.setMessage("Collaborator added successfully..");
            response.setStatusCode(200);
            return new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
   }

@Transactional
   public void saveCollaborator(Collaborator collaborator, int id) {

   
      System.out.println(collaborator.getOwner());
      System.out.println(collaborator.getSharedUser());
      User user = userDao.getUserByEmail(collaborator.getSharedUser().getEmailId());
      //System.out.println(user.getEmailId() + " " + user.getId());
      collaborator.setSharedUser(user);
      //System.out.println(collaborator.getOwner().getEmailId() + " " + collaborator.getSharedUser().getEmailId() + " "+ collaborator.getNote());
      collaboratorDao.saveCollaborator(collaborator);
   }


public boolean saveCollaborator(Collaborator collaborator) {
      Session session = sessionFactory.getCurrentSession();
      session.save(collaborator);
      return true;
   }

}
*/