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
package br.com.sorteio.core.filters;

import br.com.sorteio.core.controller.LoginController;
import br.com.sorteio.core.exceptions.ExceptionsParamenter;
import br.com.sorteio.core.models.DtoStatus;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.engine.EngineConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.osgi.service.component.propertytypes.ServiceVendor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Simple servlet filter component that logs incoming requests.
 */
@Component(service = Filter.class, property = {EngineConstants.SLING_FILTER_SCOPE + "=" + EngineConstants.FILTER_SCOPE_REQUEST,})
@ServiceDescription("Demo to filter incoming requests")
@ServiceRanking(-700)
@ServiceVendor("Adobe")
public class AuthenticationFilter implements Filter {
    private static final String HEADER_STRING = "Authorization";

    @Reference
    LoginController loginController;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException {


        final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
        final SlingHttpServletResponse slingResponse = (SlingHttpServletResponse) response;

        String requestURL = slingRequest.getRequestURL().toString();

        boolean pageAuthorized = (requestURL.equals("http://localhost:4502/content/raffle/winner.model.json") || requestURL.equals("http://localhost:4502/content/api/sorteio.model.json"));
        String method = slingRequest.getMethod();


        try {
            if (pageAuthorized && method.equals("GET")) {
                sessionIsNull(slingRequest);
                if (loginController.verify(slingRequest, slingResponse)) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setContentType("application/json");
                    slingResponse.setStatus(500);
                    response.getWriter().write(new Gson().toJson(new DtoStatus(slingResponse.getStatus(), "Please check the parameters Authentication")));
                }
            } else if (pageAuthorized && method.equals("DELETE")) {
                sessionIsNull(slingRequest);
                if (loginController.verify(slingRequest, slingResponse)) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setContentType("application/json");
                    slingResponse.setStatus(500);
                    response.getWriter().write(new Gson().toJson(new DtoStatus(slingResponse.getStatus(), "Please check the parameters Authentication")));
                }
            } else if (pageAuthorized && method.equals("PUT")) {
                sessionIsNull(slingRequest);
                if (loginController.verify(slingRequest, slingResponse)) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setContentType("application/json");
                    slingResponse.setStatus(500);
                    response.getWriter().write(new Gson().toJson(new DtoStatus(slingResponse.getStatus(), "Please check the parameters Authentication")));
                }
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (ExceptionsParamenter | ServletException e) {
            response.setContentType("application/json");
            slingResponse.setStatus(500);
            response.getWriter().write(new Gson().toJson(new DtoStatus(slingResponse.getStatus(), e.getMessage())));
        }

    }

    @Override
    public void destroy() {

    }


    private void sessionIsNull(SlingHttpServletRequest slingRequest) throws ExceptionsParamenter {
        HttpSession session = slingRequest.getSession();
        if (session.getAttribute("Authorization") == null) {
            throw  new ExceptionsParamenter("Please login");
        }
    }


}