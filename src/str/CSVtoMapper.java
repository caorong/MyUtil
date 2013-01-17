package str;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description	
 * @author caorong
 * @date 2013-1-10
 * 
 */
public class CSVtoMapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File csv = new File("C:\\1.csv"); // CSV文件

//		String[] upCases = new String[0];
		ArrayList<String> upCases = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csv));
			FileWriter fw = new FileWriter(new File("c:\\outMapper.txt"));
			BufferedWriter bw = new BufferedWriter(fw);
			// 读取直到最后一行
			String line = "";
			int count = 1;
			int newLineCount = 5;
			boolean flag = true;
			while ((line = br.readLine()) != null) {
				// StringTokenizer st = new StringTokenizer(line, ",");
				String[] strs = line.split(",");

				if (count == 0) {
//					System.out.println(strs[0]);
					String[] instr = strs[0].split("\"");
//					System.out.println(instr[1]);
					if(flag){
						flag = false;
						bw.write("\t\t<id column="+"\"" + instr[1]+"\" " + "property=\""+instr[1].toLowerCase()+"\"/>");
						System.out.println("<id column="+"\"" + instr[1]+"\" " + "property=\""+instr[1].toLowerCase()+"\"/>");
						upCases.add(instr[1]);
//						System.out.println(upCases.length);
					} else {
						bw.write("\t\t<result column="+"\"" + instr[1]+"\" " + "property=\""+instr[1].toLowerCase()+"\"/>");
						System.out.println("<result column="+"\"" + instr[1]+"\" " + "property=\""+instr[1].toLowerCase()+"\""+"/>");
						upCases.add(instr[1]);
					}
					bw.newLine();
//					if (count % newLineCount == 0) {
//						bw.newLine();
//					}
//					count++;
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();
			
			// insert
			bw.write("\t\t\tINSERT INTO xxxxxx_xxxx  (");
			System.out.print("INSERT INTO xxxxxx_xxxx  (");
			for (int i = 0; i < upCases.size()-1; i++) {
				bw.write(upCases.get(i)+", ");
				System.out.print(upCases.get(i)+", ");
			}
			bw.write(upCases.get(upCases.size()-1)+")");
			bw.newLine();
			System.out.println(upCases.get(upCases.size()-1)+")");
			
			bw.write("\t\t\tVALUES(");
			System.out.print("VALUES(");
			for (int i = 0; i < upCases.size()-1; i++) {
				bw.write("#{"+upCases.get(i).toLowerCase()+"}, ");
				System.out.print("#{"+upCases.get(i).toLowerCase()+"}, ");
			}
			bw.write("#{"+upCases.get(upCases.size()-1).toLowerCase()+"})");
			System.out.println("#{"+upCases.get(upCases.size()-1).toLowerCase()+"})");
			bw.newLine();
			bw.newLine();
			System.out.println();
			
			//queryCount
//			<if test="name!=null">
//			<![CDATA[ NAME=#{name} ]]>
//			</if>
			for (int i = 0; i < upCases.size(); i++) {
				bw.write("\t\t\t<if test=\""+upCases.get(i).toLowerCase()+"!=null\">");
				bw.newLine();
				System.out.println("<if test=\""+upCases.get(i).toLowerCase()+"!=null\">");
				bw.write("\t\t\t\t<![CDATA[ "+upCases.get(i)+"=#{"+upCases.get(i).toLowerCase()+"} ]]>");
				bw.newLine();
				System.out.println("<![CDATA[ "+upCases.get(i)+"=#{"+upCases.get(i).toLowerCase()+"} ]]>");
				bw.write("\t\t\t</if>");
				bw.newLine();
				System.out.println("</if>");
			}
			bw.newLine();
			System.out.println();
			
			//queryById
//			SELECT * FROM TCHL_DAY_SYSTEM where ID=#{id} 
			bw.write("\t\t\tSELECT * FROM xxx_xxxx where "+upCases.get(0)+"=#{"+upCases.get(0)+"} ");
			System.out.println("\t\t\tSELECT * FROM xxx_xxxx where "+upCases.get(0)+"=#{"+upCases.get(0)+"} ");
			bw.newLine();
			bw.newLine();
			
			
			//update 更新记录 
//			UPDATE SAMPLE_REG 
//			SET 
//				NAME=#{name},
//				AGE=#{age}, 
//				GENDER=#{gender}, 
//				BIRTHDAY=#{birthday}, 
//				CELLPHONE=#{cellphone} 
//			WHERE ID=#{id}
			bw.write("\t\t\t\tUPDATE xxxx_xxx");
			bw.newLine();
			System.out.println("UPDATE xxxx_xxx");
			bw.write("\t\t\t\tSET");
			bw.newLine();
			System.out.println("SET");
			for (int i = 0; i < upCases.size()-1; i++) {
				bw.write("\t\t\t\t\t"+upCases.get(i)+"=#{"+upCases.get(i).toLowerCase()+"},");
				bw.newLine();
				System.out.println(upCases.get(i)+"=#{"+upCases.get(i).toLowerCase()+"},");
			}
			bw.write("\t\t\t\t\t"+upCases.get(upCases.size()-1)+"=#{"+upCases.get(upCases.size()-1).toLowerCase()+"}");
			bw.newLine();
			System.out.println(upCases.get(upCases.size()-1)+"=#{"+upCases.get(upCases.size()-1).toLowerCase()+"}");
			bw.write("\t\t\t\tWHERE "+upCases.get(0)+"=#{"+upCases.get(0).toLowerCase()+"}");
			bw.newLine();
			System.out.println("WHERE "+upCases.get(0)+"=#{"+upCases.get(0).toLowerCase()+"}");
			bw.newLine();
			
			//delete  
//			DELETE FROM TCHL_DAY_SYSTEM WHERE ID=#{id}
			bw.write("\t\t\tDELETE * FROM xxx_xxxx WHERE "+upCases.get(0)+"=#{"+upCases.get(0).toLowerCase()+"}");
			bw.newLine();
			System.out.println("DELETE FROM xxx_xxxx WHERE "+upCases.get(0)+"=#{"+upCases.get(0).toLowerCase()+"}");
			
			
			br.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
