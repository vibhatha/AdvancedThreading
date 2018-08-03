package edu.iu.dsc.tws.executor.api.reentrant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
* Reference : https://dzone.com/articles/java-concurrency-reentrant-lock-1
**/


public class AccountTransfer {

    private ReentrantLock lock = new ReentrantLock();

    public void transfer(Account from, Account to, Integer amount) {
        boolean transfer = false;
        try {
            if (lock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " says accuire lock");
                boolean flag = from.debit(amount);
                if (flag) {
                    to.credit(amount);
                }
                System.out.println(Thread.currentThread().getName() + " :: " + from.getName() + " says :: now balance is " + from.getBalance());
                System.out.println(Thread.currentThread().getName() + " :: " + to.getName() + " says :: now balance is " + to.getBalance());
                transfer = true;
            } else {
                System.out.println(Thread.currentThread().getName() + " says fail to accuire both lock Try again");
                transfer(from, to, amount);//try again
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (transfer) {
                lock.unlock();
            }
        }
    }
}
