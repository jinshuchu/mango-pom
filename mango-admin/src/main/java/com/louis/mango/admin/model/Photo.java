package com.louis.mango.admin.model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@Setter
@Getter
public class Photo {
    private Integer id;

    private String classification;

    private String photoName;

    private String tag;

    private Double size;

    private String path;

    private String resolutionRatio;

    private Integer burnAfterReading;

    private Integer communicationStep;

    private Integer numberOfShare;

    private String additionalInfomation;

    private Integer likeNumber;

    private Integer commentsNumber;

    private Long userId;

    private Date createTime;

    public Photo() {
    }

    public Photo(Integer id, String classification, String photoName, String tag, Double size, String path, String resolutionRatio, Integer burnAfterReading, Integer communicationStep, Integer numberOfShare, String additionalInfomation, Integer likeNumber, Integer commentsNumber, Long userId, Date createTime) {
        this.id = id;
        this.classification = classification;
        this.photoName = photoName;
        this.tag = tag;
        this.size = size;
        this.path = path;
        this.resolutionRatio = resolutionRatio;
        this.burnAfterReading = burnAfterReading;
        this.communicationStep = communicationStep;
        this.numberOfShare = numberOfShare;
        this.additionalInfomation = additionalInfomation;
        this.likeNumber = likeNumber;
        this.commentsNumber = commentsNumber;
        this.userId = userId;
        this.createTime = createTime;
    }
}