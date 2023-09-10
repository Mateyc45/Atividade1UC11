/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    public ProdutosDTO produto = new ProdutosDTO();
    
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES "
            + "(?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no BD "+ e.getMessage());
        }  
    }
    List<ProdutosDTO> listaProdutos = new ArrayList<>();
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM produtos");
            stmt.executeQuery();
            ResultSet rs = null;
            rs = stmt.executeQuery();

           
            while (rs.next()) { 
                ProdutosDTO produtos = new ProdutosDTO();
       
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));

                listaProdutos.add(produtos);

            }
           
           

        } catch (SQLException e) {
            return null;
        }
        return (ArrayList<ProdutosDTO>) listaProdutos;
    }
    
    public void venderProduto(ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET `status` = 'Vendido' where id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.execute();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no BD "+ e.getMessage());
        }  
    }
    
    List<ProdutosDTO> listaProdutosVendidos = new ArrayList<>();
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        try {
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM produtos where `status` = 'Vendido';");
            stmt.executeQuery();
            ResultSet rs = null;
            rs = stmt.executeQuery();

           
            while (rs.next()) { 
                ProdutosDTO produtos = new ProdutosDTO();
       
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));

                listaProdutosVendidos.add(produtos);

            }
           
           

        } catch (SQLException e) {
            return null;
        }
        return (ArrayList<ProdutosDTO>) listaProdutosVendidos;
    }
    
}
