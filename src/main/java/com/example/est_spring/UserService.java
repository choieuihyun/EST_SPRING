package com.example.est_spring;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

/*    public UserService(UserRepositoryInterface userRepository1) { // 이거 파라미터 변수명만 바뀐건데 변수명이랑 같은 구현체로 알아서 찾아가줌.
        this.userRepository = userRepository1;
    }
*/

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
