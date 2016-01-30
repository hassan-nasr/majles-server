package ir.hassannasr.majles.domain.post;

import core.dao.GenericDaoImpl;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.user.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */
public class PostDaoImpl extends GenericDaoImpl<Post, Long> implements PostDao {
    public PostDaoImpl() {
        super(Post.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Post> loadPostByUserIds(List<Long> userIds, Long older, Long newer, Integer count) {
        if(userIds.size()==0)
            return new ArrayList<>();
        return entityManager.createNamedQuery("loadPostByUsers")
                .setParameter("userIds", userIds)
                .setParameter("older", older == null ? Long.MAX_VALUE : older)
                .setParameter("newer", newer == null ? 0 : newer)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public List<Post> loadMyPost(Long userId, Long older, Long newer, Integer count) {
        return entityManager.createNamedQuery("loadMyPosts")
                .setParameter("userId", userId)
                .setParameter("older", older == null ? Long.MAX_VALUE : older)
                .setParameter("newer", newer == null ? 0 : newer)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public List<Post> loadSponsoredPosts(SubHozeh hozeh, Long older, Long newer, Integer count) {
        return entityManager.createNamedQuery("loadSponsoredPosts")
                .setParameter("hozehId", hozeh.getId())
                .setParameter("older", older == null ? Long.MAX_VALUE : older)
                .setParameter("newer", newer == null ? 0 : newer)
                .setMaxResults(count)
                .getResultList();

    }

    public String getLastLinkPublished(User user) {
        final List<String> resultList = entityManager.createNamedQuery("lastLinkPublished").setParameter("user", user).setMaxResults(1).getResultList();
        if (resultList.size() > 0)
            return resultList.get(0);
        return null;
    }

    @Override
    public List<Post> findPostsWithIds(List<Long> originalPostIds) {
        return entityManager.createNamedQuery("findPostsWithId").setParameter("ids", originalPostIds).getResultList();
    }

}
