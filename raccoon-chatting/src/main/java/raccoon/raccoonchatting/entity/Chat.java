package raccoon.raccoonchatting.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"room_id", "seq_id"})
        })
public class Chat {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "seq_id", nullable = false)
        private Long seqId;

        @Column(name = "room_id", nullable = false)
        private Long roomId;

        @Column(nullable = false)
        private Long userId;

        @Column()
        private String text;

        @Column(nullable = false)
        private LocalDateTime createdDt;

        public static Chat of(Long seqId,Long roomId,Long userId,String text){
                return Chat.builder()
                        .seqId(seqId)
                        .roomId(roomId)
                        .userId(userId)
                        .text(text)
                        .createdDt(LocalDateTime.now())
                        .build();
        }
}
