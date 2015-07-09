package com.griddynamics.devschool.shop.servlet;

import com.griddynamics.devschool.shop.Store;
import com.griddynamics.devschool.shop.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;

/**
 * @author Sergey Korneev
 */
public class StoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/xml");
        Store store = new Store((User) req.getSession().getAttribute("user"));
        try {
            JAXBContext context = JAXBContext.newInstance(Store.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(store, resp.getWriter());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
