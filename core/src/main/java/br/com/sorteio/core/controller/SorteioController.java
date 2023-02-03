package br.com.sorteio.core.controller;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

public interface SorteioController {
    String raffle(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter;
}
