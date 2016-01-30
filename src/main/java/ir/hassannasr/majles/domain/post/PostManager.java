package ir.hassannasr.majles.domain.post;

import core.service.GenericManager;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.user.User;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by hassan on 02/11/2015.
 */
public interface PostManager extends GenericManager<Post, Long> {

    List<Post> loadPostByUserIds(List<Long> userIds, Long older, Long newer, Integer count);

    List<Post> loadMyPost(Long userId, Long older, Long newer, Integer count);

    List<Post> loadSponsoredPosts(SubHozeh hozeh, Long older, Long newer, Integer count);

    Post save(Post post, InputStream uploadedInputStream, Long price);

    String getLastLinkPublished(User user);

    Map<Long, Post> getPostMap(List<Long> originalPostIds);
}
