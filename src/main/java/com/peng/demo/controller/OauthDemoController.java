package com.peng.demo.controller;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/oauth")
@RestController
public class OauthDemoController {

    String clientId = null;
    String clientSecret = null;
    String accessTokenUrl = null;
    String userInfoUrl = null;
    String redirectUrl = null;
    String response_type = null;
    String code = null;

    @RequestMapping("/requestServerCode")
    public String requestServerFirst(HttpServletRequest request, HttpServletResponse response,
                                     RedirectAttributes attr) throws OAuthProblemException {
        clientId = "clientId";
        clientSecret = "clientSecret";
        accessTokenUrl = "responseCode";
        redirectUrl = "http://localhost:8080/oauthclient01/server/callbackCode";
        response_type = "code";

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        String requestUrl = null;

        try {
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .authorizationLocation(accessTokenUrl)
                    .setResponseType(response_type)
                    .setClientId(clientId)
                    .setRedirectURI(redirectUrl)
                    .buildQueryMessage();
            requestUrl = accessTokenRequest.getLocationUri();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return "redirect:http://localhost:8082/oauthserver/" + requestUrl;

    }

    @GetMapping("/product/{id}")
    public String gerProduct(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id:" + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id:" + id;
    }
}
