package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;

import Dao.CadastroDao;
import Model.Cadastro;
import View.LivrosView;

public class LivrosController {

	private LivrosView view = new LivrosView();

	public LivrosController() {

		MouseListener ouvinte2 = new MouseListener() {

			public void mouseReleased(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {

				if (e.getSource() == view.getTabela()) {

					int linha = view.getTabela().getSelectedRow();

					int idLivros = (int) view.getTabela().getValueAt(linha, 0);
					String Titulo = (String) view.getTabela().getValueAt(linha, 1);
					String Autor = (String) view.getTabela().getValueAt(linha, 2);
					String Ano = (String) view.getTabela().getValueAt(linha, 3);
					String Local = (String) view.getTabela().getValueAt(linha, 4);
					String Editora = (String) view.getTabela().getValueAt(linha, 5);
					view.getcidLivrosA().setText(String.format("%d", idLivros));
					view.getcTituloA().setText(Titulo);
					view.getcAutorA().setText(Autor);
					view.getcAno().setText(Ano);
					view.getcLocalA().setText(Local);
					view.getcEditoraA().setText(Editora);
					view.getbAlterar().setEnabled(true);
					view.getbExcluir().setEnabled(true);

				}

			}

		};

		ActionListener ouvinte = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == view.getbSalvar()) {

					// ================SALVAR===============//
					Cadastro cadastro = new Cadastro();
					cadastro.setTitulo(view.getcTitulo().getText());
					cadastro.setAutor(view.getcAutor().getText());
					cadastro.setAno(view.getcAno().getText());
					cadastro.setLocal(view.getcLocal().getText());
					cadastro.setEditora(view.getcEditora().getText());

					CadastroDao dao = new CadastroDao();
					dao.inserir(cadastro);

					JOptionPane.showMessageDialog(null, "!----SALVO----!");
					List<Cadastro> listaLivros = dao.consultar();
					view.CarregarTabela(listaLivros, ouvinte2);
					view.limparCampos();

				} else 

					if (e.getSource() == view.getbAlterar()) {

						// =================ALTERAR==================//
						Cadastro cadastro = new Cadastro();
						String id = view.getcidLivrosA().getText();

						if (!id.equals("")) {

							cadastro.setIdLivros(Integer.parseInt(id));
							cadastro.setTitulo(view.getcTituloA().getText());
							cadastro.setAutor(view.getcAutorA().getText());
							cadastro.setAno(view.getcAnoA().getText());
							cadastro.setLocal(view.getcLocalA().getText());
							cadastro.setEditora(view.getcEditoraA().getText());

							CadastroDao dao = new CadastroDao();
							dao.alterar(cadastro);

							JOptionPane.showMessageDialog(null, "!----ALTERADO----!");
							List<Cadastro> listaLivros = dao.consultar();
							view.CarregarTabela(listaLivros, ouvinte2);
							view.limparCampos();
						}

					} else 

						if (e.getSource() == view.getbExcluir()) {

							// ===============EXCLUIR===========//
							String id = view.getcidLivrosA().getText();

							if (!id.equals("")) {

								CadastroDao dao = new CadastroDao();
								dao.Excluir(Integer.parseInt(id));
								JOptionPane.showMessageDialog(null, "!----DELETADO----!");
								List<Cadastro> listaLivros = dao.consultar();
								view.CarregarTabela(listaLivros, ouvinte2);
								view.limparCampos();

							}

						}

					}

		};

		view.ConfgComponentes(ouvinte);

		CadastroDao dao = new CadastroDao();
		List<Cadastro> listaLivros = dao.consultar();
		view.CarregarTabela(listaLivros, ouvinte2);

	}

}
