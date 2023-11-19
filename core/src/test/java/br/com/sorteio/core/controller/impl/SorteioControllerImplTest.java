package br.com.sorteio.core.controller.impl;

import br.com.sorteio.core.models.Client;
import br.com.sorteio.core.service.RegisterService;
import br.com.sorteio.core.service.SorteioService;
import br.com.sorteio.core.service.impl.RegisterServiceImpl;
import com.google.gson.Gson;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SorteioControllerImplTest {

    private SorteioControllerImpl sorteioController;

    @Mock
    private SorteioService mockSorteioService;

    @Mock
    private RegisterService registerService;

    @Mock
    private SlingHttpServletRequest mockRequest;

    @Mock
    private ResourceResolver mockResolver;

    @Mock
    private Session mockSession;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sorteioController = new SorteioControllerImpl();
        sorteioController.registerService = registerService;
        sorteioController.sorteioService = mockSorteioService;
    }

    @Test
    void raffle() throws RepositoryException {
        Client expected = new Client( "testName","testEmail");

        when(mockSorteioService.raffle(mockResolver)).thenReturn(expected);
        when(mockRequest.getResourceResolver()).thenReturn(mockResolver);
        when(mockResolver.adaptTo(Session.class)).thenReturn(mockSession);
        doNothing().when(registerService).deleteParticipant(mockSession, "testEmail");

        String actual = null;
        try {
            actual = sorteioController.raffle(mockRequest);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(actual, new Gson().toJson(expected));

    }
}