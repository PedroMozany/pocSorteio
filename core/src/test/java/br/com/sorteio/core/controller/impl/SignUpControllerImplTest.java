package br.com.sorteio.core.controller.impl;

import br.com.sorteio.core.models.Admin;
import br.com.sorteio.core.service.SignUpService;
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

import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SignUpControllerImplTest {


    private SignUpControllerImpl signUpController;


    @Mock
    private SignUpService mockSignUpService;

    @Mock
    private SlingHttpServletRequest mockRequest;

    @Mock
    private ResourceResolver mockResolver;

    @Mock
    private Session mockSession;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        signUpController = new SignUpControllerImpl();
        signUpController.signUpService = mockSignUpService;
    }

    @Test
    void creat() throws RepositoryException {
        Admin expected = new Admin("testName", "testPassword");

        when(mockRequest.getResourceResolver()).thenReturn(mockResolver);
        when(mockResolver.adaptTo(Session.class)).thenReturn(mockSession);
        when(mockRequest.getParameter("name")).thenReturn(expected.getName());
        when(mockRequest.getParameter("password")).thenReturn(expected.getPassword());
        doNothing().when(mockSignUpService).save(any(Session.class), any(Admin.class));

        try {
            signUpController.creat(mockRequest);
        } catch (Exception e) {
            Assertions.fail();
        }

        verify(mockSignUpService).save(any(Session.class), any(Admin.class));
    }


    @Test
    void delete() throws RepositoryException {

        when(mockRequest.getResourceResolver()).thenReturn(mockResolver);
        when(mockResolver.adaptTo(Session.class)).thenReturn(mockSession);
        when(mockRequest.getParameter("name")).thenReturn("testName");

        doNothing().when(mockSignUpService).delete(any(Session.class), anyString());

        try {
            signUpController.delete(mockRequest);
        } catch (Exception e) {
            Assertions.fail();
        }

        verify(mockSignUpService).delete(any(Session.class), anyString());
    }
}