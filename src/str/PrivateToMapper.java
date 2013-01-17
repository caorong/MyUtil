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
public class PrivateToMapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File csv = new File("C:\\1.txt"); // CSV文件

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csv));
			FileWriter fw = new FileWriter(new File("c:\\mapper.txt"));
			BufferedWriter bw = new BufferedWriter(fw);
			// 读取直到最后一行
			String line = "";
			int count = 1;
			boolean flag = true;
			while ((line = br.readLine()) != null) {
				// StringTokenizer st = new StringTokenizer(line, ",");
				String[] strs = line.split(" ");
				// while (st.hasMoreTokens()) {
				// // 每一行的多个字段用TAB隔开表示
				// System.out.print(st.nextToken() + "\t");
				// }
				// for(int i=0;i<strs.length;i++){
				// }
//				if (count == 0) {
//					System.out.println(strs[0]);
					String[] instr = strs[2].split(";");
//					System.out.println(instr[0]);
				if(flag){
					flag = false;
					bw.write("<id column="+"\"" + instr[0].toUpperCase()+"\" " + "property=\""+instr[0].toLowerCase()+"\"/>");
					System.out.println("<id column="+"\"" + instr[0].toUpperCase()+"\" " + "property=\""+instr[0].toLowerCase()+"\"/>");
				} else {
					bw.write("<result column="+"\"" + instr[0].toUpperCase()+"\" " + "property=\""+instr[0].toLowerCase()+"\"/>");
					System.out.println("<result column="+"\"" + instr[0].toUpperCase()+"\" " + "property=\""+instr[0].toLowerCase()+"\""+"/>");
				}
					bw.newLine();
//				} else {
//					count--;
//				}
			}
			br.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
