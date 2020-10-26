package sopaDeLetras;

import java.util.List;

public interface Actions {
    public Boolean insert(Object obj);
    public Boolean delete(Object obj);
    public Boolean update(Object obj);
    public List<?> select(String column, String value); 
    public List<?> all(); 
}
