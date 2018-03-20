package com.jewellerypos.api.filter;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jewellerypos.api.annotation.Secured;
import com.jewellerypos.api.error.ErrorScenario;
import com.jewellerypos.api.error.NotAuthorizedException;
import com.jewellerypos.api.error.OperatorNotFoundException;
import com.jewellerypos.api.model.Operator;
import com.jewellerypos.api.model.OperatorDevice;
import com.jewellerypos.api.repository.OperatorRepository;
import com.jewellerypos.api.util.Role;

/**
 * 
 * @author webwerks This class authenticates user.
 */
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {
    
    private final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    
    /**
     * Filter the user based on cookie or userID and password
     */
    /*
     * @Override public void filter(ContainerRequestContext requestContext)
     * throws IOException {
     * 
     * 
     * 
     * }
     */
    
    // Authorization: Basic QWxhZGRpbjpPcGVuU2VzYW1l
    
    @Autowired
    private OperatorRepository operatorRepository;
    
    @Context
    private ResourceInfo resourceInfo;
    
    @Context
    private HttpServletRequest request;
    
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .entity("Access blocked for all users !!").build();
    
    @Override
    public void filter(ContainerRequestContext requestContext) {
        
        Method method = resourceInfo.getResourceMethod();
        List<Role> methodRoles = extractRoles(method);
        
        // Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            // Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
            
            // Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            
            // Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
            
            // If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
            
            // Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            
            // Decode username and password
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
            ;
            
            // Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
            
            // Verifying Username and password
            log.info(username);
            log.info(password);
            
            /*
             * System.out.println(username); System.out.println(password);
             */
            
            // Verify user access
            // if(method.isAnnotationPresent(RolesAllowed.class))
            // {
            // RolesAllowed rolesAnnotation =
            // method.getAnnotation(RolesAllowed.class);
            /// Set<String> rolesSet = new
            // HashSet<String>(Arrays.asList(rolesAnnotation.value()));
            
                        
            
            // Is user valid?
            if (!isUserAllowed(username, password,methodRoles)) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
            // }
        }
    }
    
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<Role>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }
    
    private boolean isUserAllowed(final String username, final String password, List<Role> methodRoles) {
        boolean isAllowed = false;
        
        // Step 1. Fetch password from database and match with password in
        // argument
        // If both match then get the defined role for user from database and
        // continue; else return isAllowed [false]
        // Access the database and do this part yourself
        // String userRole = userMgr.getUserRole(username);
        
        // partyRepository.findByPartyId(Pty_ID)
        Long ID = new Long(username);
        // DeviceRegistration deviceRegistration =
        // deviceRegistrationRepository.findOne(ID);
        Operator operator =  operatorRepository.findByOperatorCode(ID);
        if (operator != null) {
            
            List<OperatorDevice> deviceRegistrationList = operator.getOperatorDevice();
            
            if (!deviceRegistrationList.isEmpty()) {
                String cookie = deviceRegistrationList.stream().filter(p -> p.getDeviceStatus().equals("ACTIVE")).map(m -> m.getCookie()).findAny().orElse(null);
                if (cookie.equals(password)) {
                    if(methodRoles.contains(operator.getOperatorRole()))
                        isAllowed = true;
                    else
                        throw new NotAuthorizedException(ErrorScenario.OPERATION_FORBIDDEN);
                        
                    
                } else {
                    throw new NotAuthorizedException(ErrorScenario.COOKIE_NOT_MATCHED);
                }
                
            } else {
                
                return false;
            }
            
        } else {
            throw new OperatorNotFoundException(ErrorScenario.OPERATOR_NOT_FOUND);
        }
        return isAllowed;
    }
    
}
