package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyInfo;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyInfo companyInfo;

    @Autowired
    private TaskRepository repository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;



    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit Website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "Trello Card added");
        context.setVariable("goodbye", "Best Regards");
        context.setVariable("company_name", companyInfo.getCompanyName());
        context.setVariable("company_address", companyInfo.getCompanyAddress());
        context.setVariable("company_mail", companyInfo.getCompanyEmail());
        context.setVariable("company_phone", companyInfo.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTaskQuantityEmail(String message) {

        List<String> company = new ArrayList<>();
        company.add(companyInfo.getCompanyName());
        company.add(companyInfo.getCompanyAddress());
        company.add(companyInfo.getCompanyEmail());
        company.add(companyInfo.getCompanyPhone());

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_quantity", "You have " + repository.count() + " tasks to perform");
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit Website");
        context.setVariable("preview", "Number of tasks");
        context.setVariable("goodbye", "Best Regards");
        context.setVariable("company", company);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/number-of-tasks-mail", context);
    }
}
