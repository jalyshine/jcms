package cn.jaly.utils.caches;

import java.io.*;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 对象序列化工具
 * @author Administrator
 *
 */
public class CacheUtils {

	public static void writeCache(Object obj, String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(filePath);
		gson.toJson(obj, fileWriter);
		fileWriter.close();
	}

	public static <T> T readCache(String filePath, Class<T> cls) throws IOException{
		FileReader fileReader = new FileReader(filePath);
		Gson gson = new Gson();
		T res = gson.fromJson(fileReader, cls);
		fileReader.close();
		return res;
	}

	public static <T> List<T> readCaches(String filePath, Class<T> cls) throws IOException{
		FileReader fileReader = new FileReader(filePath);
		Gson gson = new Gson();
		List<T> res = gson.fromJson(fileReader, new ListOfJson<T>(cls));
		fileReader.close();
		return res;
	}
}
