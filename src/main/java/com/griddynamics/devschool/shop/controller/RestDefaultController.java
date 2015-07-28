package com.griddynamics.devschool.shop.controller;

import com.griddynamics.devschool.shop.Store;
import com.griddynamics.devschool.shop.entity.Items;
import com.griddynamics.devschool.shop.entity.User;
import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.jboss.resteasy.annotations.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author Sergey Korneev
 */
@Controller
@Path("/rest")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class RestDefaultController {
    @Autowired
    private Store store;

    @POST
    @Path("/buy")
    public Response buy(@QueryParam("name") String name, @Context HttpServletRequest request) {
        try {
            store.buy(name, getUser(request));
        } catch (AccessDeniedException e) {
            return Response.status(401).build();
        } catch (NotFoundException e) {
            return Response.status(404).build();
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/items")
    public Items list(@Context HttpServletRequest request) {
        Items items = new Items();
        items.setItems(store.getItems(getUser(request)));
        return items;
    }

    @POST
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return Response.ok().build();
    }

    @POST
    @Path("/registration")
    public Response registration(@Form User user, @Context HttpServletRequest request) {
        request.getSession().setAttribute("user", store.registration(user));
        return Response.ok().build();
    }

    private User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
