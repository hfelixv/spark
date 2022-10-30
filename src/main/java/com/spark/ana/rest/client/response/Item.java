package com.spark.ana.rest.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
//    <item_data>
//      |-  <pid>234075400000541</pid>
//          <barcode>42737</barcode>
    private //Long
             String mms_id;
    private String title;
    private String author;

//    @SuppressWarnings("unchecked")
    @JsonProperty("bib_data")
    private void unpackNested(Map<String,Object> bib_data) {
        this.mms_id = (String) bib_data.get("mms_id");
        this.title = (String) bib_data.get("title");
        this.author = (String)bib_data.get("author");

    }
}
