/*
 *  Copyright 2018 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package br.com.sorteio.core.servlets;

import br.com.sorteio.core.controller.LoginController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.DtoStatus;
import com.google.gson.Gson;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LoginServletTest {


    private static final Gson GSON = new Gson();

    @Mock
    private LoginServlet loginServlet;

    @Mock
    private LoginController loginController;
    @Mock
    private MockSlingHttpServletRequest request;
    @Mock
    private MockSlingHttpServletResponse response;

    @BeforeEach
    void setup(AemContext context) {
        MockitoAnnotations.openMocks(this);
        loginServlet = new LoginServlet(loginController);

        request = context.request();
        response = context.response();
    }


    @Test
    void doPostLogin() {
        DtoStatus dtdContext = new DtoStatus(200, "Success!!");
        String bodyJson = GSON.toJson(dtdContext);

        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));

        try {
            loginServlet.doPost(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(GSON.toJson(dtdContext), response.getOutputAsString());
    }

    @Test
    void doPostLoginNotExist() throws ExceptionsParamenter {
        DtoStatus dtdContext = new DtoStatus(500, "Does not exist");
        String bodyJson = GSON.toJson(dtdContext);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.doThrow(new ExceptionsParamenter("Does not exist")).when(loginController).login(Mockito.any());

        try {
            loginServlet.doPost(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
        Assertions.assertEquals(GSON.toJson(dtdContext), response.getOutputAsString());
    }


    @Test
    void doLogout() {
        DtoStatus dtdContext = new DtoStatus(200, "Success!!");
        String bodyJson = GSON.toJson(dtdContext);

        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));

        try {
            loginServlet.doDelete(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(GSON.toJson(dtdContext), response.getOutputAsString());
    }


}
