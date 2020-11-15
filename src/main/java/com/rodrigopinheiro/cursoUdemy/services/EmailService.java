package com.rodrigopinheiro.cursoUdemy.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.rodrigopinheiro.cursoUdemy.domain.Pedido;

public interface EmailService {

	void sendOrderComfirmation(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
