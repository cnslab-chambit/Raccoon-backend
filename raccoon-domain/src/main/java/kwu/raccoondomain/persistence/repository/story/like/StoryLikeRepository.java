package kwu.raccoondomain.persistence.repository.story.like;

import kwu.raccoondomain.persistence.domain.story.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryLikeRepository extends JpaRepository<Like,Long>, StoryLikeCustomRepository {
}
