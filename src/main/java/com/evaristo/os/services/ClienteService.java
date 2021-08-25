package com.evaristo.os.services;


import com.evaristo.os.domain.Cliente;
import com.evaristo.os.domain.Pessoa;
import com.evaristo.os.dtos.ClienteDTO;
import com.evaristo.os.repositories.ClienteRepository;
import com.evaristo.os.repositories.PessoaRepository;
import com.evaristo.os.services.exceptions.DataIntegratyViolationException;
import com.evaristo.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo : " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {

        return repository.findAll();
    }

    public Cliente create (ClienteDTO objDTO){
        if(findByCPF(objDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
     Cliente oldObj = findById(id);

        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
     Cliente obj = findById(id);
        if(obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Pessoa possui Ordens de Serviço, não pode ser deletado!");
        }
      repository.deleteById(id);
    }

    private Pessoa findByCPF(ClienteDTO objDTO) {
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if(obj != null) {
            return obj;
        }
        return null;
    }

}
