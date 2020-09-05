package com.produtos.apirest.resources;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.produtos.apirest.repository.ProdutoRepository;
import com.produtos.apirest.models.Produto;

@RestController
@RequestMapping(value="/api")
public class ProdutoResouce {
	
		
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produto/{id}")
	public Produto listaProdutoUnico(@PathVariable(value="id") long id){
		return produtoRepository.findById(id);
	}


	@PostMapping("/produto")
	public String salvaProduto(@RequestBody Produto produto) {	
		
		
		// Variavel para ser verificado se existe registro.
		Produto repositorio = produtoRepository.findById(produto.getId());		
		
		//se existe registro E se é igual ao que esta sendo salvo.
		if (repositorio != null && repositorio.equals(produto)) {
			
			//Comparacao de data registrada com data da nova tentativa de registro.
			LocalDateTime dataRegistro = repositorio.getDataRegistro();
			LocalDateTime dataAtual = LocalDateTime.now();
			LocalDateTime dataFinal = dataRegistro.plusMinutes(10); 	//LINHA PARA ALTERACAO DO TEMPO DE ESPERA
			int compareDate = dataAtual.compareTo(dataFinal);			
			
			//Se a diferenca nao ter passado dos 10 min.
			if (compareDate < 0) {				
				return  "403 Forbidden\n\n";
			}else {
				produto.setDataRegistro(LocalDateTime.now());
				produtoRepository.save(produto);
				return "200 OK\n\n";
			}				
		}
		else {
			produto.setDataRegistro(LocalDateTime.now());
			produtoRepository.save(produto);
			return "200 OK\n\n";
		}
	}
}
