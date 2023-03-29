package meabh.Cache;

import java.util.HashSet;
import java.util.Set;

public class ArtistCache {
    private Set<Integer> cache;

    public ArtistCache(){
        cache = new HashSet<>();
    }

    public boolean isIdInDatabase(int id){
        if(cache.contains(id)){
            return true;
        }
        return false;
    }

    public void addId(int id){
        if(!isIdInDatabase(id)){
            cache.add(id);
        }
    }

    public void deleteId(int id){
        if(isIdInDatabase(id)){
            cache.remove(id);
        }
    }
}
