package kwu.raccoondomain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserCoordinateUpdateDto {
    private Double longitude;
    private Double latitude;
}
