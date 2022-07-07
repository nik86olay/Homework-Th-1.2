import java.util.ArrayList;
import java.util.List;

public class CarDealership {
    //  Продавец
    Seller seller = new Seller(this);
    List<Auto> autos = new ArrayList<>(10);

    public void sellAuto() {
        seller.sellAuto();
    }

    public void acceptAuto() {
        seller.manufacturingAuto();
    }

    List<Auto> getAutos() {
        return autos;
    }
}
