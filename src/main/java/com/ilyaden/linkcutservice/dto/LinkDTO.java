package com.ilyaden.linkcutservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDTO {
    @NotBlank(message = "link should not be empty")
    private String link;

}
