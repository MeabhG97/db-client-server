package meabh.DTO;

import java.time.LocalDate;
import java.util.List;

public class Album {
    private int id;
    private String name;
    private int artistId;
    private LocalDate release;
    private List<String> producers;
    
    public Album(int id, String name, int artistId, LocalDate release, List<String> producers) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.release = release;
        this.producers = producers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public List<String> getProducers() {
        return producers;
    }

    public void setProducers(List<String> producers) {
        this.producers = producers;
    }

    @Override
    public String toString() {
        return "Album [id=" + id + ", name=" + name + ", artistId=" + artistId + ", release=" + release + ", producers="
                + producers + "]";
    }
}
