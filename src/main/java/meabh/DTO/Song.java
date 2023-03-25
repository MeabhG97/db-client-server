package meabh.DTO;

public class Song {
    private int id;
    private String name;
    private int albumId;
    private int length;
    
    public Song(int id, String name, int albumId, int length) {
        this.id = id;
        this.name = name;
        this.albumId = albumId;
        this.length = length;
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
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Song [id=" + id + ", name=" + name + ", albumId=" + albumId + ", length=" + length + "]";
    }
}
