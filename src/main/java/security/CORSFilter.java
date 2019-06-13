package security;

import javax.annotation.Priority;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class CORSFilter implements Filter {
                @Override
                public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

                        HttpServletRequest request = (HttpServletRequest) req;
                        HttpServletResponse response = (HttpServletResponse) res;

                        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                        response.setHeader("Access-Control-Allow-Credentials", "true");
                        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                        response.setHeader("Access-Control-Max-Age", "3600");
                        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

                        chain.doFilter(req, res);
                }

                @Override
                public void init(FilterConfig filterConfig) {
                }

                @Override
                public void destroy() {
                }

        }
