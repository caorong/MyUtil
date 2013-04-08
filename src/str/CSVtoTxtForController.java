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
			//组中文comment
			br = new BufferedReader(new FileReader(csv));
			FileWriter fw = new FileWriter(new File("c:\\Controller.txt"));
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
					bw.write(instr+",");
					System.out.print(instr.toLowerCase() + ",");
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();

			//写进csv
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
			bw.newLine();
			System.out.println();
			
			bw.write("***********************************Master表专用********************************************");
			bw.newLine();
			//***********************************Master表专用********************************************************
			//Master的bean的值转null   
			//********全局参数************
			String beanName = "m_E313_model";
			br = new BufferedReader(new FileReader(csv));
			count = 1;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					String[] instr = strs[0].split("\"");
					// get大写的头字母
					char data[] = instr[1].toCharArray();
					String head = data[0] + "";
					String ans = head + (instr[1].substring(1, instr[1].length()).toLowerCase());
					//组
//					if (m001_model.getName().isEmpty()) {
//						m001_model.setName(null);
//					}
					bw.write("if ("+beanName+".get"+ans+"().isEmpty()) {");
					bw.newLine();
					System.out.println("if ("+beanName+".get"+ans+"().isEmpty()) {");
					bw.write("\t"+beanName+".set"+ans+"(null);");
					bw.newLine();
					bw.write("}");
					bw.newLine();
					System.out.println("\t"+beanName+".set"+ans+"(null);");
					System.out.println("}");
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();
			
			
			//将查询的内容放进map
			br = new BufferedReader(new FileReader(csv));
			count = 1;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					String[] instr = strs[0].split("\"");
					// get大写的头字母
					char data[] = instr[1].toCharArray();
					String head = data[0] + "";
					String ans = head + (instr[1].substring(1, instr[1].length()).toLowerCase());
					//组
					//map.put("name", m001_model.getName());
					bw.write("map.put(\""+ans.toLowerCase()+"\", "+beanName+".get"+ans+"());");
					bw.newLine();
					System.out.println("map.put(\""+ans.toLowerCase()+"\", "+beanName+".get"+ans+"());");
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();
			
			
			
			
			br.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
