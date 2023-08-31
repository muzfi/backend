package com.example.muzfi.Dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvanceProfileSettingsUpdateDto {
    private Boolean isAllowToFollow;

    private Boolean isContentVisible;

    private Boolean isActiveInCommunityVisible;
}
