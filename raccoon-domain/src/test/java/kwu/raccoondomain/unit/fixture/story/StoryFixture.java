package kwu.raccoondomain.unit.fixture.story;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;

public class StoryFixture {

    private StoryFixture(){
    }

    public static Builder builder(){return new Builder();}
    public static class Builder{
        private Long id;
        private UserProfile userProfile;
        private String contents;
        private Long likeCount;
        private String imageUrl;

        public Builder id(Long id){
            this.id = id;
            return this;
        }
        public Builder userProfile(UserProfile userProfile){
            this.userProfile = userProfile;
            return this;
        }
        public Builder contents(String contents){
            this.contents = contents;
            return this;
        }
        public Builder likeCount(Long likeCount){
            this.likeCount = likeCount;
            return this;
        }
        public Builder imageUrl(String imageUrl){
            this.imageUrl = imageUrl;
            return this;
        }
        public Story build(){
            return new Story(
                    id,
                    userProfile,
                    contents,
                    likeCount,
                    imageUrl
            );
        }
    }

}
