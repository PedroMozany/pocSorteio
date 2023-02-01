package br.com.sorteio.core.service;

import br.com.sorteio.core.models.Client;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.List;

public interface RegisterService {
    void addParticipant(Session session, Client client)throws RepositoryException;
    List<Client> listParticipant(ResourceResolver resolverResolver);
    void  deleteParticipant(Session session, String  email) throws RepositoryException;
    void deleteAll(Session session) throws RepositoryException;

}
