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
					bw.write(comms[1]+": ");
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
			System.out.println();
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
			bw.newLine();
			System.out.println();
			
			//*****************Master表新增**********************************
			
			count = 1;
			br = new BufferedReader(new FileReader(csv));
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					// model
//					<td><input name="name" type="text" value="${m001_model.name}" /></td>
					String[] instr = strs[0].split("\"");
					/*if(instr[1].toLowerCase().contains("date")||instr[1].toLowerCase().contains("time")){
//						<td><input name="birthday" type="text" value="<fmt:formatDate value="${model.birthday}"
//						pattern="yyyy-MM-dd" type="date" dateStyle="long" />" /></td>
						bw.write("\t\t\t\t\t<td><input name=\""+instr[1].toLowerCase()+"\" type=\"text\" value=\"<fmt:formatDate Value=\"${model."+instr[1].toLowerCase()+"}\"");
						bw.newLine();
						System.out.println("<td><input name=\""+instr[1].toLowerCase()+"\" type=\"text\" value=\"<fmt:formatDate Value=\"${model."+instr[1].toLowerCase()+"}\"");
						bw.write("\t\t\t\t\t\t\tpattern=\"yyyy-MM-dd\" type=\"date\" dateStyle=\"long\" />\" /></td> ");
						bw.newLine();
						System.out.println("pattern=\"yyyy-MM-dd\" type=\"date\" dateStyle=\"long\" />\" /></td> ");
					}else{*/
						bw.write("\t\t\t\t\t<td><input name=\"" + instr[1].toLowerCase()+"\" type=\"text\" value=\"${model." + instr[1].toLowerCase()+ "}\"/></td>");
						System.out.println("<td><input name=\"" + instr[1].toLowerCase()+"\" type=\"text\" value=\"${model." + instr[1].toLowerCase()+ "}\"/></td>");
						bw.newLine();
//					}
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();
			
			//master用的 tb循环
			count = 1;
			boolean mflag = true;
			br = new BufferedReader(new FileReader(csv));
			//td cout:
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				if (count == 0) {
					// model
					String[] instr = strs[0].split("\"");
					if(mflag){
						mflag = false;
//						<td><input type="checkbox" name="checkboxId" value='<c:out value="${model.id}"/>' /></td>
						bw.write("\t\t\t\t\t<td><input type=\"checkbox\" name=\"checkboxId\" value=\'<c:out value=\"${model."
								+ instr[1].toLowerCase() + "}\"/>\' /></td>");
						bw.newLine();
						System.out
								.println("<td><input type=\"checkbox\" name=\"checkboId\" value=\'<c:out value=\"${model."
										+ instr[1].toLowerCase()
										+ "}\"/>\' /></td>");
					}
					bw.write("\t\t\t\t\t<td><c:out value=\"${model." + instr[1].toLowerCase() + "}\"/></td>");
					System.out.println("<td><c:out value=\"${model." + instr[1].toLowerCase() + "}\"/></td>");
					bw.newLine();
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();
			
			//title For Master  无&#10版
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
					bw.write(comms[1]+": ");
					System.out.print(comms[1] + ": ");
					// model
					String[] instr = strs[0].split("\"");
					bw.write("${model." + instr[1].toLowerCase() + "}");
					System.out.println("${model." + instr[1].toLowerCase()
							+ "}");
					bw.newLine();
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();
			
			//master  update-add  jsp
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
//					bw.write(comms[1]+": ");
//					System.out.print(comms[1] + ": ");
					// model
					String[] instr = strs[0].split("\"");
//					<tr >
//						<td>id</td>
//						<td><input id="" name="" type="text" value="" ></td>  value="${model.day_id}"/
//					</tr>
					bw.write("\t\t\t\t<tr>");
					bw.newLine();
					System.out.println("<tr>");
					bw.write("\t\t\t\t\t<td>"+comms[1]+"</td>");
					bw.newLine();
					System.out.println("<td>"+comms[1]+"</td>");
					bw.write("\t\t\t\t\t<td><input id=\""+instr[1].toLowerCase()+"\" name=\""+instr[1].toLowerCase()+"\" type=\"text\" value=\"${model."+instr[1].toLowerCase()+"}\" /></td>");
					bw.newLine();
					System.out.println("<td><input id=\""+instr[1].toLowerCase()+"\" name=\""+instr[1].toLowerCase()+"\" type=\"text\" value=\"${model."+instr[1].toLowerCase()+"}\" /></td>");
					bw.write("\t\t\t\t<tr>");
					bw.newLine();
					System.out.println("<tr>");
				} else {
					count--;
				}
			}
			bw.newLine();
			bw.newLine();
			bw.write("************************************************************");
			bw.newLine();
			System.out.println();
			
			
			//master add/update js部分
			count = 1;
			br = new BufferedReader(new FileReader(csv));
			while ((line = br.readLine()) != null) {
//			4	var day_id = document.getElementById("day_id").value.replace(/\s/ig,'');
//			4	if(day_id == ""){
//			5		alert("输入x");day_id
//			5		document.getElementById("day_id").focus();
//			5		return;
//			4	}
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
//					bw.write(comms[1]+": ");
//					System.out.print(comms[1] + ": ");
					// model
					String[] instr = strs[0].split("\"");
					String lstr = instr[1].toLowerCase();
					//1
					bw.write(getTab(4)+"var "+lstr+" = document.getElementById(\""+lstr+"\").value.replace(/\\s/ig,\'\');");
					bw.newLine();
					System.out.println("var "+lstr+" = document.getElementById(\""+lstr+"\").value.replace(/\\s/ig,\'\');");
					//2
					bw.write(getTab(4)+"if("+lstr+" == \"\"){");
					bw.newLine();
					System.out.println("if("+lstr+" == \"\"){");
					//3
					bw.write(getTab(5)+"alert(\"请输入"+comms[1]+"\");");
					bw.newLine();
					System.out.println("alert(\"请输入"+comms[1]+"\");");
					//4
					bw.write(getTab(5)+"document.getElementById(\""+lstr+"\").focus();");
					bw.newLine();
					System.out.println("document.getElementById(\""+lstr+"\").focus();");
					//5
					bw.write(getTab(5)+"return;");
					bw.newLine();
					System.out.println("return;");
					//6
					bw.write(getTab(4)+"}");
					bw.newLine();
					System.out.println("}");
				} else {
					count--;
				}
			}
			bw.newLine();
			System.out.println();
			
			
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static String getTab(int num){
		String ans = "";
		for (int i = 0; i < num; i++) {
			ans+="\t";
		}
		return ans;
	}
}
