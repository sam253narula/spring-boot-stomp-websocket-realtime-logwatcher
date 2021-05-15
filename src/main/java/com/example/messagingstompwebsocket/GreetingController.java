package com.example.messagingstompwebsocket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Logs log() throws Exception {
		Thread.sleep(1000); // simulated delay

		String fileName = "C:\\Users\\divya\\Downloads\\Projects\\2021 Projects\\BrowserStack\\gs-messaging-stomp-websocket-main\\complete\\logs\\spring-boot-logger.log";

		try (FileInputStream fis = new FileInputStream(fileName);
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr)) {

			String line;

			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {

				sb.append(line);
				sb.append(System.lineSeparator());
			}
			return new Logs(sb.toString());
		}

	}
}
