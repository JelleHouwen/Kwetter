package jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class Auth implements Serializable {
    private String username;
    private String password;

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

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(username, password);
            if (request.isUserInRole("Admin"))
            {
                externalContext.redirect("http://localhost:8080/Kwetter_versie_5001_war_exploded/admin/admin.xhtml");
            }
            else if(request.isUserInRole("Mod")) {
                externalContext.redirect("http://localhost:8080/Kwetter_versie_5001_war_exploded/mod/mod.xhtml");
            }
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }
        public void logout() throws IOException {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }



}