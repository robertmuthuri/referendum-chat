
package com.example.referendum_chat.models;

import java.util.List;

import com.example.referendum_chat.models.Datum;
import com.example.referendum_chat.models.Link;
import com.example.referendum_chat.models.Metadata;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoDBCitiesSearchResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GeoDBCitiesSearchResponse() {
    }

    /**
     * 
     * @param data
     * @param links
     * @param metadata
     */
    public GeoDBCitiesSearchResponse(List<Datum> data, List<Link> links, Metadata metadata) {
        super();
        this.data = data;
        this.links = links;
        this.metadata = metadata;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
