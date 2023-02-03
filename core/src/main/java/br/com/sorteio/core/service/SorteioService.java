package br.com.sorteio.core.service;


import br.com.sorteio.core.models.Client;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;

public interface SorteioService {
    Client raffle(ResourceResolver resolverResolver) throws RepositoryException;

}
