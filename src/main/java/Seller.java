import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.*;

public class Seller {

    private final CarDealership carDealership;
    ReentrantLock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();

    public Seller(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public void manufacturingAuto() {
        int counterAuto = 0;
        int maxCountAuto = 10;
        long productionTimeAuto = 3000;
        lock.lock();
        try {
            while (counterAuto != maxCountAuto) {
                carDealership.getAutos().add(new Auto());
                counterAuto++;
                System.out.println(currentThread().getName() + "изготовил " + carDealership.getAutos().get(0));
                condition.signalAll();
                condition.awaitNanos(productionTimeAuto);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sellAuto() {
        long sellTimeOut = 1000;
        lock.lock();
        try {
            while (carDealership.getAutos().size() == 0) {
                System.out.println(currentThread().getName() + " зашел в автосалон");
                System.out.println("Машин нет!");
                condition.await();
            }
            System.out.println(currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        lock.unlock();
    }
        carDealership.getAutos().remove(0);
        try {
            condition.awaitNanos(sellTimeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
