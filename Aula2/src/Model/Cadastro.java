package Model;

public class Cadastro {

	private int idLivros;
	private String Titulo;
	private String Autor;
	private String Ano;
	private String Local;
	private String Editora;

	public Cadastro() {

	}

	public Cadastro(String Titulo, String Autor, String Ano, String Local, String Editora) {

		this.Titulo = Titulo;
		this.Autor = Autor;
		this.Ano = Ano;
		this.Local = Local;
		this.Editora = Editora;

	}

	public Cadastro(int idLivros, String Titulo, String Autor, String Ano, String Local, String Editora) {

		this.idLivros = idLivros;
		this.Titulo = Titulo;
		this.Autor = Autor;
		this.Ano = Ano;
		this.Local = Local;
		this.Editora = Editora;

	}

	public int getIdLivros() {
		return idLivros;
	}

	public void setIdLivros(int idLivros) {
		this.idLivros = idLivros;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String Titulo) {
		this.Titulo = Titulo;
	}

	public String getAutor() {
		return Autor;
	}

	public void setAutor(String Autor) {
		this.Autor = Autor;
	}

	public String getAno() {
		return Ano;
	}

	public void setAno(String Ano) {
		this.Ano = Ano;
	}

	public String getLocal() {
		return Local;
	}

	public void setLocal(String Local) {
		this.Local = Local;
	}

	public String getEditora() {
		return Editora;
	}

	public void setEditora(String Editora) {
		this.Editora = Editora;
	}

}
