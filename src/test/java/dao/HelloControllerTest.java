package dao;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class HelloControllerTest extends JUnitActionBase {
	@Test
	public void testAdd() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setServletPath("/hello/mvc");
		//request.addParameter("id", "1002");
		//request.addParameter("date", "2010-12-30");
		//request.setMethod("GET");

		// 执行URI对应的action
		final ModelAndView mav = super.excuteAction(request, response);
		// Assert logic
		/*Assert.assertEquals("order/add", mav.getViewName());
		String msg = (String) request.getAttribute("msg");*/
		System.out.println(mav.toString());
	}
}
