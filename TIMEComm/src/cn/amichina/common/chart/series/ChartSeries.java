package cn.amichina.common.chart.series;

/******************************
 * 
 * 2015/10/30 liyang 创建文件
 * 
 *****************************/
import java.util.List;

public class ChartSeries {

	private String seriesname;
	private List<String> value;

	public ChartSeries(String seriesname, List<String> value) {
		this.seriesname = seriesname;
		this.value = value;
	}

	public List<String> getValue(){
		return value;
	}
	
	public String getSeriesname(){
		return seriesname;
	}
	/**
	 * @return fusionchart中data部分对应的JSON字符串
	 */
	@Override
	public String toString() {
		if(this.value==null||this.value.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();

		sb.append("{\"seriesname\":\"");
		sb.append(seriesname);
		sb.append("\",");
		sb.append("\"data\":[");
			
		for (int i = 0; i < value.size(); i++) {
			sb.append("{\"value\":\"");
			String v = value.get(i);
			if (v != null && !v.equals("0.00")) {
				sb.append(value.get(i));
			}
			sb.append("\"}");
			if (i == value.size() - 1) {
				sb.append("]}");
				break;
			}
			sb.append(",");
		}

		return sb.toString();
	}
	
	public String[] getMaxMinValue() throws NumberFormatException{
		String[] result = new String[2];
		String max=null;
		String min=null;
		for(String s:this.value){
			if(!s.equals("null")){
				max=s;
				min=s;
				break;
			}
		}
		try{
			for(String s:this.value){
				if(!s.equals("null")){
					if(Long.parseLong(s)>Long.parseLong(max)){
						max=s;
					}
					if(Long.parseLong(s)<Long.parseLong(min)){
						min=s;
					}
				}
			}
		}catch(Exception e){
			throw new RuntimeException();
		}
		result[0]=max;
		result[1]=min;
		return result;
	}
	
	
	public double[] getRatio(){
		double xysum = 0;
		double xsum = 0;
		double ysum = 0;
		double x2sum = 0;
		int flag = 0;
		for(int i=0;i<value.size();i++){
			if(!value.get(i).equals("")){
				double y = Double.parseDouble(value.get(i));
				xysum += i * y;
				xsum += i;
				ysum += y;
				x2sum += i * i;
				flag++;
			}
		}
		double[] ab = new double[2];
		ab[0] = (flag * xysum - xsum * ysum) / (flag * x2sum - xsum * xsum);
		ab[1] = ysum / flag - ab[0] * xsum / flag;
		return ab;
	}
	
}
