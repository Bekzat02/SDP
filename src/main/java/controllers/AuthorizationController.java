package controllers;

import domain.AccessToken;
import domain.CustomerLoginData;
import services.AuthorizationService;
import services.interfaces.IAuthorizationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthorizationController {
    private IAuthorizationService authorizationService;

    public AuthorizationController(){
        authorizationService=new AuthorizationService();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response login(CustomerLoginData data){
        try{
            AccessToken token= authorizationService.authenticateCustomer(data);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
