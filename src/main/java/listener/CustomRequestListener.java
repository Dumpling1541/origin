package listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class CustomRequestListener implements ServletRequestListener {
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		
		ServletRequest request = sre.getServletRequest();
		request.setAttribute("millis", System.currentTimeMillis());
	//	System.out.println("[SERVER] requestInitialized");
	}
	
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	//	System.out.println("[SERVER] requestDestroyed");
		
	}

}
