package kwu.raccoonapi.dto.user.request;

import kwu.raccoondomain.dto.user.UserCoordinateUpdateDto;
import lombok.Data;

@Data
public class UserCoordinateUpdateRequest {
    private Double latitude;
    private Double longitude;

    public UserCoordinateUpdateDto toUserCoordinateUpdateDto(){
        return UserCoordinateUpdateDto.of(
                longitude,
                latitude
        );
    }
}
