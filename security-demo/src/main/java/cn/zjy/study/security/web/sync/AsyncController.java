package cn.zjy.study.security.web.sync;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 17:01
 *  @Description: 多线程异步处理
 */
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @Description 线程同步处理
     * @Author  zjy
     * @Date   2021/3/11 17:07
     */
    /*@RequestMapping("/order")
    public String order() throws Exception {
        logger.info("主线程开始");

        Thread.sleep(1000);

		logger.info("主线程返回");

		return "success";
    }*/

	/**
	 * @Description 线程异步处理
	 * @Author  zjy
	 * @Date   2021/3/11 17:07
	 */
	@RequestMapping("/order")
	public DeferredResult<String> order() throws Exception {
		logger.info("主线程开始");

		// 随机生成订单号
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);

		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);

//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(1000);
//				logger.info("副线程返回");
//				return null;
//			}
//		};

		logger.info("主线程返回");

		return result;
	}

}
