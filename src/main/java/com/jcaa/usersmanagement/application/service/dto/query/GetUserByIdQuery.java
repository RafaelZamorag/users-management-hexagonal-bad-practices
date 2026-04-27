package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

import lombok.Builder;

@Builder
public record GetUserByIdQuery(
@NotBlank String id){

}
