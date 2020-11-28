package sopaDeLetras.dao;

import java.util.List;

public interface Actions {
    public int insert(Object obj);
    public Boolean delete(Object obj);
    public Boolean update(Object obj);
    public Object select(int id); 
    public List<?> all(); 
}
