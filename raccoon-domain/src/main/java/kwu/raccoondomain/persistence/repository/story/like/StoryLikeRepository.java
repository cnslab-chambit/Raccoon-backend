package kwu.raccoondomain.persistence.repository.story.like;

import kwu.raccoondomain.persistence.domain.story.StoryLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryLikeRepository extends JpaRepository<StoryLike,Long>, StoryLikeCustomRepository {
}
