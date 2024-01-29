package kr.letech.study.springBoot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import kr.letech.study.boot.Application;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
