package dk.aau.sw3.p32operate.business.services;


import dk.aau.sw3.p32operate.dataAccess.repositories.IUsersRepository;
import dk.aau.sw3.p32operate.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsersDetailsService implements UserDetailsService {
    private final IUsersRepository repository;

    public UsersDetailsService(IUsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
