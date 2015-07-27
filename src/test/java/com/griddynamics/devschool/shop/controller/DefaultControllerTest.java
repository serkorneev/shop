package com.griddynamics.devschool.shop.controller;

import com.griddynamics.devschool.shop.Store;
import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.entity.User;
import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Sergey Korneev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"/context/controller-config.xml", "/context/mock-repository-config.xml"})
public class DefaultControllerTest {
    @Autowired
    private Store store;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        reset(store);
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/view/index.jsp"));
    }

    @Test
    public void list() throws Exception {
        Item item = new Item();
        item.setName("ItemName");
        item.setPrice(10);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        when(store.getItems(null)).thenReturn(items);

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(forwardedUrl("/WEB-INF/view/list.jsp"))
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attribute("items", items));

        verify(store).getItems(null);
    }

    @Test
    public void buyWithAccessDeniedException() throws Exception {
        doThrow(new AccessDeniedException()).when(store).buy("ItemName", null);

        MockHttpServletRequestBuilder request = get("/buy")
                .param("name", "ItemName");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:registration"));

        verify(store).buy("ItemName", null);
    }

    @Test
    public void buyWithNotFoundException() throws Exception {
        doThrow(new NotFoundException()).when(store).buy("ItemName", null);

        MockHttpServletRequestBuilder request = get("/buy")
                .param("name", "ItemName");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));

        verify(store).buy("ItemName", null);
    }

    @Test
    public void buy() throws Exception {
        MockHttpServletRequestBuilder request = get("/buy")
                .param("name", "ItemName");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));

        verify(store).buy("ItemName", null);
    }

    @Test
    public void logout() throws Exception {
        MockHttpServletRequestBuilder request = get("/logout")
                .sessionAttr("user", "value");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"))
                .andExpect(request().sessionAttribute("user", (Object) null));
    }

    @Test
    public void showRegistrationForm() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(forwardedUrl("/WEB-INF/view/registration.jsp"))
                .andExpect(model().attributeExists("userForm"));
    }

    @Test
    public void registration() throws Exception {
        User user = new User();
        user.setName("Name");
        MockHttpServletRequestBuilder request = post("/registration")
                .sessionAttr("userForm", user);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }
}
