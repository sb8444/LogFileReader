package com.filereader.logfilereader;


import com.filereader.logfilereader.service.DataProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class LogFileReaderApplication implements CommandLineRunner {
	final Logger log = LoggerFactory.getLogger(LogFileReaderApplication.class);

	@Autowired
	DataProcessorService dataProcessorService;


	public static void main(String[] args) {
		//SpringApplication.run(LogFileReaderApplication.class, args);
		SpringApplication app = new SpringApplication(LogFileReaderApplication.class);
		app.run(args);
	}

	/**
	 *
	 * @param args
	 * @throws Exception
	 * Reads the content of a file and converts it into a String
	 */
	@Override
	public void run(String... args) throws Exception {

		try
		{
			log.info("Reading Log File Contents...");
			Path path = Paths.get(getClass().getClassLoader()
					.getResource("logFile.txt").toURI());

			Stream<String> lines = Files.lines(path);
			String data = lines.collect(Collectors.joining("\n"));
			log.debug("File Content: "+data);
			dataProcessorService.processInputData(data);
			lines.close();
		}catch(NullPointerException | FileNotFoundException e){
			log.error("File Processing Failed due to "+e.getMessage());
		}
	}
}
