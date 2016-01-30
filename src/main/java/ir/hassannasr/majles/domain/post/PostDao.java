package ir.hassannasr.majles.domain.post;

import core.dao.GenericDao;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.user.User;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */

public interface PostDao extends GenericDao<Post, Long> {
    public EntityManager getEntityManager();

    List<Post> loadPostByUserIds(List<Long> userIds, Long older, Long newer, Integer count);

    List<Post> loadMyPost(Long userId, Long older, Long newer, Integer count);

    List<Post> loadSponsoredPosts(SubHozeh hozeh, Long older, Long newer, Integer count);

    public String getLastLinkPublished(User user);

    List<Post> findPostsWithIds(List<Long> originalPostIds);
}
