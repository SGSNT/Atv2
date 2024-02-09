package Dao;

import Model.Cadastro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CadastroDao {

	private ConexaoBD conexao;

	public CadastroDao() {

		this.conexao = new ConexaoBD();
	}

	public void inserir(Cadastro cadastro) {
	    String sql = "INSERT INTO livros(Titulo,Autor,Ano,Local,Editora) VALUES (?,?,?,?,?)";

	    try {
	        if (this.conexao != null && !this.conexao.getCon().isClosed()) {
	            Connection conexao = this.conexao.getCon();
	            System.out.println("Conexão estabelecida com sucesso.");
	            
	            PreparedStatement sentenca = conexao.prepareStatement(sql);
	            System.out.println("Inserindo os seguintes dados:");
	            System.out.println("Título: " + cadastro.getTitulo());
	            System.out.println("Autor: " + cadastro.getAutor());
	            System.out.println("Ano: " + cadastro.getAno());
	            System.out.println("Local: " + cadastro.getLocal());
	            System.out.println("Editora: " + cadastro.getEditora());
	            
	            sentenca.setString(1, cadastro.getTitulo());
	            sentenca.setString(2, cadastro.getAutor());
	            sentenca.setString(3, cadastro.getAno());
	            sentenca.setString(4, cadastro.getLocal());
	            sentenca.setString(5, cadastro.getEditora());

	            sentenca.execute();
	            sentenca.close();
	            conexao.close();
	            
	            System.out.println("Inserção realizada com sucesso.");
	        } else {
	            System.out.println("Conexão não está disponível ou fechada.");
	        }
	    } catch (Exception ex) {
	        System.err.println("Erro ao inserir no banco de dados: " + ex.getMessage());
	        ex.printStackTrace();
	        throw new RuntimeException(ex);
	    }
	}



	public void alterar(Cadastro cadastro) {

		String sql = "UPDATE livros SET Titulo = ?, Autor = ?, Ano = ?, Local = ?, Editora = ? where idLivros = ?";

		try {

			if (this.conexao != null && !this.conexao.getCon().isClosed()) {

				Connection conexao = this.conexao.getCon();
				PreparedStatement sentenca = conexao.prepareStatement(sql);

				sentenca.setString(1, cadastro.getTitulo());
				sentenca.setString(2, cadastro.getAutor());
				sentenca.setString(3, cadastro.getAno());
				sentenca.setString(4, cadastro.getLocal());
				sentenca.setString(5, cadastro.getEditora());
				sentenca.setInt(6, cadastro.getIdLivros());

				sentenca.execute();
				sentenca.close();
				conexao.close();
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	public void Excluir(int idlivros) {

		String sql = "DELETE FROM livros WHERE idLivros = ?";

		try {

			if (this.conexao != null && !this.conexao.getCon().isClosed()) {

				Connection conexao = this.conexao.getCon();
				PreparedStatement sentenca = conexao.prepareStatement(sql);

				sentenca.setInt(1, idlivros);

				sentenca.execute();
				sentenca.close();
				conexao.close();
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	public ArrayList<Cadastro> consultar() {

		ArrayList<Cadastro> listarLivros = new ArrayList<Cadastro>();

		String sql = "SELECT l.idLivros,l.Titulo,l.Autor,l.Ano,l.Local,l.Editora " + "FROM livros AS l "
				+ "ORDER BY l.idLivros";

		try {

			if (this.conexao != null && !this.conexao.getCon().isClosed()) {

				Connection conexao = this.conexao.getCon();
				PreparedStatement sentenca = conexao.prepareStatement(sql);
				ResultSet resultado = sentenca.executeQuery();

				while (resultado.next()) {

					Cadastro cadastro = new Cadastro();

					cadastro.setIdLivros(resultado.getInt("idLivros"));
					cadastro.setTitulo(resultado.getString("Titulo"));
					cadastro.setAutor(resultado.getString("Autor"));
					cadastro.setAno(resultado.getString("Ano"));
					cadastro.setLocal(resultado.getString("Local"));
					cadastro.setEditora(resultado.getString("Editora"));

					listarLivros.add(cadastro);
				}

				sentenca.close();
				conexao.close();

			}

			return listarLivros;

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

}
