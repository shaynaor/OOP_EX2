package File_format;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Test {

	public static void main(String[] args) {

		//String path = "C:\\Users\\User\\Desktop\\מטלה 2 מונחה\\Ex2\\data\\WigleWifi_20171201110209.csv";
//		ArrayList<String[]> ans = new ArrayList<>();
//		
//		try {
//			ans = MyFileUtils.readCSVFile(path);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		for(int i = 0 ; i < ans.size() ; i++) {
//			System.out.println(Arrays.toString(ans.get(i)));
//		}
//		String[] s =ans.get(2);
//		System.out.println(s[3]);
		
//		String path1 = "C:\\Users\\User\\Desktop\\test-IO\\shaytest.kml";
//		
//		
//		try {
//			MyFileUtils.writeKMLFile(path1, ans);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		File f = new File("C:\\Users\\User\\Desktop\\מטלה 2 מונחה\\Ex2\\data");
		System.out.println(MyFileUtils.listFilesForFolder(f));
	}
}