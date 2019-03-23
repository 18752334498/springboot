package springboot;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.yucong.SpringBootApp;

@SpringBootTest(classes = SpringBootApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPhoneService {

	private RestTemplate template = new RestTemplate();
	private final static String url = "http://localhost:8888/phone/buyDirect?id=4673106495781814231&count=1";
	private CountDownLatch count = new CountDownLatch(200);

	@Test
	public void testBuyDirect() throws Exception {
		for (int i = 0; i < 200; i++) {
			new Thread(new Animal()).start();
			System.out.println("----: " + i);
			count.countDown();
		}
		Thread.sleep(4000);
	}

	class Animal implements Runnable {
		@Override
		public void run() {
			try {
				count.await();
				System.out.println(template.getForEntity(url, String.class).getBody());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
