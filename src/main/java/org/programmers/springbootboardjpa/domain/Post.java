package org.programmers.springbootboardjpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.programmers.springbootboardjpa.domain.user.User;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    //TODO: POST 내용에 대한 유효성 검증, 금칙어 기능 추가

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Post(Long postId, String title, String content, User user) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    @Id
    @GeneratedValue
    @Getter
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Post edit(Post postAfterFormApplied) {
        this.title = postAfterFormApplied.getTitle();
        this.content = postAfterFormApplied.getContent();
        return this;
    }
}