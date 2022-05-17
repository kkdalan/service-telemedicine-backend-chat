package com.fet.telemedicine.backend.chat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

public class CommandUtil {

    public static void runWindowsCommand(String command) throws Exception {
	// -- Windows --
	ProcessBuilder processBuilder = new ProcessBuilder();

	// Run a command
	processBuilder.command("cmd.exe", "/c", command);
//	processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

	startRunningCommand(processBuilder);
    }

    public static void runWindowsBatFile(String batPath) throws Exception {
	// -- Windows --
	ProcessBuilder processBuilder = new ProcessBuilder();

	// Run a bat file
	// processBuilder.command("C:\\Users\\mkyong\\hello.bat");
	processBuilder.command(batPath);

	startRunningCommand(processBuilder);
    }

    public static void runShellCommand(String command) throws Exception {
	// -- Linux --
	ProcessBuilder processBuilder = new ProcessBuilder();

	// Run a shell command
//		processBuilder.command("bash", "-c", "ls /home/mkyong/");
	processBuilder.command("bash", "-c", command);

	// Run a shell script
//		 processBuilder.command("path/to/hello.sh");

	startRunningCommand(processBuilder);

    }

    public static void runShellScript(String scriptPath) throws Exception {
	// -- Linux --
	ProcessBuilder processBuilder = new ProcessBuilder();

	// Run a shell script
//		 processBuilder.command("path/to/hello.sh");
	processBuilder.command(scriptPath);

	startRunningCommand(processBuilder);
    }

    private static void startRunningCommand(ProcessBuilder processBuilder) {

	try {
	    Process process = processBuilder.start();
	    StringBuilder output = new StringBuilder();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

	    String line;
	    while ((line = reader.readLine()) != null) {
		output.append(line + "\n");
	    }

	    int exitVal = process.waitFor();
	    if (exitVal == 0) {
		System.out.println("[ CMD ] - Running command => " + StringUtils.join(processBuilder.command(), " "));
		System.out.println(output);
//		System.exit(0);
	    } else {
		// abnormal...
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
