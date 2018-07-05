package com.hjy.entity;/**
 * @desciption : ${description}
 * @author : ${user}
 * @time : --
 */

import java.util.Date;

/**
 * @author : yanzz
 * @-- 文件创建实体
 **/
public class FileCreation {

    private String fileHash;
    private Long userId;
    private Date gmtDate;
    private Integer downloadCount;
    private Integer collectCount;
    private Integer likeCount;

    @Override
    public String toString() {
        return "FileCreation{" +
                "fileHash='" + fileHash + '\'' +
                ", userId=" + userId +
                ", gmtDate=" + gmtDate +
                ", downloadCount=" + downloadCount +
                ", collectCount=" + collectCount +
                ", likeCount=" + likeCount +
                '}';
    }

    public FileCreation() {
    }

    public FileCreation(String fileHash, Long userId, Date gmtDate, Integer downloadCount, Integer collectCount, Integer likeCount) {
        this.fileHash = fileHash;
        this.userId = userId;
        this.gmtDate = gmtDate;
        this.downloadCount = downloadCount;
        this.collectCount = collectCount;
        this.likeCount = likeCount;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getGmtDate() {
        return gmtDate;
    }

    public void setGmtDate(Date gmtDate) {
        this.gmtDate = gmtDate;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
