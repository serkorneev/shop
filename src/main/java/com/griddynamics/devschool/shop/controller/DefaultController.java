package com.griddynamics.devschool.shop.controller;

import com.griddynamics.devschool.shop.Store;
import com.griddynamics.devschool.shop.entity.Items;
import com.griddynamics.devschool.shop.entity.User;
import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.xml.bind.*;
import java.io.IOException;

/**
 * @author Sergey Korneev
 */
@Controller
public class DefaultController {
    private Store store;

    @Autowired
    public void setStore(Store store) {
        this.store = store;
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping("/buy")
    public String buy(HttpServletRequest request) {
        try {
            store.buy(request.getParameter("name"), getUser(request));
        } catch (AccessDeniedException e) {
            return "redirect:registration";
        } catch (NotFoundException e) {
            return "redirect:list";
        }

        return "redirect:list";
    }

    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("items", store.getItems(getUser(request)));
        return "list";
    }

    @RequestMapping("/items.xml")
    public void itemsXML(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/xml");

        Items items = new Items();
        items.setItems(store.getItems(getUser(request)));
        try {
            JAXBContext context = JAXBContext.newInstance(Items.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(items, response.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, HttpServletRequest request) {
        request.getSession().setAttribute("user", user);
        return "redirect:list";
    }

    private User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
