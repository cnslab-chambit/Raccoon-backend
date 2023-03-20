package kwu.raccoonapi.config;

import kwu.raccooncommon.consts.cache.CacheKey;
import kwu.raccoondomain.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDomainService userDomainService;

    @Override
    @Cacheable(value = CacheKey.JWT,key = "#userId",unless = "#result == null")
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return new UserPrincipal(userDomainService.getUserByIdOrElseThrow(Long.parseLong((userId))));
    }

}
