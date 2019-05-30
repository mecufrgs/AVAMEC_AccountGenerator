package edu.avamec.accountgenerator.service.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.avamec.accountgenerator.repository.*;
import edu.avamec.accountgenerator.data.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorService implements IGeneratorService {

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    IMunicipioRepository municipioRepository;

    @Autowired
    IPaisRepository paisRepository;

    @Autowired
    ITelefoneRepository telefoneRepository;

    @Autowired
    IEstadoRepository estadoRepository;

    private static String ufrgs = "ufrgs2019";
    private static String email = "@ufrgs.edu.br";

    @Override
    public ResponseEntity<?> criar(String nomeGerador) {
        String geradorFinal = getGerador(nomeGerador);

        Long numeroGerador = getNumeroGerador(geradorFinal);

        Municipio municipioBD = getMunicipio();

        Pais paisBD = getPais();

        List<Usuario> usuarios = gerarUsuarios(numeroGerador, geradorFinal, nomeGerador, municipioBD, paisBD);

        List<Usuario> usuariosSalvos = salvarNoAVAMEC(usuarios);

        return new ResponseEntity<>(usuariosSalvos, HttpStatus.OK);
    }

    private String getGerador(String seed){
        return seed.toLowerCase()
                .replace(" ", "")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ã", "a")
                .replace("õ", "o")
                .replace("â", "a")
                .replace("ê", "e")
                .replace("î", "i")
                .replace("ô", "o")
                .replace("û", "u").concat(ufrgs);
    }

    private Long getNumeroGerador(String gerador){
        Long numeroGerador = 0L;

        Usuario usuarioBD = usuarioRepository.findFirstByNomeGeradorOrderByNumeroGeradorDesc(gerador);

        if(usuarioBD != null){
            numeroGerador = usuarioBD.getNumeroGerador() + 1L;
        }

        return numeroGerador;
    }

    private Municipio getMunicipio(){
        Municipio municipioBD = municipioRepository.findMunicipioById(1L);

        if(municipioBD == null){
            Estado estado = new Estado();
            estado.setId(1L);
            estadoRepository.save(estado);

            municipioBD = new Municipio();
            municipioBD.setId(1L);
            municipioBD.setEstado(estado);

            municipioRepository.save(municipioBD);
        }

        return municipioBD;
    }

    private Pais getPais(){
        Pais paisBD = paisRepository.findPaisById(31L);

        if(paisBD == null){
            paisBD = new Pais();
            paisBD.setId(31L);

            paisRepository.save(paisBD);
        }

        return paisBD;
    }

    private List<Usuario> gerarUsuarios(Long numeroGerador, String gerador, String nome, Municipio municipio, Pais pais){
        List<Usuario> usuarios = new ArrayList<>();

        for(Long x = numeroGerador; x < numeroGerador + 5; x++){
            Usuario usuario = new Usuario();
            usuario.setChecked(false);
            usuario.setDataNascimento("2016-05-02T03:00:00.000Z");
            usuario.setEmail(gerador.concat("_").concat(x.toString()).concat(email));
            usuario.setEmailConfirmacao(usuario.getEmail());
            usuario.setEstrangeiro(false);
            usuario.setMunicipio(municipio);
            usuario.setNomeCompleto(nome);
            usuario.setNomeSocial(nome);
            usuario.setPais(pais);
            usuario.setReceberSMS(false);
            usuario.setSelecionado(false);
            usuario.setSenha("8be8a3bcaaa786c41a5893bac0eef7f8d24c9545ab83ce656dac236530f88055");
            usuario.setSenhaConfirmacao("8be8a3bcaaa786c41a5893bac0eef7f8d24c9545ab83ce656dac236530f88055");
            usuario.setSexo("MASCULINO");
            usuario.setSituacao("ATIVO");
            usuario.setNomeGerador(gerador);
            usuario.setNumeroGerador(x);

            usuarioRepository.save(usuario);

            Telefone telefone = new Telefone();
            telefone.setDdi("55");
            telefone.setTipoTelefone("CELULAR");
            telefone.setNumero("(54) 984"+((int)(Math.random()*99)+10)+"-"+((int)(Math.random()*9999)+1000));
            telefone.setUsuario(usuario);
            telefoneRepository.save(telefone);

            List<Telefone> telefones = new ArrayList<>();
            telefones.add(telefone);

            usuario.setTelefones(telefones);

            usuarios.add(usuario);
        }

        return usuarios;
    }

    private List<Usuario> salvarNoAVAMEC(List<Usuario> usuarios) {
        List<Usuario> usuariosSalvos = new ArrayList<>();
        String url = "http://hmg.labtime.ufg.br/ava-mec-ws/usuario/cadastra";

        usuarios.forEach(usuario -> {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPut put = new HttpPut(url);
            StringEntity bodyString = null;

            ObjectMapper mapper = new ObjectMapper();

            try {
                String usuarioJson = mapper.writeValueAsString(usuario);
                bodyString = new StringEntity(usuarioJson);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            put.setEntity(bodyString);
            put.setHeader("Content-type", "application/json");

            try {
                HttpResponse response = httpClient.execute(put);
                String responseStatus = response.getStatusLine().getStatusCode() + "";
                if(responseStatus.equalsIgnoreCase(HttpStatus.OK.toString())){
                    usuariosSalvos.add(usuario);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return usuariosSalvos;
    }
}
