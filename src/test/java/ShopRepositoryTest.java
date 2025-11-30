import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    ShopRepository repo = new ShopRepository();

    @Test
    public void shouldRemoveExistingProduct() {

        Product p1 = new Product(1, "Book", 100);
        Product p2 = new Product(2, "Phone", 200);
        Product p3 = new Product(3, "TV", 300);

        repo.add(p1);
        repo.add(p2);
        repo.add(p3);

        repo.removeById(2);

        Product[] expected = {p1, p3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundException() {

        repo.add(new Product(1, "Book", 100));

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(999);
        });
    }

    @Test
    public void shouldAddNewProduct() {

        Product p1 = new Product(1, "Book", 100);

        repo.add(p1);

        Product[] expected = {p1};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsException() {

        Product p1 = new Product(1, "Book", 100);

        repo.add(p1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(new Product(1, "Duplicate", 200));
        });
    }

    @Test
    public void shouldAddProductSuccessfully() {

        Product product = new Product(1, "Phone", 10_000);

        repo.add(product);

        Product[] expected = {product};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
