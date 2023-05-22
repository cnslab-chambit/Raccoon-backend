package raccoon.raccoonchatting.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import raccoon.raccoonchatting.dto.ChatRoomQueryDto;

import java.util.List;

import static raccoon.raccoonchatting.entity.QChat.chat;
import static raccoon.raccoonchatting.entity.QChatRoom.chatRoom;


@Repository
@RequiredArgsConstructor
public class ChatRepo {
    private final JPAQueryFactory queryFactory;

    public Long getLastChatSeqId(Long roomId){
        BooleanBuilder where = new BooleanBuilder();

        where.and(chat.roomId.eq(roomId));

        return queryFactory
                .select(chat.seqId)
                .from(chat)
                .where(where)
                .orderBy(chat.seqId.desc())
                .limit(1)
                .fetchOne();
    }

    public List<Chat> getAll(Long roomId){
        BooleanBuilder where = new BooleanBuilder();
        where.and(chat.roomId.eq(roomId));

        return queryFactory
                .selectFrom(chat)
                .where(where)
                .orderBy(chat.seqId.asc())
                .fetch();
    }

    public List<ChatRoomQueryDto> findAllUserChatRooms(Long userId){
        BooleanBuilder where = new BooleanBuilder();
        where.or(chatRoom.user1Id.eq(userId));
        where.or(chatRoom.user2Id.eq(userId));

        QChat subChat = new QChat("subChat");

        return queryFactory.select(Projections.bean(ChatRoomQueryDto.class,
                        chatRoom.id,
                        chatRoom.user1Id,
                        chatRoom.user2Id,
                        chat.text.as("lastMessage"),
                        chat.createdDt.as("lastTime")
                        ))
                .from(chatRoom)
                .innerJoin(chat).on(chat.roomId.eq(chatRoom.id))
                .where(where)
                .where(chat.seqId.eq(
                        JPAExpressions
                                .select(subChat.seqId.max())
                                .from(subChat)
                                .where(subChat.roomId.eq(chatRoom.id))
                ))
                .fetch();

    }

}
