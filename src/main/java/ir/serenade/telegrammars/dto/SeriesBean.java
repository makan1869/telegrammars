package ir.serenade.telegrammars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeriesBean {
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;
    @JsonProperty("data")
    private Long[] data;


    public SeriesBean(String name, String color, Long[] data) {
        this.name = name;
        this.color = color;
        this.data = data;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long[] getData() {
        return data;
    }

    public void setData(Long[] data) {
        this.data = data;
    }
}