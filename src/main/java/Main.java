import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        final CarDealership carDealership = new CarDealership();
        Runnable shopping = carDealership::sellAuto;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // пул потоков покупателей
        ExecutorService threadPool = Executors.newFixedThreadPool(4, x -> new Thread(x, "Покупатель " + atomicInteger.addAndGet(1)));
        for (int i = 1; i < 11; i++) {
         threadPool.submit(shopping);
        }
        // поток производителя
        new Thread(null, carDealership::acceptAuto, "Производитель ").start();
        threadPool.shutdown();
    }
}
