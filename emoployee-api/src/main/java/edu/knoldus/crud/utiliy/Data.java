package edu.knoldus.crud.utiliy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    int id;
    @JsonProperty("fname")
    String first_name;
    String last_name;
    @JsonProperty("av")
    String avatar;
    String age;
}
