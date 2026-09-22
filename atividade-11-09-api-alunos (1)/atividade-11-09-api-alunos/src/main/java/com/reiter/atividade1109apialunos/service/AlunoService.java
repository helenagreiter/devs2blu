package com.reiter.atividade1109apialunos.service;

import org.springframework.stereotype.Service;

import com.reiter.atividade1109apialunos.dto.AlunoRequest;
import com.reiter.atividade1109apialunos.dto.AlunoResponse;
import com.reiter.atividade1109apialunos.exception.AlunoNaoEncontradoException;
import com.reiter.atividade1109apialunos.exception.EmailCadastradoException;
import com.reiter.atividade1109apialunos.model.Aluno;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final List<Aluno> alunos;
    private int id = 1;

    public AlunoService() {
        alunos = new ArrayList<Aluno>();

        /*
        antes do alunorequest e antes do cadastrarAluno
        alunos.add(new Aluno(1, "Charles", "charles@email.com", "1234", LocalDate.of(2000, 8, 26), 8.0));
        alunos.add(new Aluno(2, "Joana", "joana@email.com", "5678", LocalDate.of(1999, 5, 18), 9.0));
        alunos.add(new Aluno(3, "Diana", "diana@email.com", "9101", LocalDate.of(1996, 3, 9), 9.2));
        */


    }

    public List<AlunoResponse> listarAlunos() {
        List<Aluno> alunosModel = alunos;
        List<AlunoResponse> alunosResponse = new ArrayList<>();

        for (Aluno a : alunosModel) {
            alunosResponse.add(new AlunoResponse
                    (a.getId(), a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia()));
        }
        return alunosResponse;
    }

    public AlunoResponse obterAlunoPorId(int id) {

        for (Aluno a : alunos) {
            if (a.getId() == id) {
                return new AlunoResponse(
                        id,
                        a.getNome(),
                        a.getEmail(),
                        a.getDataNascimento(),
                        a.getMedia()
                );
            }
        }
     throw new AlunoNaoEncontradoException("Aluno não entcontrado");
    }

    public AlunoResponse cadastrarAluno(AlunoRequest req) {
    	for (Aluno a : alunos) {
       if (a.getEmail().equalsIgnoreCase(req.getEmail())) {
    	   throw new EmailCadastradoException("Email já existe");
    		   
    	   }
       }
    	
    	alunos.add
                (new Aluno(id,
                        req.getNome(),
                        req.getEmail(),
                        req.getSenha(),
                        req.getDataNascimento(),
                        req.getMedia()));

        id++;

        Aluno alunoCadastrado = alunos.getLast();

        return new AlunoResponse(
                alunoCadastrado.getId(),
                alunoCadastrado.getNome(),
                alunoCadastrado.getEmail(),
                alunoCadastrado.getDataNascimento(),
                alunoCadastrado.getMedia());

        /*
         * ou
         * Aluno alunoCadastrado = alunos.get(alunos.size() - 1);
         */
    }


    public AlunoResponse atualizarAluno(int id, @Valid AlunoRequest req) {

        for (Aluno a : alunos) {

            if (req.getEmail().equalsIgnoreCase(a.getEmail())
                    && id != a.getId()) {

                throw new EmailCadastradoException("Email já cadastrado");
    

    		}
    	}

        for (Aluno a : alunos) {
            if (a.getId() == id) {
                a.setNome(req.getNome());
                a.setEmail(req.getEmail());
                a.setDataNascimento(req.getDataNascimento());
                a.setMedia(req.getMedia());
                
                return new AlunoResponse(
                        a.getId(),
                        a.getNome(),
                        a.getEmail(),
                        a.getDataNascimento(),
                        a.getMedia());

            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado");

    }

    
    public void excluirAluno(int id) {
    	for (Aluno a : alunos) {
    		if (a.getId() == id) {
    			alunos.remove(a);
    			return;
    		}
    	}
    }
}
