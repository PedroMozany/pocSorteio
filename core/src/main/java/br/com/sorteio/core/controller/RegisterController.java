package br.com.sorteio.core.controller;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.jcr.RepositoryException;

public interface RegisterController {
    void addParticipant(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter;
    void deleteParticipant(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter;
    void deleteAll(SlingHttpServletRequest request, SlingHttpServletResponse response) throws RepositoryException;
    String listParticipant (SlingHttpServletRequest request, SlingHttpServletResponse response);
}
