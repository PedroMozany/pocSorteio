package br.com.sorteio.core.controller.impl;

import br.com.sorteio.core.controller.SignUpController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Client;
import br.com.sorteio.core.service.RegisterService;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.osgi.service.component.annotations.Component;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@Component(immediate = true, service = SignUpController.class)
class RegisterControllerImplTest {


    private RegisterControllerImpl registerController;

    @Mock
    private RegisterService registerService;

    @Mock
    private SlingHttpServletRequest mockRequest;

    @Mock
    private SlingHttpServletResponse mockResponse;

    @Mock
    private ResourceResolver mockResolver;

    @Mock
    private Session mockSession;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registerController = new RegisterControllerImpl();
        registerController.registerService = registerService;
    }

    @Test
    void addParticipant() throws RepositoryException {
        when(mockRequest.getResourceResolver()).thenReturn(mockResolver);
        when(mockResolver.adaptTo(Session.class)).thenReturn(mockSession);
        when(mockRequest.getParameter("name")).thenReturn("testName");
        when(mockRequest.getParameter("email")).thenReturn("testEmail");
        doNothing().when(registerService).addParticipant(any(Session.class), any(Client.class));

        try {
            registerController.addParticipant(mockRequest);
        } catch (ExceptionsParamenter e) {
            Assertions.fail();
        }

        verify(registerService).addParticipant(any(Session.class), any(Client.class));
    }

    @Test
    void deleteParticipant() throws RepositoryException {
        when(mockRequest.getResourceResolver()).thenReturn(mockResolver);
        when(mockResolver.adaptTo(Session.class)).thenReturn(mockSession);
        when(mockRequest.getParameter("email")).thenReturn("testEmail");
        doNothing().when(registerService).deleteParticipant(any(Session.class), anyString());

        try {
            registerController.deleteParticipant(mockRequest, mockResponse);
        } catch (ExceptionsParamenter e) {
            Assertions.fail();
        }

        verify(registerService).deleteParticipant(any(Session.class), anyString());
    }

    @Test
    void deleteAll() throws RepositoryException {
        when(mockRequest.getResourceResolver()).thenReturn(mockResolver);
        when(mockResolver.adaptTo(Session.class)).thenReturn(mockSession);
        doNothing().when(registerService).deleteAll(any(Session.class));

        try {
            registerController.deleteAll(mockRequest);
        } catch (Exception e) {
            Assertions.fail();
        }

        verify(registerService).deleteAll(any(Session.class));

    }

    @Test
    void listParticipant() {
        List<Client> expected = new LinkedList<>();
        expected.add(new Client("testName1", "testEmail1"));
        expected.add(new Client("testName2", "testEmail2"));
        when(mockRequest.getResourceResolver()).thenReturn(mockResolver);
        when(mockResolver.adaptTo(Session.class)).thenReturn(mockSession);
        when(registerService.listParticipant(any(ResourceResolver.class))).thenReturn(expected);

        String actual = null;
        try{
            actual = registerController.listParticipant(mockRequest, mockResponse);
        } catch (Exception e) {
            Assertions.fail();
        }

        assertEquals(actual, new Gson().toJson(expected));
    }
}