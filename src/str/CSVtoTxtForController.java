package str;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description
 * @author caorong
 * @date 2013-1-10
 * 
 */
public class CSVtoTxtForController {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		File csv = new File("C:\\1.csv"); // CSV文件
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csv));
			FileWriter fw = new FileWriter(new File("c:\\comment.txt"));
			BufferedWriter bw = new BufferedWriter(fw);
			// 读取直到最后一行
			String line = "";
			int count = 1;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					String instr = null;
					// 因为  NUMBER(24,4)
					if (strs.length == 7) {
						instr = strs[6];
					} else {
						instr = strs[5];
					}
					bw.write(instr);
					System.out.print(instr.toLowerCase() + ",");
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();

			br = new BufferedReader(new FileReader(csv));
			// 写lstt_model.get(i).getxxx()
			count = 1;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					String[] instr = strs[0].split("\"");
					// get大写的头字母
					char data[] = instr[1].toCharArray();
					String head = data[0] + "";
					String ans = head
							+ (instr[1].substring(1, instr[1].length())
									.toLowerCase());
					System.out.println("lstt_model.get(i).get" + ans + "(),");
					bw.write("lstt_model.get(i).get" + ans + "(),");
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
