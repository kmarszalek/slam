package pl.cyfronet.ltos.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import pl.cyfronet.ltos.repository.MockMvcSecurityTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by piotr on 12.07.16.
 */

public class LegacyMethodsTest extends MockMvcSecurityTest {

    @Test
    public void testUser() throws Exception {
        mockMvc.perform( get("/user/get").session(user()) ).andExpect(status().isOk());
    }

    @Test
    public void testIdentity() throws Exception {
        mockMvc.perform( get("/identity/get").session(user()) ).andExpect(status().isOk());
    }

    @Test
    public void testLogout() throws Exception {
        MockHttpSession httpSession = user();
        Assert.assertFalse(httpSession.isInvalid());

        mockMvc.perform( get("/auth/logout").session(httpSession) ).andExpect(redirectedUrl("/"));
        Assert.assertTrue(httpSession.isInvalid());
    }

}
