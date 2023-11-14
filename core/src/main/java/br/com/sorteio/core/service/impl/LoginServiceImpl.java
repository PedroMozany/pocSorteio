package br.com.sorteio.core.service.impl;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import br.com.sorteio.core.service.LoginService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

@Component(immediate = true, service = LoginService.class)
public class LoginServiceImpl implements LoginService, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String PATH = "/content/usergenerated/login";





    @Override
    public boolean verify(SlingHttpServletRequest request, Admin admin) throws RepositoryException, ExceptionsParamenter, IOException {

        ResourceResolver resolverResolver = request.getResourceResolver();
        Iterator<Resource> authors = resolverResolver.getResource(PATH).listChildren();

        while (authors.hasNext()) {
            Resource resource = authors.next();
            ValueMap prop = resource.getValueMap();
            Object[] aux = prop.values().toArray();
            if (admin.getName().equals(aux[1].toString()) && admin.getPassword().equals(aux[2].toString())) {
                return true;
            }
        }

        throw new ExceptionsParamenter("Does not exist");
    }


}
