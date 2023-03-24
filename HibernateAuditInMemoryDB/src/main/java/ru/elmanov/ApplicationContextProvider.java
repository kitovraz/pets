package ru.elmanov;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Component;
import ru.elmanov.model.Resource;
import ru.elmanov.model.SupportProgram;
import ru.elmanov.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Value("${my.database.schema}")
    private String schemaDb;

    @Value("${dev.work.schema}")
    private String devWorkSchema;

    private static String getCurrentBranchName() {
        try {
            Process process = Runtime.getRuntime().exec("git rev-parse --abbrev-ref HEAD");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Could not get current git branch name", e);
        }
    }

    @EventListener(ApplicationContextInitializedEvent.class)
    public void ApplicationStartedEvent() {
        System.out.println("ApplicationStartedEvent");
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "default");//setCurrentProfile());
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

    }

    private String setCurrentProfile() {
        String branchName = getCurrentBranchName();
        if (!branchName.equals("main")) {
            return StringUtils.isBlank(devWorkSchema) ?
                    "default" : devWorkSchema;
        }
        return "default";
    }
}