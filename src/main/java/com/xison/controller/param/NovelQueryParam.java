package com.xison.controller.param;

import java.io.Serializable;

/**
 * NovelQueryParam
 *
 * @author chenjj2
 * @version V1.0
 * @since 2017-11-14 11:02
 */
public class NovelQueryParam implements Serializable {

    private static final long serialVersionUID = 8802191846054557030L;

    private Integer pageNo = 0;

    private Integer pageSize = 10;

    private String title;

    private String author;

    private Long ltePrice;

    private Long gtePrice;

    private Integer lteWordCount;

    private Integer gteWordCount;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getLtePrice() {
        return ltePrice;
    }

    public void setLtePrice(Long ltePrice) {
        this.ltePrice = ltePrice;
    }

    public Long getGtePrice() {
        return gtePrice;
    }

    public void setGtePrice(Long gtePrice) {
        this.gtePrice = gtePrice;
    }

    public Integer getLteWordCount() {
        return lteWordCount;
    }

    public void setLteWordCount(Integer lteWordCount) {
        this.lteWordCount = lteWordCount;
    }

    public Integer getGteWordCount() {
        return gteWordCount;
    }

    public void setGteWordCount(Integer gteWordCount) {
        this.gteWordCount = gteWordCount;
    }
}