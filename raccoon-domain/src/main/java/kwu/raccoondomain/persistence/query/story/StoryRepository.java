package kwu.raccoondomain.persistence.query.story;

import kwu.raccoondomain.persistence.domain.story.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story,Long> {

}
