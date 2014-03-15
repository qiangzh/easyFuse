package com.maodr.framework.listener;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class UserCounterListener implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {

    public static final String COUNT_KEY = "userCounter";

    public static final String USERS_KEY = "userNames";

    public static final String EVENT_KEY = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    private transient ServletContext servletContext;

    private int counter;

    private Set<User> users;

    @Override
    public synchronized void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.setAttribute((COUNT_KEY), Integer.toString(counter));
    }

    @Override
    public synchronized void contextDestroyed(ServletContextEvent event) {
        servletContext = null;
        users = null;
        counter = 0;
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            SecurityContext securityContext = (SecurityContext) event.getValue();
            if (securityContext != null && securityContext.getAuthentication().getPrincipal() instanceof User) {
                User user = (User) securityContext.getAuthentication().getPrincipal();
                addUsername(user);
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            SecurityContext securityContext = (SecurityContext) event.getValue();
            Authentication auth = securityContext.getAuthentication();
            if (auth != null && (auth.getPrincipal() instanceof User)) {
                User user = (User) auth.getPrincipal();
                removeUsername(user);
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            final SecurityContext securityContext = (SecurityContext) event.getValue();
            if (securityContext.getAuthentication() != null
                    && securityContext.getAuthentication().getPrincipal() instanceof User) {
                final User user = (User) securityContext.getAuthentication().getPrincipal();
                addUsername(user);
            }
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Object obj = se.getSession().getAttribute(EVENT_KEY);
        if (obj != null) {
            se.getSession().removeAttribute(EVENT_KEY);
        }
    }

    private synchronized void incrementUserCounter() {
        counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
        counter++;
        servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));
    }

    private synchronized void decrementUserCounter() {
        int counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
        counter--;

        if (counter < 0) {
            counter = 0;
        }

        servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));
    }

    @SuppressWarnings("unchecked")
    private synchronized void addUsername(User user) {
        users = (Set<User>) servletContext.getAttribute(USERS_KEY);

        if (users == null) {
            users = new LinkedHashSet<User>();
        }

        if (!users.contains(user)) {
            users.add(user);
            servletContext.setAttribute(USERS_KEY, users);
            incrementUserCounter();
        }
    }

    @SuppressWarnings("unchecked")
    private synchronized void removeUsername(User user) {
        users = (Set<User>) servletContext.getAttribute(USERS_KEY);

        if (users != null) {
            users.remove(user);
        }

        servletContext.setAttribute(USERS_KEY, users);
        decrementUserCounter();
    }

    private boolean isAnonymous() {
        AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx != null) {
            Authentication auth = ctx.getAuthentication();
            return resolver.isAnonymous(auth);
        }
        return true;
    }
}
