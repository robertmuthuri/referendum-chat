
package com.example.referendum_chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("currentOffset")
    @Expose
    private Integer currentOffset;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Metadata() {
    }

    /**
     * 
     * @param totalCount
     * @param currentOffset
     */
    public Metadata(Integer currentOffset, Integer totalCount) {
        super();
        this.currentOffset = currentOffset;
        this.totalCount = totalCount;
    }

    public Integer getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(Integer currentOffset) {
        this.currentOffset = currentOffset;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
