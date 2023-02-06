package br.com.sorteio.core.controller;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import br.com.sorteio.core.service.JWTokenService;
import br.com.sorteio.core.service.LoginService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component(immediate = true, service = LoginController.class)
public class LoginControllerImpl implements LoginController, Serializable {

    private static final long serialVersionUID = 1L;
    @Reference
    LoginService loginService;
    @Reference
    JWTokenService jwTokenService;


    @Override

    public void login(SlingHttpServletRequest request) throws ExceptionsParamenter {
        try {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            Admin admin = new Admin(name, password);

            if (name != null && password != null && !name.equals("") && !password.equals("")) {
                if (loginService.verify(request, admin)) {
                    jwTokenService.addAuthentication(request, admin);
                } else {
                    throw new ExceptionsParamenter("User does not exist!!");
                }
            } else {
                throw new ExceptionsParamenter("Please check the parameters.");
            }
        }catch (IOException | NoSuchAlgorithmException | InvalidKeyException | RepositoryException e) {
            throw new ExceptionsParamenter(e.getMessage());
        }
    }


    @Override
    public boolean verify(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ExceptionsParamenter{
        try {
            HttpSession session = request.getSession();
            String[] authorization = jwTokenService.getAuthentication(session,response).split(" ");

            if (loginService.verify(request, new Admin(authorization[0], authorization[1]))) {
                return true;
            } else {
                return false;
            }
        } catch (RepositoryException | NoSuchAlgorithmException | IOException e) {
            throw new ExceptionsParamenter(e.getMessage());
        }

    }


    @Override
    public void logout(SlingHttpServletRequest request, SlingHttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();

    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

}
