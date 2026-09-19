package com.reiter.atividade1109apialunos.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reiter.atividade1109apialunos.dto.AlunoRequest;
import com.reiter.atividade1109apialunos.dto.AlunoResponse;
import com.reiter.atividade1109apialunos.model.Aluno;
import com.reiter.atividade1109apialunos.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarAlunos() {
        return ResponseEntity.status(HttpStatus.OK).body (service.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> obterAlunoPorId(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterAlunoPorId(id));
    }
   
    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrarAluno(@Valid @RequestBody AlunoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body( service.cadastrarAluno(req));

    }
 
    @PutMapping("/{id}")
    public AlunoResponse atualizarAluno(
            @PathVariable int id,
            @Valid @RequestBody AlunoRequest request) {

        return service.atualizarAluno(id, request);

    }

    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable int id) {

        service.excluirAluno(id);

    }
  


    

    /*
    * public void cadastrarAluno(@RequestBody AlunoRequest req) {
        service.cadastrarAluno(req);
    }
    * */

}
