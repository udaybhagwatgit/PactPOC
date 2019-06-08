package com.student.test;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.PactVerificationResult;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.model.MockProviderConfig;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;
import com.student.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static au.com.dius.pact.consumer.ConsumerPactRunnerKt.runConsumerTest;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceApplicationTests {

	PactSpecVersion pactSpecVersion;
	@Autowired StudentService studentService;

	@Test
	public void testGETWithPactDSLJsonBody() {

		//Version of Pact to be used
		MockProviderConfig config = MockProviderConfig.createDefault(this.pactSpecVersion.V3);

		//Setting up the mock data
		DslPart body = new PactDslJsonBody()
				.stringType("name", "Mike")
				.integerType("age", 10)
				.integerType("marks", 90);

		//We tell Pact that - given the mock data created above, use the API endpoint, and test the Contract
		RequestResponsePact pact = ConsumerPactBuilder
				.consumer("JunitDSLJsonBodyConsumer")
				.hasPactWith("ExampleProvider")
				.given("")
				.uponReceiving("Student Request")
				.path("/students/getStudents")
				.method("GET")
				.willRespondWith()
				.status(200)
				.body(body)
				.toPact();

		//as of now, only the "name" is ready and thus please check if the name is "Mike" or not
		PactVerificationResult result = runConsumerTest(pact, config, mockServer -> {
			studentService.setBackendURL(mockServer.getUrl(), true);
			Student std = studentService.getStudent();
			assertEquals(std.getName(), "Mike");
		});

	}
}