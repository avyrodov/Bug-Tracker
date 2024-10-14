package com.avyrodov.bugTracker;

import com.avyrodov.bugTracker.service.IUserService;
import com.avyrodov.bugTracker.web.utils.RequestUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("requestUtilsInitializer")
public class RequestUtilsInitializer implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        RequestUtils.setUserService(appContext.getBean("userService", IUserService.class));
    }
}
