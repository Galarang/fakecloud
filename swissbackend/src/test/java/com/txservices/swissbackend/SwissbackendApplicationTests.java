package com.txservices.swissbackend;

import com.txservices.swissbackend.rest.ConnectionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class SwissbackendApplicationTests {

	@Autowired
	private ConnectionController connectionController;

	@Test
	void contextLoads() throws Exception{
		assertThat(connectionController).isNotNull();
	}

}
