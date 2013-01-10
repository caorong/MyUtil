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
public class CSVtoTxtForjsp {

	public static void main(String[] args) {
		File csv = new File("C:\\1.csv"); // CSV文件

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csv));
			FileWriter fw = new FileWriter(new File("c:\\jspTitle.txt"));
			BufferedWriter bw = new BufferedWriter(fw);
			// 读取直到最后一行
			String line = "";
			int count = 1;
			// <th>
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					String comm = null;
					// 因为 NUMBER(24,4)
					if (strs.length == 7) {
						comm = strs[6];
					} else {
						comm = strs[5];
					}
					String[] comms = comm.split("\"");
					bw.write("<th>" +comms[1]+"</th>");
					System.out.println("<th>" +comms[1]+"</th>");
					bw.newLine();
				} else {
					count--;
				}
			}
			bw.newLine();
			// title
			count = 1;
			br = new BufferedReader(new FileReader(csv));
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					String comm = null;
					// 因为 NUMBER(24,4)
					if (strs.length == 7) {
						comm = strs[6];
					} else {
						comm = strs[5];
					}
					String[] comms = comm.split("\"");
					bw.write(comms[1]);
					System.out.print(comms[1] + ": ");
					// model
					String[] instr = strs[0].split("\"");
					bw.write("${model." + instr[1].toLowerCase() + "}&#10");
					System.out.println("${model." + instr[1].toLowerCase()
							+ "}&#10");
					bw.newLine();
				} else {
					count--;
				}
			}
			bw.newLine();
//			br.close();

			count = 1;
			br = new BufferedReader(new FileReader(csv));
			//td cout:
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					// model
					String[] instr = strs[0].split("\"");
					bw.write("<td><c:out value=\"${model." + instr[1].toLowerCase() + "}\"/></td>");
					System.out.println("<td><c:out value=\"${model." + instr[1].toLowerCase() + "}\"/></td>");
					bw.newLine();
				} else {
					count--;
				}
			}
			
			
			
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
