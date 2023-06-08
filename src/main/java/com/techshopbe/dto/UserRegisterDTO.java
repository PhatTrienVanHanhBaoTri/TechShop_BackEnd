package com.techshopbe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterDTO {
    private String email;
    private String fullname;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pswd;
    private String DOB;
    private String phone;
    private String address;
    private String gender;
}
