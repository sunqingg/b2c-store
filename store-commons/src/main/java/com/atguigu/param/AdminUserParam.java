package com.atguigu.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AdminUserParam {
    @Length(min = 6)
    private String userAccount;
    @Length(min = 6)
    private String userPaassword;
    @NotBlank
    private String verCode;
}
