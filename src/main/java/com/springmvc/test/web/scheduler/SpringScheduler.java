package com.springmvc.test.web.scheduler;

import java.time.LocalTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringScheduler {
	/** * 1. 오후 05:50:00에 호출이 되는 스케쥴러 */
	@Scheduled(cron = "0 50 17 * * *")
	public void cronTest1() {
		System.out.println("오후 05:50:00에 호출이 됩니다 ");
	}

	/** * 2. 오후 05:51:00에 호출이 되는 스케쥴러 */
	@Scheduled(cron = "0 51 17 * * *")
	public void cronTest2() {
		System.out.println("오후 05:51:00에 호출이 됩니다 ");
	}
	
	/** * 3. 매분 마다 호출이 되는 스케쥴러 */
	@Scheduled(cron = "0 0/1 * * * *")
	public void cronTest3() {
		System.out.println(LocalTime.now());
	}
}
