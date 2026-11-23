package ua.edu.coursework.eco.model;

public class Location {
    private Long id;
    private String code;
    private String name;
    private String region;

    public Location() {
    }

    public Location(Long id, String code, String name, String region) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.region = region;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = code; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}
