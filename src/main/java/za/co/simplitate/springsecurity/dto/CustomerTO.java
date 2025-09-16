package za.co.simplitate.springsecurity.dto;

public record CustomerTO(
        long id,
        String email,
        String pwd,
        String role
) {}
