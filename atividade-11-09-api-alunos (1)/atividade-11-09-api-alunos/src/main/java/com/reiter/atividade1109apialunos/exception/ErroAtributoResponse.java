package com.reiter.atividade1109apialunos.exception;

import java.time.Instant;
import java.util.List;

public record ErroAtributoResponse(
	int status,
	String mensagem,
	Instant timestamp,
	List<ErroAtributo> erroAtributo
	
	){}