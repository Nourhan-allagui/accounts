package com.example.account.dtos;

public class LoginDto {
    private String email;
    private String password;
    public LoginDto() {
    }

    public LoginDto(SignUpResponse signUpResponse) {
        this.email = signUpResponse.getEmail();
        this.password = signUpResponse.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
