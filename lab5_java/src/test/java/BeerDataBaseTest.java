import lab5.BeerDataBase;
import lab5.Beer;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BeerDataBaseTest {
    private BeerDataBase dataBase;

    @Before
    public void init(){
        dataBase = new BeerDataBase();
        Beer beer = new Beer("some beer", 4.2f);
        dataBase.add(beer);
    }
    @Test
    public void testFind(){
        Optional<Beer> empty = Optional.empty();
        String name = "some beer";
        assertThat(dataBase.find(name)).isNotEqualTo(empty);
    }
    @Test
    public void tetFindNonexistent(){
        Optional<Beer> empty = Optional.empty();
        String name = "nonexistent beer";
        assertThat(dataBase.find(name)).isEqualTo(empty);
    }
    @Test
    public void testRemoveNonexistent(){
        String name = "nonexistent beer";
        assertThatThrownBy(() -> dataBase.remove(name)).hasMessage("Beer doesn't exist.");
    }
    @Test
    public void testAddExisting(){
        String name = "some beer";
        Beer beer = new Beer(name, 3.6f);
        assertThatThrownBy(() -> dataBase.add(beer)).hasMessage("Beer " + beer.getName() + " already exists.");
    }
}