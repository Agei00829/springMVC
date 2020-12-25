
package com.lab.jpa.controller;

import com.google.api.client.googleapis.apache.GoogleApacheHttpTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    private final String CLIEND_ID = "682356853200-6l0uj6qsfb38ls32n29ab639fkk7dncs.apps.googleusercontent.com";
    
    @PostMapping("/check")
    //@ResponseBody
    public String check(HttpServletRequest req) {
        String id_token = req.getParameter("id_token");
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(new NetHttpTransport(), new JacksonFactory());
        GoogleIdToken id_token_result = null;
        try {
            id_token_result = verifier.verify(id_token);
            if(id_token_result != null){
                Payload payload = id_token_result.getPayload();
                String username = payload.get("name") + "";
                String email = payload.getEmail();
                String picture_url = payload.get("picture") + "";
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                return "redirect: ../dept/";
                //return "pass" + username + " , " + email + " , " + picture_url;
            }else {
                return "redirect: ../../google_login.jsp";
            }
        } catch (Exception e) {
        }
        return "Login Check";
        
    }
}
