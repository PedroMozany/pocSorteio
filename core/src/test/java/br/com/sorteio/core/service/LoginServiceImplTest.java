package br.com.sorteio.core.service;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jcr.RepositoryException;
import java.io.IOException;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LoginServiceImplTest {

    @Mock
    private LoginService loginService = Mockito.mock(LoginService.class);;

    @Mock
    private MockSlingHttpServletRequest request;


    @Test
    void verify() throws ExceptionsParamenter, RepositoryException, IOException {
        Admin admin = new Admin("Pedro", "pedro@hotmail.com");
        Mockito.when(loginService.verify(request,admin)).thenReturn(true);

        try {
            boolean result  = loginService.verify(request,admin);
            Assertions.assertTrue(result);
        } catch (Exception e) {
            Assertions.fail();
        }
    }


    @Test
    void verifyError() throws ExceptionsParamenter, RepositoryException, IOException {
        Admin admin = new Admin("Pedro", "pedro@hotmail.com");
        Mockito.when(loginService.verify(request,admin)).thenThrow(new ExceptionsParamenter("Does not exist"));

        try {
             loginService.verify(request,admin);
            Assertions.fail();
        } catch (ExceptionsParamenter e) {
            Assertions.assertEquals("Does not exist", e.getMessage());
        }
    }
}