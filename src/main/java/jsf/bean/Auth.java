package jsf.bean;


import Models.User;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

@ManagedBean
@ViewScoped
public class Auth implements Serializable {
        private String username;
        private String password;
        private String originalURL;

//        @PostConstruct
//        public void init() {
//            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//            originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
//
//            if (originalURL == null) {
//                originalURL = externalContext.getRequestContextPath() + "/admin/admin.xhtml";
//            } else {
//                String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
//
//                if (originalQuery != null) {
//                    originalURL += "?" + originalQuery;
//                }
//            }
//        }

        @EJB
        private UserService userService;

        public void login() throws IOException {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

            try {
                request.login(username, password);
                User user = userService.getUser(username);
                externalContext.getSessionMap().put("user", user);
                externalContext.redirect(externalContext.getRequestContextPath() +"/admin/admin.xhtml");
            } catch (ServletException e) {
                // Handle unknown username/password in request.login().
                context.addMessage(null, new FacesMessage("Unknown login"));
            }
        }

        public void logout() throws IOException {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
// Getters/setters for username and password.
    }