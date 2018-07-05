package com.hjy.entity;

import java.util.Date;

/**
 * @author : yanzz
 *
 **/
public class FileOperation {

    private String fileHash;
    private Long  userId;
    private Date gmtCreate;
    private byte status;


    public FileOperation() {
    }

    public FileOperation(String fileHash, Long userId, Date gmtCreate, byte status) {
        this.fileHash = fileHash;
        this.userId = userId;
        this.gmtCreate = gmtCreate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "FileOperation{" +
                "fileHash='" + fileHash + '\'' +
                ", userId=" + userId +
                ", gmtCreate=" + gmtCreate +
                ", status=" + status +
                '}';
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
