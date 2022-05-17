package com.fet.telemedicine.backend.chat.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tools.zip.ZipFile;

public class ZipUtil {

    public static void main(String[] a) {

	String folder = "C:/Users/Alan/Desktop";

	File srcFile = new File(folder + "/source");
	File targetZip = new File(folder + "/target.zip");
	File extractDir = new File(folder + "/extract");

	try {
	    // 壓縮
	    ZipUtil.makeZip(srcFile, targetZip);
	    // 解壓縮
	    ZipUtil.unzipFile(targetZip, extractDir);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 解壓縮
     * 
     * @param zipfile
     * @param extractDir
     * @return
     */
    public static boolean unzipFile(File zipfile, File extractDir) {
	try {
	    unZip(zipfile, extractDir.getAbsolutePath());
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
     * 建立資料夾
     * 
     * @param directory
     * @param subDirectory
     */
    private static void createDirectory(String directory, String subDirectory) {
	String dir[];
	File fl = new File(directory);
	try {
	    if (subDirectory == "" && fl.exists() != true)
		fl.mkdir();
	    else if (subDirectory != "") {
		dir = subDirectory.replace('\\', '/').split("/");
		for (int i = 0; i < dir.length; i++) {
		    File subFile = new File(directory + File.separator + dir[i]);
		    if (subFile.exists() == false)
			subFile.mkdir();
		    directory += File.separator + dir[i];
		}
	    }
	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}
    }

    /**
     * 解壓縮主程式
     * 
     * @param zipFileName
     * @param outputDirectory
     * @throws Exception
     */
    private static void unZip(File ZIPFile, String outputDirectory) throws Exception {
	try {
	    ZipFile zipFile = new ZipFile(ZIPFile);
	    Enumeration e = zipFile.getEntries();
	    org.apache.tools.zip.ZipEntry zipEntry = null;
	    createDirectory(outputDirectory, "");
	    // if(!outputDirectory.exists()) outputDirectory.mkdirs();

	    while (e.hasMoreElements()) {
		zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
		System.out.println("unziping " + zipEntry.getName());
		if (zipEntry.isDirectory()) {
		    String name = zipEntry.getName();
		    name = name.substring(0, name.length() - 1);
		    File file = new File(outputDirectory + File.separator + name);
		    file.mkdir();
		    System.out.println("創建立目錄：" + outputDirectory + File.separator + name);
		} else {
		    String fileName = zipEntry.getName();
		    fileName = fileName.replace('\\', '/');
		    if (fileName.indexOf("/") != -1) {
			createDirectory(outputDirectory, fileName.substring(0, fileName.lastIndexOf("/")));
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		    }

		    File file = new File(outputDirectory + File.separator + zipEntry.getName());
		    file.createNewFile();
		    InputStream in = zipFile.getInputStream(zipEntry);
		    FileOutputStream out = new FileOutputStream(file);

		    byte[] by = new byte[1024];
		    int c;
		    while ((c = in.read(by)) != -1) {
			out.write(by, 0, c);
		    }
		    out.close();
		    in.close();
		}
	    }
	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}

    }

    /**
     * 建立 zip 檔
     * 
     * @param srcFile
     * @param targetZip
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void makeZip(File srcFile, File targetZip) throws IOException, FileNotFoundException {
	ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetZip));
	String dir = "";
	recurseFiles(srcFile, zos, dir);
	zos.close();
    }

    /**
     * 壓縮 主程式
     * 
     * @param file
     * @param zos
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static void recurseFiles(File file, ZipOutputStream zos, String dir)
	    throws IOException, FileNotFoundException {
	// 目錄
	if (file.isDirectory()) {
	    System.out.println("找到資料夾:" + file.getName());
	    dir += file.getName() + File.separator;
	    String[] fileNames = file.list();
	    if (fileNames != null) {
		for (int i = 0; i < fileNames.length; i++) {
		    recurseFiles(new File(file, fileNames[i]), zos, dir);
		}
	    }
	}
	// Otherwise, a file so add it as an entry to the Zip file.
	else {
	    System.out.println("壓縮檔案:" + file.getName());

	    byte[] buf = new byte[1024];
	    int len;

	    // Create a new Zip entry with the file's name.
	    dir = dir.substring(dir.indexOf(File.separator) + 1);
	    ZipEntry zipEntry = new ZipEntry(dir + file.getName());
	    // Create a buffered input stream out of the file
	    // we're trying to add into the Zip archive.
	    FileInputStream fin = new FileInputStream(file);
	    BufferedInputStream in = new BufferedInputStream(fin);
	    zos.putNextEntry(zipEntry);
	    // Read bytes from the file and write into the Zip archive.
	    while ((len = in.read(buf)) >= 0) {
		zos.write(buf, 0, len);
	    }
	    // Close the input stream.
	    in.close();
	    // Close this entry in the Zip stream.
	    zos.closeEntry();
	}
    }
}
