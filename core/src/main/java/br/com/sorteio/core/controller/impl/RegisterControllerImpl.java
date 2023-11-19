package br.com.sorteio.core.controller.impl;

import br.com.sorteio.core.controller.RegisterController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Client;
import br.com.sorteio.core.service.RegisterService;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Component(immediate = true, service = RegisterController.class)
public class RegisterControllerImpl implements RegisterController, Serializable {
    private static final long serialVersionUID = 1L;
    @Reference
    RegisterService registerService;


    @Override
    public void addParticipant(SlingHttpServletRequest request) throws ExceptionsParamenter {

        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        String name = request.getParameter("name").toLowerCase();
        String email = request.getParameter("email").toLowerCase();
        valetationSession(session);

        try {
            if (name != null && email != null && !name.equals("") && !email.equals("")) {

                registerService.addParticipant(session, new Client(name, email));

            } else {
                throw new ExceptionsParamenter("Please, check the parameters.");
            }
        } catch (RepositoryException e) {
            throw new ExceptionsParamenter(e.getMessage());
        }
    }



    @Override
    public void deleteParticipant(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        String email = request.getParameter("email").toLowerCase();

        try {
            if (email != null) {
                registerService.deleteParticipant(session, email);
            } else {
                throw new ExceptionsParamenter("Please, check the parameters.");
            }
        } catch (RepositoryException | ExceptionsParamenter e) {
            throw new ExceptionsParamenter(e.getMessage());
        }

    }

    @Override
    public void deleteAll(SlingHttpServletRequest request) throws RepositoryException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        registerService.deleteAll(session);

    }


    @Override
    public String listParticipant(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        ResourceResolver resolverResolver = request.getResourceResolver();

        List<Client> list = registerService.listParticipant(resolverResolver);
        return new Gson().toJson(list);
    }


    private boolean valetationSession(Session session) throws ExceptionsParamenter {
        if (session == null) {
            throw new ExceptionsParamenter("Session is null.");
        }
        return true;
    }

}
