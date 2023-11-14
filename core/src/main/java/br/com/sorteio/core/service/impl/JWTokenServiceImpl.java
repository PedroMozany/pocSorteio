package br.com.sorteio.core.service.impl;

import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.Admin;
import br.com.sorteio.core.service.JWTokenService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.InvalidKeyException;

@Component(immediate = true, service = JWTokenService.class)
public class JWTokenServiceImpl implements JWTokenService, Serializable {
    private static final long serialVersionUID = 1L;
    private char[] key;
    private int[] sbox;
    private static final int SBOX_LENGTH = 256;
    private static final int TAM_MIN_CHAVE = 5;
    private static final String SECRET = "SenhaTokenSorteioCompass.Uol";
    private static final String HEADER_STRING = "Authorization";

    @Override
    public void addAuthentication(SlingHttpServletRequest request, Admin admin) throws ExceptionsParamenter {
        HttpSession session = request.getSession();
        String key = admin.getName() + " " + admin.getPassword();

        try {
            setKey(SECRET);
            char[] mensagemCriptografada = criptografa(key.toCharArray());
            session.setAttribute(HEADER_STRING,new String(mensagemCriptografada));
        } catch (InvalidKeyException e) {
            throw new ExceptionsParamenter(e.getMessage());
        }


    }

    @Override
    public String getAuthentication(HttpSession session, SlingHttpServletResponse response) {
        char[] mensagemCriptografada = new String((String) session.getAttribute(HEADER_STRING)).toCharArray();
        char[] textoDescriptografado = criptografa(mensagemCriptografada);
        return new String(textoDescriptografado);
    }

    private char[] criptografa(final char[] msg) {
        sbox = initSBox(key);
        char[] code = new char[msg.length];
        int i = 0;
        int j = 0;
        for (int n = 0; n < msg.length; n++) {
            i = (i + 1) % SBOX_LENGTH;
            j = (j + sbox[i]) % SBOX_LENGTH;
            swap(i, j, sbox);
            int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGTH];
            code[n] = (char) (rand ^ (int) msg[n]);
        }
        return code;
    }

    private int[] initSBox(char[] key) {
        int[] sbox = new int[SBOX_LENGTH];
        int j = 0;

        for (int i = 0; i < sbox.length; i++) {
            sbox[i] = i;
        }

        for (int i = 0; i < sbox.length; i++) {
            j = (j + sbox[i] + key[i % key.length]) % SBOX_LENGTH;
            swap(i, j, sbox);
        }
        return sbox;
    }


    private void swap(int i, int j, int[] sbox) {
        int temp = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = temp;
    }


    private void setKey(String key) throws InvalidKeyException {
        if (!(key.length() >= TAM_MIN_CHAVE && key.length() < SBOX_LENGTH)) {
            throw new InvalidKeyException("Tamanho da chave deve ser entre "
                    + TAM_MIN_CHAVE + " e " + (SBOX_LENGTH - 1));
        }
        this.key = key.toCharArray();
    }

}
