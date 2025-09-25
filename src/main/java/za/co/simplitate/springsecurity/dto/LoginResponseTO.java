package za.co.simplitate.springsecurity.dto;

public record LoginResponseTO(
        String status,
        String jwtToken
) {}
