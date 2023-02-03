package br.com.sorteio.core.controller;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

public interface LoginController {
    void login(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter;
    boolean verify(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter;
    void logout(SlingHttpServletRequest request, SlingHttpServletResponse response);
}
