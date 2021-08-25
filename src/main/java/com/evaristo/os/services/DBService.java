package com.evaristo.os.services;

import com.evaristo.os.domain.Cliente;
import com.evaristo.os.domain.OS;
import com.evaristo.os.domain.Tecnico;
import com.evaristo.os.domain.enuns.Prioridade;
import com.evaristo.os.domain.enuns.Status;
import com.evaristo.os.repositories.ClienteRepository;
import com.evaristo.os.repositories.OsRepository;
import com.evaristo.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OsRepository osRepository;

    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Elton Evaristo","080.941.760-08", "(83) 98888-8888");
        Tecnico t2 = new Tecnico(null, "Linus Torvalds","641.760.040-88", "(83) 98888-8855");
        Cliente c1 = new Cliente(null, "Betina Campos", "199.946.300-59", "(83) 98888-7777");
        OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1,t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
