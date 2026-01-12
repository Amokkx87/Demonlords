package de.demonlords.auth;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
     
    public AuthFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {
 
            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);
            //  allow user to proceed if url is login.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            
            boolean loggedIn = (session != null && session.getAttribute("username") != null);

            boolean loginRequest = reqURI.contains("/login.xhtml");
            boolean resourceRequest = reqURI.contains("javax.faces.resource");
            boolean resourceRequest2 = reqURI.contains("jakarta.faces.resource");
            boolean css = reqURI.contains(".css");
            boolean publicAccess = reqURI.contains("/public/");
            
            if (loggedIn || loginRequest || publicAccess || resourceRequest || resourceRequest2 || css) {
                chain.doFilter(request, response);
            } else {
                System.out.println("AuthFilter: Unauthorized access to " + reqURI + " → redirecting to login.xhtml");
                res.sendRedirect(req.getContextPath() + "/login.xhtml?faces-redirect=true");
            }
      }
     catch(Throwable t) {
         System.out.println( t.getMessage());
     }
    } //doFilter
 
    @Override
    public void destroy() {
         
    }

}