package org.einnovator.notifications.client.web;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.einnovator.notifications.client.NotificationsClient;
import org.einnovator.notifications.client.model.ErrorReport;
import org.einnovator.notifications.client.support.Messages;

public class ReportErrorController {
	
	private static final String DEAULT_REDIRECT_URI = "/";

	private final Log logger = LogFactory.getLog(getClass());

	  
    @Autowired
    private NotificationsClient notificationClient;
 
    @PostMapping("/send-error")
    public String sendError(ErrorReport error, Principal principal, RedirectAttributes redirectAttributes) {
    	logger.error("error:" + error);
		try {
			notificationClient.reportError(error, null);
			logger.debug("reportError:" + error);
	    	redirectAttributes.addFlashAttribute(Messages.ATTRIBUTE_INFO, Messages.MSG_ERROR_SENT);
		} catch (RuntimeException e) {
			logger.error("reportError:" + e);
	    	redirectAttributes.addFlashAttribute(Messages.ATTRIBUTE_ERROR, Messages.MSG_ERROR_FAIL);

		}
        return "redirect:" + redirectUri();
    }
    

	
	protected String redirectUri() {
		return DEAULT_REDIRECT_URI;
	}

}
