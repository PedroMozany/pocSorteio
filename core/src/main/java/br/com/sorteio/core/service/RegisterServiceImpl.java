package br.com.sorteio.core.service;

import br.com.sorteio.core.models.Client;
import com.day.cq.commons.jcr.JcrUtil;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.day.cq.commons.jcr.JcrConstants.NT_UNSTRUCTURED;

@Component(immediate = true, service = RegisterService.class)
public class RegisterServiceImpl implements RegisterService, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String CREATE_PATH = "/content/usergenerated/sorteio";

    @Override
    public void addParticipant(Session session, Client client) throws RepositoryException {
        Node record = JcrUtil.createPath(CREATE_PATH + "/" + client.getEmail(), NT_UNSTRUCTURED, session);
        record.setProperty("email", client.getEmail());
        record.setProperty("name", client.getName());
        session.save();
    }


    @Override
    public List<Client> listParticipant(ResourceResolver resolverResolver) {

        List<Client> authorList = new LinkedList<>();

        Iterator<Resource> authors = resolverResolver.getResource(CREATE_PATH).listChildren();
        Client client = null;

        while (authors.hasNext()) {
            Resource resource = authors.next();
            ValueMap prop = resource.getValueMap();
            Object[] aux = prop.values().toArray();
            client = new Client((String) aux[1], (String) aux[2]);
            authorList.add(client);
        }
        return authorList;
    }



    @Override
    public void deleteParticipant(Session session, String email) throws RepositoryException {
        Node record = JcrUtil.createPath(CREATE_PATH + "/" + email, NT_UNSTRUCTURED, session);
        record.remove();
        session.save();
    }



    @Override
    public void deleteAll(Session session) throws RepositoryException {
        Node record = JcrUtil.createPath(CREATE_PATH, NT_UNSTRUCTURED, session);
        record.remove();
        session.save();
    }
}
