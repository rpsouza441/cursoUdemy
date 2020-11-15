package com.rodrigopinheiro.cursoUdemy.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.rodrigopinheiro.cursoUdemy.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine engine;
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendOrderComfirmation(Pedido pedido) {
		SimpleMailMessage smm = PrepareSimpleMailMessageFromPedido(pedido);
		sendEmail(smm);
	}

	protected SimpleMailMessage PrepareSimpleMailMessageFromPedido(Pedido pedido) {

		SimpleMailMessage m = new SimpleMailMessage();
		m.setTo(pedido.getCliente().getEmail());
		m.setFrom(sender);
		m.setSubject("Pedido Confirmado! Código: " + pedido.getId());
		m.setSentDate(new Date(System.currentTimeMillis()));
		m.setText(pedido.toString());
		return m;
	}

	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return engine.process("email/confirmacaoPedido", context);

	}
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		MimeMessage mm;
		try {
			mm = prepareSimpleMailMessageFromPedido(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderComfirmation(obj);
		}
	}

	protected MimeMessage prepareSimpleMailMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(message, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return message;
	}

}
