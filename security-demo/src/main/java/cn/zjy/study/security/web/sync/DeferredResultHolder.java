/**
 * 
 */
package cn.zjy.study.security.web.sync;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 17:39
 *  @Description: 线程之间的桥梁
 */
@Component
public class DeferredResultHolder {

	/**
	 * String 处理的具体信息
	 * DeferredResult<String> 获取订单的处理结果
	 */
	private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}
	
}
