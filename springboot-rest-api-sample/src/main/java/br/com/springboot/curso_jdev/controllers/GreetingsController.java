package br.com.springboot.curso_jdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso.jdev.model.Usuario;
import br.com.springboot.curso_jdev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
	//mvn cleas package
	
	//Usar da Interface
	@Autowired /* Injeção de dependência*/
	private UsuarioRepository usuarioRepository; 
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
		return nome;
	}
    
    @GetMapping(value = "listatodos")
    @ResponseBody//Retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	//Retorna a List em json
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody
    //Passar o Obj que vai ser salva no BD
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    //? posso retornar <String> e ou <Usuario>
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não informado para atualização", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    //Verbo para deletar
    @DeleteMapping(value = "delete")
    @ResponseBody
    //Passa o parametro
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("user deletado com sucesso", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    //Passa o parametro
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser")Long iduser){
    	//Não esquece do .get()
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    //Passa o parametro
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){
    	//Não esquece do .get()
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Usuario>> (usuario, HttpStatus.OK);
    }
}
