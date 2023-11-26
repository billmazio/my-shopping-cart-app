package com.purchase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {

    private Long id;

    @NotBlank(message = "{item.name.notBlank}")
    private String name;

    @NotBlank(message = "{item.price.notBlank}")
    private BigDecimal price;

    @NotBlank(message = "{item.code.notBlank}")
    @Pattern(regexp = "\\d{10}|\\d{13}", message = "{seminar.code.size}")
    private String code;
}
