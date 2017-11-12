/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import classes.Mensagem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.time.Clock.system;
import java.util.ArrayList;
/**
 *
 * @author Syane
 */
public class ListaMensagens {
    
    private static ListaMensagens Inst = new ListaMensagens(); 
    public ArrayList<Mensagem> Mensagens = new ArrayList<>();
    private String arquivo;
    
    public void setArquivo(String arquivo){
        this.arquivo = arquivo;
    }
    
    
    public static ListaMensagens getMensagens(){
        
        return Inst;
    }
    
    public void lerArquivo(){
            FileReader arq;
            try{
                arq = new FileReader(this.arquivo);
                BufferedReader buffRead = new BufferedReader(arq);
                
                String linha = buffRead.readLine();
                System.out.println(linha);
                
                while(linha != null){
                    Mensagem msg = new Mensagem();
                    System.out.println("entrei aqui");
                    String[] msgs = linha.split(",");
                    msg.setEmail(msgs[0]);
                    msg.setMensagem(msgs[1]);
                    linha = buffRead.readLine();
                    this.Mensagens.add(msg);
                }
                arq.close();
            }
            catch(IOException e){
                    
                System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
                        
            }
        }
    
    public void gravarMensagem(Mensagem msg) throws IOException{
        Mensagens.add(msg);
        BufferedWriter bw = null; 
        FileWriter fp = new FileWriter(arquivo, true);
        try {
            bw = new BufferedWriter(fp);
            bw.write(msg.getEmail() + "," + msg.getMensagem()+"\n");
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try {
                    bw.close();
                    fp.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }
    }
    
    public void removeMensagem(Mensagem msg){
        Mensagens.remove(msg);
    }
}
