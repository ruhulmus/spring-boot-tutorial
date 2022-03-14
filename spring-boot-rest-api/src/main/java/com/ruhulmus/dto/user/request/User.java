package com.ruhulmus.dto.user.request;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ruhulmus.constant.FieldConstraints;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @Size.List({
            @Size(
                    max = FieldConstraints.User.MAX_NAME_LENGTH,
                    message = "First Name can have at most {max} characters"),
            @Size(
                    min = FieldConstraints.User.MIN_NAME_LENGTH,
                    message = "First Name must have at least {min} characters")
    })
    private String fullName;

    @Size.List({
            @Size(
                    min = FieldConstraints.User.MIN_USERNAME_LENGTH,
                    message = "Username must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.User.MAX_USERNAME_LENGTH,
                    message = "Username can have at most {max} characters")
    })

    @NotEmpty(message = "Username can't be empty")
    private String userName;
    @Size.List({
            @Size(
                    min = FieldConstraints.User.MIN_EMAIL_LENGTH,
                    message = "Email Address must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.User.MAX_EMAIL_LENGTH,
                    message = "Email Address can have at most {max} characters")
    })

    @Email
    @NotEmpty(message = "Email Address can't be empty")
    private String emailAddress;

    @Size.List({
            @Size(
                    min = FieldConstraints.User.MIN_PHONE_NUMBER_LENGTH,
                    message = "Phone Number must have at least {min} characters"),
            @Size(
                    max = FieldConstraints.User.MAX_PHONE_NUMBER_LENGTH,
                    message = "Phone Number can have at most {max} characters")
    })

    @NotEmpty(message = "Phone Number can't be empty")
    private String phoneNumber;

    private String address;
}
