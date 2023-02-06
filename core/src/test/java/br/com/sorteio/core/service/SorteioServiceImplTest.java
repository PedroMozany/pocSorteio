package br.com.sorteio.core.service;

import br.com.sorteio.core.models.Client;
import com.google.gson.Gson;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ResourceResolver;
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
import java.nio.charset.StandardCharsets;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SorteioServiceImplTest {
    private static final Gson GSON = new Gson();
    @Mock
    private SorteioService sorteioService;

    @Mock
    ResourceResolver resourceResolver;
    @Mock
    private MockSlingHttpServletRequest request;

    @Mock
    private MockSlingHttpServletResponse response;

    @Mock
    ResourceResolver resolverResolver;

    @BeforeEach
    void setup(AemContext context) {
        MockitoAnnotations.openMocks(this);
        sorteioService = Mockito.mock(SorteioService.class);

        resourceResolver = context.resourceResolver();
        request = context.request();
        response = context.response();
    }

    @Test
    void raffle() throws RepositoryException {
        Client winner = new Client("pedro", "pedro@hotmail.com") ;
        String bodyJson = GSON.toJson(winner);
        request.setContent(bodyJson.getBytes(StandardCharsets.UTF_8));
        Mockito.when(sorteioService.raffle(resolverResolver)).thenReturn(winner);

        try {
          sorteioService.raffle(resolverResolver);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(200,response.getStatus());
        Assertions.assertEquals("",response.getOutputAsString());
    }


}