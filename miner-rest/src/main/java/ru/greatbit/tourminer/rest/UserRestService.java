package ru.greatbit.tourminer.rest;

import ru.greatbit.tourminer.beans.User;
import ru.greatbit.tourminer.service.exceptions.AuthException;
import ru.greatbit.tourminer.service.services.UserService;
import ru.greatbit.tourminer.service.utils.UserDataUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;


/**
 * Created with IntelliJ IDEA.
 * User: azee
 */
@Component
@Path("/user")
public class UserRestService {
    private final static Logger log = Logger.getLogger(UserRestService.class);

    @Autowired
    UserService userService;

    @Autowired
    UserDataUtils userDataUtils;

    /**
     * Authorise a user by login and password. Sets a cookie.
     * @param login
     * @param pass
     * @param hsr
     * @return
     * @throws Exception
     */
    @GET
    @Path("/authorise")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response authorise(
            @QueryParam("login") final String login,
            @QueryParam("pass") final String pass,
            @Context HttpServletRequest hsr) throws Exception {
        try {
            User user = userService.authenticate(login, pass);
            return Response.ok(user).cookie(new NewCookie(userDataUtils.getSidCookieName(), user.getSid())).build();
        } catch (AuthException e){
            return Response.serverError().status(401).build();
        }
    }

    @GET
    @Path("/sid")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response authoriseBySid(@Context HttpServletRequest hsr) throws Exception {
        try {
            User user = userService.checkSid(hsr);
            return Response.ok(user).build();
        } catch (AuthException e){
            return Response.serverError().status(401).build();
        }
    }


    /**
     * Logout a user
     * @param hsr
     * @return
     * @throws Exception
     */
    @GET
    @Path("/logout")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response logout(@Context HttpServletRequest hsr) throws Exception {
        try {
            User user = userService.checkSid(hsr);
            userService.removeUserCookie(user);
        } catch (AuthException e){
            log.info(e.getMessage());
        }
        return Response.ok().build();
    }

    /**
     * Creates a new Unverified user
     * @param login
     * @param pass
     * @param email
     * @return
     * @throws Exception
     */
    @POST
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(
            @QueryParam("login") final String login,
            @QueryParam("pass") final String pass,
            @QueryParam("email") final String email) throws Exception {
        User user;
        try {
            userService.getUserByName(login);
            userService.getUserByEmail(login);
            user = userService.createUser(login, pass, email);
        } catch (AuthException e){
            return Response.serverError().status(409).build();
        }

        return Response.ok(user).build();
    }

    /**
     * Verify user's email link handler
     * @param id
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{id}/verify")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response verify(@PathParam("id") final String id) throws Exception {
        User user;
        try {
            user = userService.verifyUser(id);
        } catch (AuthException e){
            return Response.serverError().status(401).build();
        }
        return Response.ok(user).build();
    }
}
