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

import br.com.sorteio.core.controller.RegisterController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Client;
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

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class RegisterServletTest {


    private static final Gson GSON = new Gson();

    private RegisterServlet registerServlet;

    @Mock
    private RegisterController registerController;

    private MockSlingHttpServletRequest request;

    private MockSlingHttpServletResponse response;

    @BeforeEach
    void setup(AemContext context) {
        MockitoAnnotations.openMocks(this);
        registerServlet = new RegisterServlet();
        registerServlet.registerController = registerController;
        request = context.request();
        response = context.response();
    }


    @Test
    void doPost() {
        DtoStatus dtdContext = new DtoStatus(200, "Success!!");
        String bodyJson = GSON.toJson(dtdContext);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));

        try {
            registerServlet.doPost(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(GSON.toJson(dtdContext), response.getOutputAsString());
    }


    @Test
    void doPostError() throws ExceptionsParamenter {
        DtoStatus dtdContext = new DtoStatus(500, "Please, check the parameters.");
        String bodyJson = GSON.toJson(dtdContext);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.doThrow(new ExceptionsParamenter("Please, check the parameters.")).when(registerController).addParticipant(Mockito.any());

        try {
            registerServlet.doPost(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(GSON.toJson(dtdContext), response.getOutputAsString());
    }


    @Test
    void doGet() {
        List<Client> authorList = new LinkedList<>();
        authorList.add(new Client("pedro","pedro@hotmail.com"));
        authorList.add(new Client("bruna","bruna@hotmail.com"));
        String bodyJson = GSON.toJson(authorList);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.when(registerController.listParticipant(request,response)).thenReturn(bodyJson);

        try {
            registerServlet.doGet(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(bodyJson, response.getOutputAsString());
    }


    @Test
    void doPut() throws ExceptionsParamenter {
        request.addRequestParameter("email", "pedro@hotmail.com");
        DtoStatus dtdContext = new DtoStatus(200, "Success!!");
        String bodyJson = GSON.toJson(dtdContext);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.doNothing().when(registerController).deleteParticipant(request,response);

        try {
            registerServlet.doPut(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(bodyJson, response.getOutputAsString());
    }


    @Test
    void doDelete() throws RepositoryException {
        request.addRequestParameter("email", "pedro@hotmail.com");
        DtoStatus dtdContext = new DtoStatus(200, "Success!!");
        String bodyJson = GSON.toJson(dtdContext);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.doNothing().when(registerController).deleteAll(request);

        try {
            registerServlet.doDelete(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(bodyJson, response.getOutputAsString());
    }

}
