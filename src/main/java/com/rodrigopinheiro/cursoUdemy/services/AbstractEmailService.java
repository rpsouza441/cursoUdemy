package com.rodrigopinheiro.cursoUdemy.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.rodrigopinheiro.cursoUdemy.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

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

}
