/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package br.com.sorteio.core.servlets;

import br.com.sorteio.core.controller.SignUpController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.DtoStatus;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import java.io.IOException;

import static org.apache.sling.api.servlets.HttpConstants.METHOD_DELETE;
import static org.apache.sling.api.servlets.HttpConstants.METHOD_POST;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_EXTENSIONS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(immediate = true, service = Servlet.class, property = {
        SLING_SERVLET_METHODS + "=" + METHOD_POST,
        SLING_SERVLET_METHODS + "=" + METHOD_DELETE,
        SLING_SERVLET_RESOURCE_TYPES + "=" + SignUpServlet.RESOURCE_TYPE,
        SLING_SERVLET_EXTENSIONS + "=" + SignUpServlet.EXTENSION})
@ServiceDescription("Servlet Sorteio")
public class SignUpServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 2405172041950251807L;
    public static final String RESOURCE_TYPE = "api/signUp";
    public static final String EXTENSION = "json";

    @Reference
    SignUpController signUpController;

    @Activate
    public SignUpServlet(@Reference SignUpController signUpControllerMock) {
        this.signUpController = signUpControllerMock;
    }


    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            signUpController.creat(request,response);
            response.setContentType("application/json");
            response.setStatus(200);
            response.getWriter().write(new Gson().toJson(new DtoStatus(response.getStatus(), "Success!!")));

        } catch (ExceptionsParamenter  e) {
            response.setContentType("application/json");
            response.setStatus(500);
            response.getWriter().write(new Gson().toJson(new DtoStatus(response.getStatus(), e.getMessage())));
        }
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            signUpController.delete(request,response);
            response.setContentType("application/json");
            response.setStatus(200);
            response.getWriter().write(new Gson().toJson(new DtoStatus(response.getStatus(), "Success!!")));

        } catch (ExceptionsParamenter e) {
            response.setContentType("application/json");
            response.setStatus(500);
            response.getWriter().write(new Gson().toJson(new DtoStatus(response.getStatus(), e.getMessage())));
        }
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
