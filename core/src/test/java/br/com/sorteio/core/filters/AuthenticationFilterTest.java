package br.com.sorteio.core.filters;

import br.com.sorteio.core.controller.LoginController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.DtoStatus;
import com.google.gson.Gson;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class AuthenticationFilterTest {


    private AuthenticationFilter authenticationFilter;

    private MockSlingHttpServletResponse response;

    @Mock
    private LoginController loginController;

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession mockSession;

    @BeforeEach
    public void setUp(AemContext contex) {
        MockitoAnnotations.openMocks(this);
        authenticationFilter = new AuthenticationFilter();
        authenticationFilter.loginController = loginController;
        response = contex.response();
    }


    @Test
    void doFilter() throws ExceptionsParamenter, ServletException, IOException {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:4503/content/raffle/winner.model.json"));
        when(request.getMethod()).thenReturn(HttpConstants.METHOD_GET);
        Mockito.lenient().when(loginController.verify(request, response)).thenReturn(true);

        try {
            authenticationFilter.doFilter(request, response, filterChain);
        } catch (Exception e) {
            Assertions.fail();
        }
        Mockito.verify(filterChain).doFilter(request, response);
    }


    @Test
    public void testDoFilterWithAuthorizedPageAndInvalidAuthentication() throws IOException, ExceptionsParamenter {

        String expected = new Gson().toJson(new DtoStatus(500, "Please check the parameters Authentication"));

        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:4502/content/raffle/winner.model.json"));
        when(request.getMethod()).thenReturn(HttpConstants.METHOD_GET);
        when(request.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute("Authorization")).thenReturn("test");
        doThrow(new ExceptionsParamenter("Please check the parameters Authentication")).when(loginController).verify(any(SlingHttpServletRequest.class), any(SlingHttpServletResponse.class));

        try {
            authenticationFilter.doFilter(request, response, filterChain);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
        Assertions.assertEquals("application/json", response.getContentType());
        Assertions.assertEquals(expected, response.getOutputAsString());
    }


    @Test
    void init() {
    }


    @Test
    void destroy() {
    }
}