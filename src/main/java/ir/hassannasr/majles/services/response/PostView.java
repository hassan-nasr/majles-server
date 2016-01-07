package ir.hassannasr.majles.services.response;

import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.post.Post;

import java.util.Date;

/**
 * Created by hassan on 02/01/2016.
 */
public class PostView {
    Boolean deleted = false;
    Long id;
    Date publishDate = new Date();
    UserSimpleView user;
    String text = "";
    String imageId;
    String link;
    Long basePostId;
    Long likeCount = 0L;
    Long subHozehId;
    Boolean sponsored = false;

    public PostView(Post post, Candid candid) {
        deleted = post.getDeleted();
        id = post.getId();
        publishDate = post.getPublishDate();
        user = new UserSimpleView(post.getUser(), candid);
        text = post.getText();
        imageId = post.getImageId();
        link = post.getLink();
        basePostId = post.getBasePostId();
        likeCount = post.getLikeCount();
        subHozehId = post.getSubHozehId();
        sponsored = post.getSponsored();

    }

    public Long getBasePostId() {
        return basePostId;
    }

    public void setBasePostId(Long basePostId) {
        this.basePostId = basePostId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    public void setSponsored(Boolean sponsored) {
        this.sponsored = sponsored;
    }

    public Long getSubHozehId() {
        return subHozehId;
    }

    public void setSubHozehId(Long subHozehId) {
        this.subHozehId = subHozehId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserSimpleView getUser() {
        return user;
    }

    public void setUser(UserSimpleView user) {
        this.user = user;
    }
}
