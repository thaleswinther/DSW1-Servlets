package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.util.Erro;

//import java.util.ArrayList;
//import java.nio.charset.StandardCharsets;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PASSEI POR: IndexController"); 
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			if (login == null || login.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = dao.getbyEmail(login);
				if (usuario != null) {
					if (usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("usuarioLogado", usuario);
						if (usuario.getPapel().equals("ADMIN")) {
							response.sendRedirect("admin/");
						} else {
							if (usuario.getPapel().equals("Cliente") || usuario.getPapel().equals("Locadora") ) {
								response.sendRedirect("usuario/");
							} 			
						}
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		request.getSession().invalidate();

		List<Locadora> listaLocadoras = new LocadoraDAO().getAll();
		request.getSession().setAttribute("listaLocadoras", listaLocadoras);
		List<Locacao> listaLocacoes = new LocacaoDAO().getAll();
        request.getSession().setAttribute("listaLocacoes", listaLocacoes);
		request.setAttribute("mensagens", erros);
		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);

		
	}
}