package com.fet.telemedicine.backend.chat.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PrintUtil {

    public static String formatAsTable(List<String> headers, List<List<String>> rows) {
	int[] maxLengths = new int[rows.get(0).size()];
	for (List<String> row : rows) {
	    for (int i = 0; i < row.size(); i++) {
		maxLengths[i] = Math.max(maxLengths[i], headers.get(i).length());
		maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
	    }
	}

	StringBuilder result = new StringBuilder();
	// build row format
	int space = 2;
	StringBuilder formatBuilder = new StringBuilder();
	for (int maxLength : maxLengths) {
	    formatBuilder.append("%-").append(maxLength + space).append("s");
	}
	String format = formatBuilder.toString();
	// make line
	int headerLineLength = Arrays.stream(maxLengths).map(maxLength -> maxLength + space).sum();
	String headerLine = StringUtils.repeat("-", headerLineLength);
	// append header with lines
	result.append(headerLine).append("\n");
	result.append(String.format(format, headers.toArray(new String[0]))).append("\n");
	result.append(headerLine).append("\n");
	// append rows
	for (List<String> row : rows) {
	    result.append(String.format(format, row.toArray(new String[0]))).append("\n");
	}
	// append line
	result.append(headerLine).append("\n");
	return result.toString();
    }

    public static void printTable(List<String> headers, List<List<String>> rows) {
	System.out.println(formatAsTable(headers, rows));
    }

}
