package ir.hassannasr.majles.domain.post;

import core.dao.GenericDao;
import core.service.GenericManagerImpl;
import ir.hassannasr.majles.domain.ImageManager;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.user.User;
import ir.hassannasr.majles.domain.user.UserDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hassan on 02/11/2015.
 */
public class PostManagerImpl extends GenericManagerImpl<Post, Long> implements PostManager {


    String postImageLocation;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PostDao postDao;

    public PostManagerImpl(GenericDao<Post, Long> genericDao) {
        super(genericDao);
    }

    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public String getPostImageLocation() {
        return postImageLocation;
    }

    public void setPostImageLocation(String postImageLocation) {
        this.postImageLocation = postImageLocation;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Post> loadPostByUserIds(List<Long> userIds, Long older, Long newer, Integer count) {
        return postDao.loadPostByUserIds(userIds, older, newer, count);
    }

    @Override
    public List<Post> loadMyPost(Long userId, Long older, Long newer, Integer count) {
        return postDao.loadMyPost(userId, older, newer, count);
    }

    @Override
    public List<Post> loadSponsoredPosts(SubHozeh hozeh, Long older, Long newer, Integer count) {
        return postDao.loadSponsoredPosts(hozeh, older, newer, count);
    }

    @Override
    public Post save(Post post, InputStream uploadedInputStream, Long price) {
        if (uploadedInputStream != null) {
            final String imageId = RandomStringUtils.random(20, false, true);
            post.setImageId(imageId);
            ImageManager.writeToFile(uploadedInputStream, imageId);
        }
        if (post.getSponsored()) {
            post.getUser().setVerifiedCredit(post.getUser().getVerifiedCredit() - price);
            post.setUser(userDao.save(post.getUser()));
        }
        return save(post);
    }

    @Override
    public String getLastLinkPublished(User user) {
        return postDao.getLastLinkPublished(user);
    }

    @Override
    public Map<Long, Post> getPostMap(List<Long> originalPostIds) {
        if (originalPostIds == null || originalPostIds.size() == 0)
            return new HashMap<>();
        List<Post> post = postDao.findPostsWithIds(originalPostIds);
        Map<Long, Post> postMap = new HashMap<>();
        for (Post candid : post) {
            postMap.put(candid.getId(), candid);
        }
        return postMap;
    }


}
