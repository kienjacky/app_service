package com.vn.app_service.security.services.users;

import com.vn.app_service.constant.enums.ApiStatus;
import com.vn.app_service.model.User;
import com.vn.app_service.payload.response.BaseResponse;
import com.vn.app_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BaseResponse<?> getAll() {
        List<User> lstUser = userRepository.findAll();
        if (lstUser.isEmpty()) {
            return BaseResponse.builder().status(ApiStatus.BAD_REQUEST.getStatus()).build();
        }
        return BaseResponse.builder().status(ApiStatus.SUCCESS.getStatus()).data(lstUser).build();
    }
}
