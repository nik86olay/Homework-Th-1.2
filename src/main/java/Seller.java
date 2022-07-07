import static java.lang.Thread.*;

public class Seller {

    private final CarDealership carDealership;

    public Seller(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public synchronized void manufacturingAuto() {
        int counterAuto = 0;
        int maxCountAuto = 10;
        int productionTimeAuto = 3000;
        try {
            while (counterAuto != maxCountAuto) {
                carDealership.getAutos().add(new Auto());
                counterAuto++;
                System.out.println(currentThread().getName() + "изготовил " + carDealership.getAutos().get(0));
                notifyAll();
                wait(productionTimeAuto);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sellAuto() {
        int sellTimeOut = 1000;
        try {
            while (carDealership.getAutos().size() == 0) {
                System.out.println(currentThread().getName() + " зашел в автосалон");
                System.out.println("Машин нет!");
                wait();
            }
            System.out.println(currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        carDealership.getAutos().remove(0);
        try {
            wait(sellTimeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
