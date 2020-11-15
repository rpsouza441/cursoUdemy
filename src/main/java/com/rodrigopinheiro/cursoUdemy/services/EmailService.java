package com.rodrigopinheiro.cursoUdemy.services;

import org.springframework.mail.SimpleMailMessage;

import com.rodrigopinheiro.cursoUdemy.domain.Pedido;

public interface EmailService {

	void sendOrderComfirmation(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);

}
