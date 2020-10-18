package com.extra.feature.service;

import com.extra.feature.dto.EmailReciver;

public interface EmailReciverService {

	public void sendTextMail(EmailReciver emailReciver);
	public void sendLinkViaMail(EmailReciver emailReciver);
	public void sendFileViaMail(EmailReciver emailReciver);
}
