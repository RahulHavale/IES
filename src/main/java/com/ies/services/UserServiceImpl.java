package com.ies.services;

import com.ies.dtos.LoginForm;
import com.ies.dtos.RegisterForm;
import com.ies.dtos.UnlockForm;
import com.ies.entities.User;
import com.ies.repositories.UserRepo;
import com.ies.utils.EmailUtils;
import com.ies.utils.TempPzwd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtils emailUtils;

    String tempPzwd = TempPzwd.tempPzwd();

    @Override
    public boolean login(LoginForm loginForm){
        Logger log = Logger.getLogger(String.valueOf(UserServiceImpl.class));
        User dbData = userRepo.findByEmail(loginForm.getEmail());
        log.info(dbData.getEmail());

        log.info(loginForm.getEmail());
        if(dbData.getEmail().equals(loginForm.getEmail()) &&
                dbData.getPassword().equals(loginForm.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public boolean register(RegisterForm registerForm) {
        User email = userRepo.findByEmail(registerForm.getEmail());

        if(email != null) return false;

        User entity = new User();
        BeanUtils.copyProperties(registerForm,entity);
        entity.setAccStatus("LOCKED");
        entity.setPassword(tempPzwd);
        userRepo.save(entity);

        String to = registerForm.getEmail();
        String subject = "Unlock your account | Ashok IT";
        StringBuffer body = new StringBuffer(" ");
        body.append("<h1>Use below temporary password to Unlock your account</h1>");
        body.append("Temorary pwd : " + tempPzwd);
        body.append("<br>");
        body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click here to Unlock your account</a>");
        emailUtils.sendEmail(to, subject, body.toString());
        return true;
    }

    @Override
    public boolean unlockAccount(UnlockForm unlockForm) {

        if(unlockForm.getTempPzwd().equals(tempPzwd)){
            User entity = userRepo.findByEmail(unlockForm.getEmail());
            entity.setPassword(unlockForm.getPzwd());
            entity.setAccStatus("UNLOCKED");
            userRepo.save(entity);
            return true;
        }
        return false;
    }
}
