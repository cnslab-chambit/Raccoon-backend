package kwu.raccooninfra.dto;

import lombok.Data;

@Data
public class ChatDto {
    private Long userId;
    private Long roomId;
    private Long seqId;
    private String text;
}
