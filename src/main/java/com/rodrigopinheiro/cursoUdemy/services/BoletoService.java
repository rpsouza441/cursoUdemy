package com.rodrigopinheiro.cursoUdemy.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.rodrigopinheiro.cursoUdemy.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date isntantePedido) {
		Calendar cal =Calendar.getInstance();
		cal.setTime(isntantePedido);
		cal.add(Calendar.DAY_OF_WEEK, 7);
		
	}

}
