package kwu.raccoondomain.persistence.query.story.like;

import kwu.raccoondomain.persistence.domain.story.like.StoryLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryLikeRepository extends JpaRepository<StoryLike,Long>, StoryLikeCustomRepository {
}
