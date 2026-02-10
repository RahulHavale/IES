package com.ies.services;

import com.ies.dtos.LoginForm;
import com.ies.dtos.RegisterForm;
import com.ies.dtos.UnlockForm;

public interface UserService {

    public boolean login(LoginForm loginForm);
    public boolean register(RegisterForm registerForm);
    public boolean unlockAccount(UnlockForm unlockForm);
}
