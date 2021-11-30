package com.filereader.LogFileReader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class LogFileReaderApplication implements CommandLineRunner {
	final Logger log = LoggerFactory.getLogger(getClass());


	public static void main(String[] args) {
		//SpringApplication.run(LogFileReaderApplication.class, args);
		SpringApplication app = new SpringApplication(LogFileReaderApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		try
		{
			Path path = Paths.get(getClass().getClassLoader()
					.getResource("logFile.txt").toURI());

			Stream<String> lines = Files.lines(path);
			String data = lines.collect(Collectors.joining("\n"));
			log.info(data);
			lines.close();
		}catch(NullPointerException e){
			log.error(e.getMessage());
		}
	}
}
