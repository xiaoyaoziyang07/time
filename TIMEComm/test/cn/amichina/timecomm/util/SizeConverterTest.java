package cn.amichina.timecomm.util;
public class SizeConverterTest {
	public static void main(String[] args) {
		Long long_val =10240032l;
		String s =SizeConverter.B.convert(long_val.floatValue());
		System.out.println(s);
		Double d =long_val.doubleValue()/1024/1024;
		System.out.println(d);
		Object obj =d;
		String str =String.format("%.2f", obj);
		System.out.println(str);
	}
}
