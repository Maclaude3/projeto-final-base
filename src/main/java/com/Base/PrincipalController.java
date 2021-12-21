package com.Base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping(path = "/demo") 
@CrossOrigin(origins = "*")
public class PrincipalController {

    @Autowired 
    private UsuarioRepository pRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNovoPaciente(@RequestParam String nome, @RequestParam String status,@RequestParam String local,@RequestParam String entrada,@RequestParam String fim, @RequestParam String saida, @RequestParam String inicio) {

      
        Usuario p = new Usuario();
        p.setNome(nome);
        p.setStatus(status);
        p.setLocal(local);
        p.setEntrada(entrada);
        p.setInicio(inicio);
        p.setFim(fim);
        p.setSaida(saida);
        pRepository.save(p);
        return "Gravado com sucesso!.";    
      
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Usuario> getAllPacientes() {
        return pRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PutMapping(path = "/atualizar/{id}")
    public @ResponseBody String atualizarPaciente(@PathVariable int id,@RequestParam String nome,@RequestParam String local,@RequestParam String fim,@RequestParam String saida,@RequestParam String inicio,@RequestParam String entrada,@RequestParam String status){
        Usuario p = pRepository.findById(id);
        p.setNome(nome);
        p.setStatus(status);
        p.setLocal(local);
        p.setEntrada(entrada);
        p.setInicio(inicio);
        p.setFim(fim);
        p.setSaida(saida);
        pRepository.save(p);
        return "Atualizado com sucesso!"; 
    }
    @DeleteMapping(path = "/excluir/{id}")
    public @ResponseBody String excluirPaciente(@PathVariable int id){
        pRepository.deleteById(id);
        return "Excluido com sucesso!";
    }
    
}
