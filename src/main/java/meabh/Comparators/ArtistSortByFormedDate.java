package meabh.Comparators;

import java.util.Comparator;

import meabh.DTO.Artist;

public class ArtistSortByFormedDate implements Comparator<Artist> {
    //Returns 0 if equal, Positive if o1 is later than o2, Negative if o1 is earlier than o2
    @Override
    public int compare(Artist o1, Artist o2) {
        return o1.getFormation().compareTo(o2.getFormation());
    }
}