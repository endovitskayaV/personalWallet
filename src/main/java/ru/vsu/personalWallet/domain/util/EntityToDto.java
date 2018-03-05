package ru.vsu.personalWallet.domain.util;

import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.entity.AimEntity;

public class EntityToDto {
    //TODO: finish class
    public static AimDto toDto(AimEntity aimEntity){
        if (aimEntity!=null){
            return new AimDto().setId(aimEntity.getId())
                    .setName(aimEntity.getName())
                    .setOperationType(aimEntity.getOperationType())
                    .setDescription(aimEntity.getDescription())
                    .setReminderSec(aimEntity.getReminderSec())
                    .setDate(aimEntity.getDate());
        }
        else return null;
    }
}
