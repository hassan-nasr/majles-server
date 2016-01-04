package ir.hassannasr.majles.domain.post;

import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hassan on 01/01/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "loadPostByUsers", query = "from Post p where p.user.id in :userIds and (deleted = false or deleted is null) and p.id>:newer and p.id<:older  order by p.id desc "),
        @NamedQuery(name = "loadMyPosts", query = "from Post p where p.user.id = :userId and (deleted = false or deleted is null) and p.id>:newer and p.id<:older order by p.id desc "),
        @NamedQuery(name = "loadSponsoredPosts", query = "from Post p where sponsored=true and ( subHozehId = :hozehId or subHozehId is null )and (deleted = false or deleted = NULL) and p.id>:newer and p.id<:older order by p.id desc "),
})
public class Post extends BaseObject {
    Boolean deleted = false;
    Long id;
    Date publishDate = new Date();
    User user;
    String text = "";
    String imageId;
    String link;
    Long basePostId;
    Long likeCount = 0L;
    Long subHozehId;
    Boolean sponsored = false;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean delete) {
        this.deleted = delete;
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

    public Long getBasePostId() {
        return basePostId;
    }

    public void setBasePostId(Long basePostId) {
        this.basePostId = basePostId;
    }

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Lob
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "basePostId=" + basePostId +
                ", id=" + id +
                ", publishDate=" + publishDate +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return !(id != null ? !id.equals(post.id) : post.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
