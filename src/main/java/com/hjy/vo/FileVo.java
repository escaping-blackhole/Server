package com.hjy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author : yanzz
 * @createTime : 04 13:31
 * @description :
 **/
public class FileVo {

    private String filename;
    private String fileHash;
    private Long userId;
    @JsonFormat
    private Date gmtDate;
    private Integer downloadCount;
    private Integer collectCount;
    private Integer likeCount;
    private byte status;

    public FileVo() {
    }

    public FileVo(String filename, String fileHash, Long userId, Date gmtDate, Integer downloadCount, Integer collectCount, Integer likeCount, byte status) {
        this.filename = filename;
        this.fileHash = fileHash;
        this.userId = userId;
        this.gmtDate = gmtDate;
        this.downloadCount = downloadCount;
        this.collectCount = collectCount;
        this.likeCount = likeCount;
        this.status = status;
    }

    public Date getGmtDate() {

        return gmtDate;
    }

    public void setGmtDate(Date gmtDate) {
        this.gmtDate = gmtDate;
    }

    @Override
    public String toString() {
        return "FileVo{" +
                "filename='" + filename + '\'' +
                ", fileHash='" + fileHash + '\'' +
                ", userId=" + userId +
                ", gmtDate=" + gmtDate +
                ", downloadCount=" + downloadCount +
                ", collectCount=" + collectCount +
                ", likeCount=" + likeCount +
                ", status=" + status +
                '}';
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
