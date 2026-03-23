/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;
import java.util.Date;
/**
 *
 * @author DELL
 */
public class aluguelDTO {
    private int    id;
    private int    idLivro;
    private int    idLeitor;
    private String nomeLeitor;
    private String tituloLivro;
    private Date   dataEmprestimo;
    private Date   dataDevolucao;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public int getIdLivro() {
        return idLivro;
    }
 
    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
 
    public int getIdLeitor() {
        return idLeitor;
    }
 
    public void setIdLeitor(int idLeitor) {
        this.idLeitor = idLeitor;
    }
 
    public String getNomeLeitor() {
        return nomeLeitor;
    }
 
    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }
 
    public String getTituloLivro() {
        return tituloLivro;
    }
 
    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }
 
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
 
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
 
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
 
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
 
    // Retorna true se o prazo de devolução já passou
    public boolean isAtrasado() {
        if (dataDevolucao == null) return false;
        return dataDevolucao.before(new Date());
    }    
}
