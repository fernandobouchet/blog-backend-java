package com.fernandobouchet.blog.mappers;

import com.fernandobouchet.blog.domain.PostStatus;
import com.fernandobouchet.blog.domain.dtos.TagDto;
import com.fernandobouchet.blog.domain.entities.Post;
import com.fernandobouchet.blog.domain.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagDto toTagResponse(Tag tag);

   List<TagDto> toTagResponseList(List<Tag> tags);

   @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
       if(posts == null) {
           return 0;
       }
       return (int) posts.stream()
               .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
               .count();
   }

}
