package com.prestes.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestes.model.Cliente;
import com.prestes.model.ClienteRepository;
import com.prestes.model.Endereco;
import com.prestes.model.EnderecoRepository;
import com.prestes.service.ClienteService;
import com.prestes.service.ViaCepService;


@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		 Optional<Cliente> cliente = clienteRepository.findById(id);
		 return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		
		salvarClienteComCep(cliente);
		
	}

	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(()-> {
			
			Endereco novoEnder = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEnder);
			return novoEnder;});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> cliente1 = clienteRepository.findById(id);
		if(cliente1.isPresent()) {
			salvarClienteComCep(cliente);
		}
		
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

}
