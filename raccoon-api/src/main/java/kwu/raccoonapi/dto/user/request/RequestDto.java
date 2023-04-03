package kwu.raccoonapi.dto.user.request;

import kwu.raccoondomain.persistence.domain.user.enums.Location;
import lombok.Getter;

@Getter
public class RequestDto {
    private Location location;
}
