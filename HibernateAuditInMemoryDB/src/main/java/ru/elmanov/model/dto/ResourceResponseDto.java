package ru.elmanov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionType;
import ru.elmanov.model.BaseEntity;
import ru.elmanov.model.SupportProgram;
import ru.elmanov.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponseDto extends BaseEntity {

    private String title;

    private SupportProgram supportProgram;
    private Long supportProgramId;

    private User user;
    private Long userId;

    private RevisionType type;
}
