package com.sekohan.sekohanback.service.user.signout;

import org.springframework.transaction.annotation.Transactional;

public interface SignOutService {
    @Transactional
    void logout(String accessToken);
}
