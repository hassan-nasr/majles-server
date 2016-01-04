package ir.hassannasr.majles.domain.post;

import core.dao.GenericDaoImpl;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;

import javax.persistence.EntityManager;
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

}
