package com.ruhulmus.dto.user.request;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAdd {
    @Size.List({
            @Size(
                    min = FieldConstraints.ForUser.MIN_FIRST_NAME_LENGTH,
                    message = "First Name must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.ForUser.MAX_FIRST_NAME_LENGTH,
                    message = "First Name can have at most {max} characters")
    })
    private String firstName;

    @Size.List({
            @Size(
                    min = FieldConstraints.ForUser.MIN_LAST_NAME_LENGTH,
                    message = "Last Name must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.ForUser.MAX_LAST_NAME_LENGTH,
                    message = "Last Name can have at most {max} characters")
    })
    private String lastName;


    @Size.List({
            @Size(
                    min = FieldConstraints.ForUser.MIN_USERNAME_LENGTH,
                    message = "Username must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.ForUser.MAX_USERNAME_LENGTH,
                    message = "Username can have at most {max} characters")
    })

    @NotEmpty(message = "Username can't be empty")
    private String userName;
    @Size.List({
            @Size(
                    min = FieldConstraints.ForUser.MIN_EMAIL_LENGTH,
                    message = "Email Address must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.ForUser.MAX_EMAIL_LENGTH,
                    message = "Email Address can have at most {max} characters")
    })

    @Email
    @NotEmpty(message = "Email Address can't be empty")
    private String emailAddress;
    @Size.List({
            @Size(
                    min = FieldConstraints.ForUser.MIN_PASSWORD_LENGTH,
                    message = "Password must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.ForUser.MAX_PASSWORD_LENGTH,
                    message = "Password can have at most {max} characters")
    })

    @NotEmpty(message = "Password can't be empty")
    private String password;
    @Size.List({
            @Size(
                    min = FieldConstraints.ForUser.MIN_PHONE_NUMBER_LENGTH,
                    message = "Phone Number must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.ForUser.MAX_PHONE_NUMBER_LENGTH,
                    message = "Phone Number can have at most {max} characters")
    })

    @NotEmpty(message = "Phone Number can't be empty")
    private String phoneNumber;

    private String address;
}
