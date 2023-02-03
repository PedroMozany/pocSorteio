package br.com.sorteio.core.service;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import org.apache.sling.api.SlingHttpServletRequest;

import javax.jcr.RepositoryException;
import java.io.IOException;

public interface LoginService {
    boolean verify(SlingHttpServletRequest request, Admin adim) throws RepositoryException, ExceptionsParamenter, IOException;

}
