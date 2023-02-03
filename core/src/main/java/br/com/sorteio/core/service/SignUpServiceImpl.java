package br.com.sorteio.core.service;

import br.com.sorteio.core.models.Admin;
import com.day.cq.commons.jcr.JcrUtil;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import java.io.Serializable;

import static com.day.cq.commons.jcr.JcrConstants.NT_UNSTRUCTURED;


@Component(immediate = true, service = SignUpService.class)
public class SignUpServiceImpl implements SignUpService, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String CREATE_PATH = "/content/usergenerated/login";

    @Override
    public void save(Session session, Admin admin) throws RepositoryException {
        Node record = JcrUtil.createPath(CREATE_PATH + "/" + admin.getName(), NT_UNSTRUCTURED, session);
        record.setProperty("password", admin.getPassword());
        record.setProperty("name", admin.getName());
        session.save();
    }


    @Override
    public void delete(Session session, String name) throws RepositoryException {
        Node record = JcrUtil.createPath(CREATE_PATH + "/" + name, NT_UNSTRUCTURED, session);
        record.remove();
        session.save();
    }



}
