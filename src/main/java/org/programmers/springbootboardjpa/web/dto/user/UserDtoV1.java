package org.programmers.springbootboardjpa.web.dto.user;

import org.programmers.springbootboardjpa.domain.user.User;
import org.programmers.springbootboardjpa.domain.user.UserInterests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public record UserDtoV1(Long userId, String nickname, String firstname, String lastname, LocalDate birthDate,
                        Optional<UserInterests> interests,
                        LocalDateTime createdDate, LocalDateTime lastModifiedDate) {

    public static UserDtoV1 from(User user) {
        return new UserDtoV1(user.getUserId(), user.getNickname(), user.getName().getFirstname(), user.getName().getLastname(),
                user.getAge().getBirthDate(), (user.getInterests() != null) ? Optional.of(user.getInterests()) : Optional.empty(),
                user.getCreatedDate(), user.getLastModifiedDate());
    }
}