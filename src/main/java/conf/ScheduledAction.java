package conf;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.ProductDao;
import ninja.scheduler.Schedule;

import java.util.concurrent.TimeUnit;

@Singleton
public class ScheduledAction {

        @Inject
        ProductDao productDao;

        @Schedule(delay = 5, initialDelay = 5, timeUnit = TimeUnit.SECONDS)
        public void decideWinner() {
            productDao.pickWinner();
            System.out.println("Hello");
        }

        @Schedule(delay = 5, initialDelay = 5, timeUnit = TimeUnit.SECONDS)
        public void setActive(){
            productDao.startBid();
            System.out.println("Bid is Active set");
        }

}
