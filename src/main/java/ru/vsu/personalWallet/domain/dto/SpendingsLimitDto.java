package ru.vsu.personalWallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SpendingsLimitDto {
    private long id;
    private String comment;
    private long maxSum;
    private Date date;
}
