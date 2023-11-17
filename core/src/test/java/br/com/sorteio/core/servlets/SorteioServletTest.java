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

import br.com.sorteio.core.controller.SorteioController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Client;
import br.com.sorteio.core.models.DtoStatus;
import com.google.gson.Gson;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SorteioServletTest {


    private final Gson GSON = new Gson();

    private SorteioServlet sorteioServlet;

    @Mock
    private SorteioController sorteioController;

    private MockSlingHttpServletRequest request;
    private MockSlingHttpServletResponse response;

    @BeforeEach
    void setup(AemContext context) {
        MockitoAnnotations.openMocks(this);
        sorteioServlet = new SorteioServlet();
        sorteioServlet.sorteioController = sorteioController;
        request = context.request();
        response = context.response();
    }

    @Test
    void doGet() throws ExceptionsParamenter {
        Client winner = new Client("pedro", "pedro@hotmail.com");
        String bodyJson = GSON.toJson(winner);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.when(sorteioController.raffle(request)).thenReturn(bodyJson);

        try {
            sorteioServlet.doGet(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(GSON.toJson(winner), response.getOutputAsString());
    }


    @Test
    void doGetRaffleError() throws ExceptionsParamenter {
        DtoStatus dtdContext = new DtoStatus(500, "Erro winner");
        String bodyJson = GSON.toJson(dtdContext);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.doThrow(new ExceptionsParamenter("Erro winner")).when(sorteioController).raffle(Mockito.any());


        try {
            sorteioServlet.doGet(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(bodyJson, response.getOutputAsString());
    }

}
