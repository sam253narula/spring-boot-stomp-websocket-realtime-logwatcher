package com.example.messagingstompwebsocket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfig {

	@Autowired
	SimpMessagingTemplate template;

	@Scheduled(fixedDelay = 3000)
	public void sendAdhocMessages() throws FileNotFoundException, IOException {
		String fileName = "C:\\Users\\divya\\Downloads\\Projects\\2021 Projects\\BrowserStack\\gs-messaging-stomp-websocket-main\\complete\\logs\\spring-boot-logger.log";
		StringBuilder sb = new StringBuilder();
		try (FileInputStream fis = new FileInputStream(fileName);
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr)) {

			String line;
		
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			
			template.convertAndSend("/topic/greetings", new Logs(sb.toString()));
		}
	}

}