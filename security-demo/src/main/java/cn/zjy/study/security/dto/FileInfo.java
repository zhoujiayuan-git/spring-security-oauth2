/**
 * 
 */
package cn.zjy.study.security.dto;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 16:27
 *  @Description: 文件对象
 */
public class FileInfo {
	
	public FileInfo(String path){
		this.path = path;
	}
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
