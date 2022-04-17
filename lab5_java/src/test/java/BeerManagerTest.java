import lab5.BeerManager;
import lab5.BeerDataBase;
import lab5.Beer;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class BeerManagerTest {
    private BeerManager manager;
    private BeerDataBase dataBase;

    @Before
    public void init(){
        dataBase = mock(BeerDataBase.class);
        manager = new BeerManager(dataBase);
    }

    @Test
    public void testAdd(){
        String name = "some beer";
        assertThat(manager.add(name, 4.2f)).isEqualTo("Beer" + name + "was successfully added.");
    }
    @Test
    public void testAddExisting(){
        String name = "some beer";
        Beer beer = new Beer(name, 3.6f);
        doThrow(new IllegalArgumentException("Beer already exists.")).when(dataBase).add(any(Beer.class));
        assertThat(manager.add("some beer", 3.6f)).isEqualTo("Beer already exists.");
    }
    @Test
    public void testFind(){
        String name = "some beer";
        Beer beer = new Beer(name, 4.2f);
        doReturn(Optional.of(beer)).when(dataBase).find(name);
        assertThat(dataBase.find(name)).isEqualTo(Optional.of(beer));
    }
    @Test
    public void testFindNonexistent(){
        String name = "nonexistent beer";
        doReturn(Optional.empty()).when(dataBase).find(name);
        assertThat(manager.find(name)).isEqualTo("No matching beers found.");
    }
    @Test
    public void testRemove(){
        String name = "some beer";
        assertThat(manager.remove(name)).isEqualTo("Beer" + name + "was successfully removed.");
    }
    @Test
    public void testRemoveNonexistent(){
        String name = "nonexistent beer";
        doThrow(new IllegalArgumentException("Beer doesn't exist.")).when(dataBase).remove(name);
        assertThat(manager.remove(name)).isEqualTo("Beer doesn't exist.");
    }
}
