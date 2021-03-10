package lab2_sona.sb.dtos;

public class MovieDto {
    private long id;
    private String name;
    private String director;

    public MovieDto(long id, String name, String director) {
        this.id = id;
        this.name = name;
        this.director = director;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
