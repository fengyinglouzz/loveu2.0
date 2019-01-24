package com.zxzyyyy.loveu.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description:
 */
@Data
@Document(indexName = "blog", type = "blog") //文档
public class EsBlog implements Serializable {

    @Id //主键 还是用org.springframework.data.annotation.Id呢
    private String id;
    private String title;
    private String summary;
    private String content;

    protected EsBlog() { //JPA 规范要求 防止直接使用

    }
    public EsBlog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    @Override
    public String toString() {
        return "EsBlog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
