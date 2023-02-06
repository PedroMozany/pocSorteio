package br.com.sorteio.core.controller;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;

public interface SignUpController {
    void creat(SlingHttpServletRequest request) throws ExceptionsParamenter;
    void delete(SlingHttpServletRequest request) throws ExceptionsParamenter;

}
