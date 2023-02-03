package br.com.sorteio.core.controller;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

public interface SignUpController {
    void creat(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter;
    void delete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter;

}
