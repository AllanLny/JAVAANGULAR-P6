package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserRegistrationDto {
    @Email(message = "Email invalide")
    private String email;

    @Size(min = 3, max = 50, message = "Le nom d'utilisateur doit contenir entre 3 et 50 caractères")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial")
    private String password;
}
