package ru.greatbit.tourminer.rest.security;

import ru.greatbit.tourminer.beans.Role;
import ru.greatbit.tourminer.beans.User;
import ru.greatbit.tourminer.service.services.UserService;
import ru.greatbit.tourminer.service.utils.UserDataUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 */
public class PreAuthUserDetails implements AuthenticationUserDetailsService {

    private final static Logger log = Logger.getLogger(PreAuthUserDetails.class);

    @Autowired
    private UserService userService;

    @Autowired
    UserDataUtils userDataUtils;

    public UserDetails loadUserDetails(Authentication authUser) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByName(authUser.getName());
            if(user == null){
                log.info("User " + authUser.getName() + " not found, granting Guest Role");
                user = new User();
                user.setName(authUser.getName());
                user.getRoles().add(Role.ROLE_GUEST);
            }

            log.info("Setting up a user " + user.getName());
            UserDetailsImpl userDetails = new UserDetailsImpl();
            userDetails.setUser(user);
            userDetails.setRoles();
            return userDetails;
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
