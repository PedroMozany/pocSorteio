package br.com.sorteio.core.service;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface JWTokenService {
   void addAuthentication(SlingHttpServletRequest  request, Admin admin) throws IOException, NoSuchAlgorithmException, InvalidKeyException, ExceptionsParamenter;
   String getAuthentication(HttpSession session,SlingHttpServletResponse response) throws NoSuchAlgorithmException, IOException;
}
