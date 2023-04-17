package kwu.raccoondomain.persistence.repository.story;

import kwu.raccoondomain.persistence.domain.story.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story,Long> {

}
