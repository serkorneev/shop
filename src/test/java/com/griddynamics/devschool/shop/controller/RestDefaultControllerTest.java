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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
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
@ContextConfiguration(locations = {"/context/controller-config.xml", "/context/mock-repository-config.xml", "classpath:springmvc-resteasy.xml"})
public class RestDefaultControllerTest {
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
    public void list() throws Exception {
        Item item = mock(Item.class);
        ReflectionTestUtils.setField(item, "id", 1);
        ReflectionTestUtils.setField(item, "name", "ItemName");
        ReflectionTestUtils.setField(item, "price", 10);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        when(store.getItems(null)).thenReturn(items);

        MockHttpServletRequestBuilder request = get("/rest/items");
        request.contentType(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"items\":{\"item\":{\"id\":1,\"name\":\"ItemName\",\"price\":10}}}"));

        verify(store).getItems(null);
    }

    @Test
    public void buyWithAccessDeniedException() throws Exception {
        doThrow(new AccessDeniedException()).when(store).buy("ItemName", null);

        MockHttpServletRequestBuilder request = post("/rest/buy?name=ItemName")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().is(401));

        verify(store).buy("ItemName", null);
    }

    @Test
    public void buyWithNotFoundException() throws Exception {
        doThrow(new NotFoundException()).when(store).buy("ItemName", null);

        MockHttpServletRequestBuilder request = post("/rest/buy?name=ItemName")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().is(404));

        verify(store).buy("ItemName", null);
    }

    @Test
    public void buy() throws Exception {
        MockHttpServletRequestBuilder request = post("/rest/buy?name=ItemName")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().is(200));

        verify(store).buy("ItemName", null);
    }

    @Test
    public void logout() throws Exception {
        MockHttpServletRequestBuilder request = post("/rest/logout")
                .sessionAttr("user", "value");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("user", (Object) null));
    }

    @Test
    public void registration() throws Exception {
        User user = new User();
        when(store.registration(any(User.class))).thenReturn(user);
        MockHttpServletRequestBuilder request = post("/rest/registration")
                .param("name", "username")
                .param("email", "email");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("user", user));

        verify(store).registration(any(User.class));
    }
}
