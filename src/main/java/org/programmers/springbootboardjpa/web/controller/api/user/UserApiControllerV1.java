package org.programmers.springbootboardjpa.web.controller.api.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programmers.springbootboardjpa.service.user.UserService;
import org.programmers.springbootboardjpa.web.dto.LongIdResponse;
import org.programmers.springbootboardjpa.web.dto.user.UserCreateFormV1;
import org.programmers.springbootboardjpa.web.dto.user.UserDtoV1;
import org.programmers.springbootboardjpa.web.dto.user.UserUpdateFormV1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiControllerV1 implements UserApiController {

    private final UserService userService;


    @PostMapping("/api/v1/users")
    public ResponseEntity<LongIdResponse> postUser(@RequestBody UserCreateFormV1 userCreateForm) {
        Long id = userService.registerUser(userCreateForm);
        return ResponseEntity.created(URI.create("/api/v1/users/" + id)).body(new LongIdResponse(id));
    }
    //TODO PR 포인트7
    @GetMapping("/api/v1/users/{id}")
    public UserDtoV1 showUserDetails(@PathVariable("id") Long userId) {
        //TODO: 사용자가 설정한 공개 범위에 따라 다르게 공개
        return UserDtoV1.from(userService.findUserWith(userId));
    }

    @PatchMapping("/api/v1/users/{id}")
    public UserDtoV1 setUserData(@PathVariable("id") Long userId, @RequestBody UserUpdateFormV1 userUpdateForm) {
        return UserDtoV1.from(userService.modifyUserdata(userUpdateForm));
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/users")
    public Page<UserDtoV1> showUsersPaged(Pageable pageable) {
        return userService.findUsersWithPage(pageable).map(UserDtoV1::from);
    }
}