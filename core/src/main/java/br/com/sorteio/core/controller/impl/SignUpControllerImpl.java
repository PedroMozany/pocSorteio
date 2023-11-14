package br.com.sorteio.core.controller.impl;

import br.com.sorteio.core.controller.SignUpController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import br.com.sorteio.core.service.SignUpService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;
import java.io.Serializable;


@Component(immediate = true, service = SignUpController.class)
public class SignUpControllerImpl implements SignUpController,Serializable {

    private static final long serialVersionUID = 1L;
    @Reference
    SignUpService signUpService;

    @Override
    public void creat(SlingHttpServletRequest request) throws ExceptionsParamenter {

        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        String name = request.getParameter("name").toLowerCase();
        String password = request.getParameter("password").toLowerCase();

        valetationSession(session);


        if (name != null && password != null && !name.equals("") && !password.equals("")) {
            try {
                signUpService.save(session, new Admin(name, password));

            } catch (RepositoryException e) {
                throw new ExceptionsParamenter(e.getMessage());
            }
        } else {
            throw new ExceptionsParamenter("Please, check the parameters.");
        }
    }


    @Override
    public void delete(SlingHttpServletRequest request) throws ExceptionsParamenter {

        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        String name = request.getParameter("name").toLowerCase();
        valetationSession(session);

        if (name != null && !name.equals("")) {
            try {
                signUpService.delete(session, name);

            } catch (RepositoryException e) {
                throw new ExceptionsParamenter(e.getMessage());
            }
        } else {
            throw new ExceptionsParamenter("Please, check the parameters.");
        }

    }


    private boolean valetationSession(Session session) throws ExceptionsParamenter {
        if (session == null) {
            throw new ExceptionsParamenter("Session is null.");
        }
        return true;
    }
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

}

