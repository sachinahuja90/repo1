package nag.worldcup.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import nag.worldcup.customException.ZipCreationException;
import nag.worldcup.reporterLogger.ReportFactory;

/* Class Decription - Class contains all the functions that are common for all the application and tests     
 * Created by - Sachin Ahuja
 * Created on - 27th April
 * Modified by
 * Modified on
 * */

public class Utilities extends FileUtility{
	

	public void takeScreenShot(WebDriver driver,String screenShotPath) throws IOException {
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(screenshotFile, new File(screenShotPath));
	 }

   
   /* Function Decription - Function converts a Hashmap<String, Integer> to a Sorted Linked Hashmap in decending Order   
	 * Created by - Sachin Ahuja
	 * Created on - 11th March
	 * Modified by
	 * Modified on
	 * */
    public LinkedHashMap<String, Integer> convertMapToReverseSortedMap(HashMap<String,Integer> map) {
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		  
		 map.entrySet()
		     .stream()
		     .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		     .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		 return sortedMap;
	}

   	public void createZipFolder(Path sourceFolderPath, Path zipPath) throws Exception {
       ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
       Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() {
           public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
               zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
               Files.copy(file, zos);
               zos.closeEntry();
               return FileVisitResult.CONTINUE;
           }
       });
       zos.close();
   	}
   	
   	public String dateFormatter(Date date) {
		Format formatter = new SimpleDateFormat("mm-dd-yyyy");
	    String s = formatter.format(date);
	    return s;
	}
   	
   	public Date increamentCurrentDate(int days) {
   		LocalDate date =  LocalDate.now().plusDays(days);
   		Date date2=java.sql.Date.valueOf(date);
	    return date2;
	}
   	
   	public String getCurrentDateTime() {
   		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   		Date date = new Date();
   		
   		return dateFormat.format(date);
   	}
   	
   	public void archieveLastReports(String src) throws IOException, ZipCreationException{
   		createZipFolder(src);
   		deleteFileFlder(new File(src));
   		String archiveReportZip=src.replaceAll(ReportFactory.reportPropertyMap.get("htmlReportFolder").replace("/", "")+"", ReportFactory.reportPropertyMap.get("archieveFolder").replace("/", ""))+".zip";
   		new File(System.getProperty("user.dir")+ReportFactory.reportPropertyMap.get("archieveFolder")).mkdir();
   		copyFolder(new File(src+".zip"),new File(archiveReportZip));
   		deleteFileFlder(new File(src+".zip"));
   	}
   	
   	public Map<String, Integer> sortByValue(boolean order, Map<String, Integer> map) {
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());
				}
			}
		});
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

   	
   	  	
   	  	
   	
   	
   	
   	
   
   
}