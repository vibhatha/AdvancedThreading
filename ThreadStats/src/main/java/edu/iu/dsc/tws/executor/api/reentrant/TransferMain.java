package edu.iu.dsc.tws.executor.api.reentrant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TransferMain {

    public static void main(String[] args) {
        List<Future<?>> futures = new ArrayList<Future<?>>();
        ExecutorService service = Executors.newFixedThreadPool(3);
        final Account from = new Account();
        from.setBalance(20000);
        from.setName("Shamik Mitra");
        final Account to = new Account();
        to.setName("Samir Mitra");
        final AccountTransfer transfer = new AccountTransfer();
        Runnable a = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                transfer.transfer(from, to, 200);
                System.out.println(Thread.currentThread().getName() + " says :: Transfer successfull");
            }
        };
        Runnable b = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                transfer.transfer(to, from, 1000);
                System.out.println(Thread.currentThread().getName() + " says :: Transfer successfull");
            }
        };
        for (int i = 0; i < 4; i++) {
            Future<?> a1 = service.submit(a);
            futures.add(a1);
            Future<?> b1 = service.submit(b);
            futures.add(b1);
        }

        for(Future<?> f : futures) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        boolean allDone = true;
        for(Future<?> future : futures) {
            allDone &= future.isDone();
        }

        if (allDone) {
            System.out.println("All Transactions Completed ...");
            service.shutdown();
        }


    }
}
