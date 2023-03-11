package kwu.raccoonapi.config;

import kwu.raccoondomain.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDomainService userDomainService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return new UserPrincipal(userDomainService.getUserByIdOrElseThrow(Long.parseLong((userId))));
    }

}
