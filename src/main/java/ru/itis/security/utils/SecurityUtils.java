package ru.itis.security.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.dtos.UserDto;
import ru.itis.models.Cv;
import ru.itis.models.User;
import ru.itis.repositories.CvRepository;
import ru.itis.security.details.UserDetailsImpl;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SecurityUtils {
    private final CvRepository cvRepository;

    public boolean isCvOwner(UserDetailsImpl userDetailsImpl, String cvId) {
        if (userDetailsImpl == null) {
            return false;
        }
        User user = userDetailsImpl.getUser();
        Optional<Cv> cv = cvRepository.findById(cvId);
        return cv.map(value -> value.getOwnerId().equals(user.get_id())).orElse(false);
    }
}
