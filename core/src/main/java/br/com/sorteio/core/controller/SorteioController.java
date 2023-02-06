package br.com.sorteio.core.controller;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;

public interface SorteioController {
    String raffle(SlingHttpServletRequest request) throws ExceptionsParamenter;
}
