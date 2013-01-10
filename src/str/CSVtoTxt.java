package str;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @description
 * @author caorong
 * @date 2013-1-9
 */
public class CSVtoTxt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File csv = new File("C:\\1.csv"); // CSV文件

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csv));
			FileWriter fw = new FileWriter(new File("c:\\private.txt"));
			BufferedWriter bw = new BufferedWriter(fw);
			// 读取直到最后一行
			String line = "";
			int count = 1;
			while ((line = br.readLine()) != null) {
				// StringTokenizer st = new StringTokenizer(line, ",");
				String[] strs = line.split(",");
				// while (st.hasMoreTokens()) {
				// // 每一行的多个字段用TAB隔开表示
				// System.out.print(st.nextToken() + "\t");
				// }
				// for(int i=0;i<strs.length;i++){
				// }
				if (count == 0) {
//					System.out.println(strs[0]);
					String[] instr = strs[0].split("\"");
//					System.out.println(instr[1]);
					bw.write("private String " + instr[1].toLowerCase() + ";");
					System.out.println("private String " + instr[1].toLowerCase() + ";");
					bw.newLine();
				} else {
					count--;
				}
			}
			br.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
