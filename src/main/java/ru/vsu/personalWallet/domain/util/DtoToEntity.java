package ru.vsu.personalWallet.domain.util;

import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.entity.AimEntity;

public class DtoToEntity {
    //TODO: finish class
    public static AimEntity toEntity(AimDto aimDto){
        if (aimDto!=null){
            return new AimEntity().setId(aimDto.getId())
                    .setName(aimDto.getName())
                    .setOperationType(aimDto.getOperationType())
                    .setDescription(aimDto.getDescription())
                    .setReminderSec(aimDto.getReminderSec())
                    .setDate(aimDto.getDate());
        }
        else return null;
    }
}
