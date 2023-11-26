package com.purchase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Surname is required")
    private String surname;

    @NotEmpty(message = "Postal code is required")
    private String postalCode;

    @NotEmpty(message = "Phone is required")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;


}
