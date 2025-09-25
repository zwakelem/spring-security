package za.co.simplitate.springsecurity.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorisationEvents {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent event) {
        log.warn("Authorisation failed for user={}, due to : {}",  event.getAuthentication().get().getName(),
                event.getAuthorizationDecision().toString());
    }
}
