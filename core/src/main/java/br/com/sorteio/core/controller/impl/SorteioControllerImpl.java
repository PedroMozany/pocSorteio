package br.com.sorteio.core.controller.impl;

import br.com.sorteio.core.controller.SorteioController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Client;
import br.com.sorteio.core.service.RegisterService;
import br.com.sorteio.core.service.impl.RegisterServiceImpl;
import br.com.sorteio.core.service.SorteioService;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;
import java.io.Serializable;

@Component(immediate = true, service = SorteioController.class)
public class SorteioControllerImpl implements SorteioController, Serializable {

    private static final long serialVersionUID = 1L;

    @Reference
    SorteioService sorteioService;

    @Reference
    RegisterService registerService;

    @Override
    public String raffle(SlingHttpServletRequest request) throws ExceptionsParamenter {
        ResourceResolver resolverResolver = request.getResourceResolver();
        Session session = resolverResolver.adaptTo(Session.class);
        try {
            Client client = sorteioService.raffle(resolverResolver);
            registerService.deleteParticipant(session,client.getEmail());
            return new Gson().toJson(client);

        } catch (RepositoryException e) {
            throw new ExceptionsParamenter(e.getMessage());
        }
    }
}
