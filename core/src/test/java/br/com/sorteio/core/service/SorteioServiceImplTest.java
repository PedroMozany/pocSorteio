package br.com.sorteio.core.service;

import br.com.sorteio.core.models.Client;
import br.com.sorteio.core.service.impl.SorteioServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SorteioServiceImplTest {
    private SorteioService sorteioService;

    @Mock
    private ResourceResolver resolverResolver;

    @Mock
    private Resource resource;
    @Mock
    private ValueMap valueMap;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        sorteioService = new SorteioServiceImpl();

    }

    @Test
    void raffle() {
        when(resource.getValueMap()).thenReturn(valueMap);
        when(valueMap.values()).thenReturn(Arrays.asList(List.of("id1", "pedro", "pedro@hotmail.com").toArray()));
        when(resolverResolver.getResource("/content/usergenerated/sorteio")).thenReturn(mock(Resource.class));
        when(resolverResolver.getResource("/content/usergenerated/sorteio").listChildren()).thenReturn(Collections.singletonList(resource).iterator());

        Client actual = null;
        try {
            actual = sorteioService.raffle(resolverResolver);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals("pedro", actual.getName());
        Assertions.assertEquals("pedro@hotmail.com", actual.getEmail());
    }


}