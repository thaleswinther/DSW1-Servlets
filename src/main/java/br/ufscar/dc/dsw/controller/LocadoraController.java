package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(urlPatterns = "/admin/locadoras/*")
public class LocadoraController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private LocadoraDAO dao;
    private UsuarioDAO daoUsuario;

    @Override
    public void init() {
        dao = new LocadoraDAO();
        daoUsuario = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

        System.out.println("PASSEI POR: LocadoraController"); 
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();    
        if (usuario == null ) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getPapel().equals("ADMIN")) {
            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }
            try {
                switch (action) {
                    case "/cadastro":
                        apresentaFormCadastro(request, response);
                        break;
                    case "/insercao":
                        insere(request, response);
                        break;
                    case "/remocao":
                        remove(request, response);
                        break;
                    case "/edicao":
                        apresentaFormEdicao(request, response);
                        break;
                    case "/atualizacao":
                        atualize(request, response);
                        break;
                    default:
                        lista(request, response);
                        break;
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
        } else {
            erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        request.getSession().setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/locadora/lista.jsp");
        dispatcher.forward(request, response);
    }

    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/locadora/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Locadora locadora = dao.get(id);
        request.setAttribute("locadora", locadora);
        RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF/locadora/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String papel= request.getParameter("papel");
            
            Usuario usuario = new Usuario(email, senha, nome, papel);
            daoUsuario.insert(usuario);
            usuario = daoUsuario.getbyEmail(email);
            String cnpj = request.getParameter("CNPJ");
            String cidade = request.getParameter("cidade");
            Locadora locadora = new Locadora(usuario.getId(), email, senha, nome, papel, cnpj, cidade);
            dao.insert(locadora);
            response.sendRedirect("lista");
        } catch (RuntimeException | IOException e) {
            throw new ServletException(e);
        }
    }


    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String papel = request.getParameter("papel");
            
            Usuario usuario = daoUsuario.get(Long.parseLong(request.getParameter("id")));

            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setPapel(papel);
            daoUsuario.update(usuario);

            String cnpj = request.getParameter("CNPJ");
            String cidade = request.getParameter("cidade");
            Locadora locadora = dao.get(usuario.getId());

            locadora.setCNPJ(cnpj);
            locadora.setCidade(cidade);

            dao.update(locadora);
            response.sendRedirect("lista");
        } catch (RuntimeException | IOException e) {
            throw new ServletException(e);
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Usuario usuario = daoUsuario.get(Long.parseLong(request.getParameter("id")));
            daoUsuario.delete(usuario);
            response.sendRedirect("lista");
        } catch (RuntimeException | IOException e) {
            throw new ServletException(e);
        }
    }
}