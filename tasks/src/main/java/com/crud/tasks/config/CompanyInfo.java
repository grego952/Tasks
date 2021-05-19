package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyInfo {
    @Value("${company.name}")
    private String companyName;
    @Value("${company.address}")
    private String companyAddress;
    @Value("${company.email}")
    private String companyEmail;
    @Value("${company.phone}")
    private String companyPhone;
}
