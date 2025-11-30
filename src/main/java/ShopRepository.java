public class ShopRepository {

    private Product[] products = new Product[0];

    public Product[] findAll() {
        return products;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int id) {
        Product target = findById(id);
        if (target == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        Product[] tmp = new Product[products.length - 1];
        int copyIndex = 0;

        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyIndex] = product;
                copyIndex++;
            }
        }
        products = tmp;
    }

    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException(
                    "Element with id: " + product.getId() + " already exists"
            );
        }
        products = addToArray(products, product);
    }

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }
}
