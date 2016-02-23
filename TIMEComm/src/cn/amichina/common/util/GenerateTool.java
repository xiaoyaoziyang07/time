package cn.amichina.common.util;

import java.util.List;

public class GenerateTool {

	public static String idGenerator(String prefix,List<String> idList){
		StringBuilder policyId = new StringBuilder(prefix);
		String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		do{
			char[] rands = new char[4];
			for (int i = 0; i < rands.length; i++) {
				int rand = (int) (Math.random() * a.length());
				rands[i] = a.charAt(rand);
				policyId.append(rands[i]);
			}
		}while(idList.contains(policyId.toString()));
		return policyId.toString();
	}
}
