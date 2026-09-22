package com.reiter.atividade1109apialunos.exception;

import java.time.Instant;

public record ErroResponse(

	int status,
	String mensagem,
	Instant timestamp
	){}

