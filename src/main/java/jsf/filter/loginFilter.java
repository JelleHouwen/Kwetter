package jsf.filter;

import jsf.bean.Login;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req =(HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        Login session = (Login) req.getSession().getAttribute("Login");

        String url = req.getRequestURI();

        if(session== null ||!session.isLoggedIn) {
            if (url.indexOf("admin.xhtml") >= 0 || url.indexOf("logout.xhtml") >= 0) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "login.xhtml");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }else{
            if(url.indexOf("login.xhtml")>=0){
                resp.sendRedirect(req.getServletContext().getContextPath()+"/admin.xhtml");
            }else if (url.indexOf("logout.xhtml")>=0){
                req.getSession().removeAttribute("Login");
                resp.sendRedirect(req.getServletContext().getContextPath()+"/login.xhtml");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
