package br.com.sorteio.core.service;

import br.com.sorteio.core.models.Client;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component(immediate = true, service = SorteioService.class)
public class SorteioServiceImpl implements SorteioService, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String CREATE_PATH = "/content/usergenerated/sorteio";


    @Override
    public Client raffle(ResourceResolver resolverResolver) {
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

        client = createNumber(authorList);
        return client;
    }


    private Client createNumber(List<Client> concurrent) {
        Random random = new Random();
        int number = random.nextInt(concurrent.size());
        return concurrent.get(number);
    }
}
