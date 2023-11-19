package br.com.sorteio.core.controller.impl;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import br.com.sorteio.core.service.JWTokenService;
import br.com.sorteio.core.service.LoginService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginControllerImplTest {


    private LoginControllerImpl loginController;

    @Mock
    private LoginService loginService;

    @Mock
    private JWTokenService jwTokenService;

    @Mock
    private SlingHttpServletRequest mockRequest;
    @Mock
    private SlingHttpServletResponse mockResponse;

    @Mock
    private HttpSession mockSession;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginController = new LoginControllerImpl();
        loginController.loginService = loginService;
        loginController.jwTokenService = jwTokenService;
    }


    @Test
    void login() throws ExceptionsParamenter, RepositoryException, IOException, NoSuchAlgorithmException, InvalidKeyException {
        Admin admin = new Admin("name", "testEmail");

        when(mockRequest.getParameter("name")).thenReturn(admin.getName());
        when(mockRequest.getParameter("password")).thenReturn(admin.getPassword());
        when(loginService.verify(eq(mockRequest), any(Admin.class))).thenReturn(true);
        doNothing().when(jwTokenService).addAuthentication(eq(mockRequest), any(Admin.class));

        try {
            loginController.login(mockRequest);
        } catch (Exception e) {
            Assertions.fail();
        }

        Mockito.verify(loginService).verify(eq(mockRequest), any(Admin.class));
        Mockito.verify(jwTokenService).addAuthentication(eq(mockRequest), any(Admin.class));
    }

    @Test
    void verify() throws NoSuchAlgorithmException, IOException, ExceptionsParamenter, RepositoryException {

        when(mockRequest.getSession()).thenReturn(this.mockSession);
        when(jwTokenService.getAuthentication(any(HttpSession.class), any(SlingHttpServletResponse.class))).thenReturn("testName testPassword");
        when(loginService.verify(mockRequest, new Admin("testName","testPassword"))).thenReturn(true);

        try{
            loginController.verify(mockRequest,mockResponse);
        } catch (Exception e) {
            Assertions.fail();
        }

        Mockito.verify(jwTokenService).getAuthentication(any(HttpSession.class),eq(mockResponse));
        Mockito.verify(loginService).verify(mockRequest, new Admin("testName","testPassword"));

    }

    @Test
    void logout() {
        when(mockRequest.getSession()).thenReturn(mockSession);

        try{
            loginController.logout(mockRequest,mockResponse);
        }catch (Exception e) {
            Assertions.fail();
        }

        Mockito.verify(mockSession).invalidate();
    }

}


