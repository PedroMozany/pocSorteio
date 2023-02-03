package br.com.sorteio.core.service;

import br.com.sorteio.core.models.Admin;

import javax.jcr.RepositoryException;
import javax.jcr.Session;


public interface SignUpService {
    void save(Session session, Admin admin) throws RepositoryException;

    void delete(Session session, String name) throws RepositoryException;
}
