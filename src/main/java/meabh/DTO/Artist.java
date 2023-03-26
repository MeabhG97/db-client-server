package meabh.DTO;

import java.time.LocalDate;
import java.util.List;

public class Artist {
    private int id;
    private String name;
    private LocalDate formation;
    private String origin;
    private List<String> members;

    public Artist(String name, LocalDate formation, String origin, List<String> members) {
        this.name = name;
        this.formation = formation;
        this.origin = origin;
        this.members = members;
    }

    public Artist(int id, String name, LocalDate formation, String origin, List<String> members) {
        this.id = id;
        this.name = name;
        this.formation = formation;
        this.origin = origin;
        this.members = members;
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

    public LocalDate getFormation() {
        return formation;
    }

    public void setFormation(LocalDate formation) {
        this.formation = formation;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<String> getMembers() {
        return members;
    }

    public String getMembersAsString(){
        String memberString = "";
        for(String member : members){
            if(memberString.length() == 0){
                memberString += member;
            }
            else{
                memberString += ",";
                memberString += member;
            }
        }
        return memberString;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + ", formation=" + formation + ", origin=" + origin + ", members="
                + members + "]";
    }
}
